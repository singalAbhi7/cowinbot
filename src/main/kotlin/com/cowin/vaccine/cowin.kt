package com.cowin.vaccine

import com.github.kittinunf.fuel.httpGet
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.util.concurrent.RateLimiter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

private val getUrl = { district:String, date:String -> "https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByDistrict?district_id=$district&date=$date"}
private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
private val headers = mapOf(
    "user-agent" to "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36",
    "accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
)
private val rateLimiter: RateLimiter = RateLimiter.create(0.5)

class Cowin {
    private val cacheLoader = CacheBuilder.newBuilder()
        .refreshAfterWrite(5, TimeUnit.SECONDS)
        .build(Loader())

    fun getCowinResponse(district: String): String {
        return cacheLoader.get(district)
    }
}

private class Loader: CacheLoader<String, String>() {
    override fun load(district: String): String {
        rateLimiter.acquire()
        val url = getUrl(district, getDateString())
        val (request, response, result) = url.httpGet()
            .header(headers)
            .responseString()
        val (payload, error) = result
        if (error != null) {
            println("Cowin API error for district ${names[district]}: $error")
        }
        return payload ?: ""
    }
}

fun getDateString(): String {
    return ZonedDateTime.now().format(formatter)
}