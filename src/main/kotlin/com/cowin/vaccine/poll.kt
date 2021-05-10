package com.cowin.vaccine

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.StringReader
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

private val klaxon = Klaxon()

@ExperimentalTime
class Poller(
    private val cowinApi: Cowin,
    private val districtGroups: Map<String, Array<String>>,
    private val districtNames: Map<String, String>
) {
    private val dedupe = mutableSetOf<String>()

    init {
        expireDedupe()
    }

    fun getStatuses(group: String = "blr"): List<String> {
        val messages = mutableListOf<String>()
        for (district in districtGroups[group]!!) {
            messages.addAll(forDistrict(district))
        }
        return messages
    }

    private fun forDistrict(district: String): List<String> {
        val messages = mutableListOf<String>()
        val payload = cowinApi.getCowinResponse(district)
        val json = try {
            klaxon.parseJsonObject(StringReader(payload))
        } catch (e: Exception) { null }
        if (json != null) {
            val sessions = processJson(json)
            val list = mutableListOf<String>()
            if (sessions.isNotEmpty()) {
                list.add("*${districtNames[district]} District*")
                list.addAll(sessions)
                messages.add(list.joinToString(separator = "\n"))
            }
        }

        return messages
    }

    // TODO: Code needs to be cleaned up.
    private fun processJson(json: JsonObject): List<String> {
        val list = mutableListOf<String>()
        val centers = json["centers"] as? JsonArray<JsonObject>?
        if (centers != null) {
            for (center in centers) {
                val sessions = center["sessions"] as? JsonArray<JsonObject>?
                if (sessions != null) {
                    for (session in sessions) {
                        val ageLimit = session["min_age_limit"] as? Int ?: 0
                        val available = session["available_capacity"] as? Int ?: getCastedCapacity(session)
                        if (ageLimit < 45 && available >= 5) {
                            val message = printSession(center, session)
                            if (dedupe.add(message)) list.add(message)
                        }
                    }
                }
            }
        }
        return list
    }

    // May not be the most ideal solution but since this is a task that has to be live for the lifecycle of the
    // application, I think it's okay to use GlobalScope
    @OptIn(DelicateCoroutinesApi::class)
    private fun expireDedupe()  {
        GlobalScope.launch {
            while(true) {
                delay(Duration.minutes(5))
                dedupe.clear()
            }
        }
    }
}

// Sometimes capacity is formatted as a double in the JSON response.
private fun getCastedCapacity(session: JsonObject): Int {
    return (session["available_capacity"] as? Double ?: 0).toInt()
}

private fun printSession(center: JsonObject, session: JsonObject): String {
    val date: String = (session["date"] as String).replace('-', '/')
    return "*${center["name"]}*, ${date}, *Available slots:* ${session["available_capacity"]}"
}

