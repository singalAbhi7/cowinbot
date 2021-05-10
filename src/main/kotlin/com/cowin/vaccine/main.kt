package com.cowin.vaccine

import kotlin.time.ExperimentalTime


val groups = mapOf(
//  "blr" to arrayOf("265", "276", "294", "291", "277", "292", "288"),
    "blr" to arrayOf("265", "276", "294"),
    "del" to arrayOf("141", "145", "140", "146", "147", "143", "149", "144", "150", "142")
)
val names = mapOf(
    "265" to "Bangalore Urban",
    "276" to "Bangalore Rural",
    "294" to "BBMP",
    "291" to "Chikkaballapur",
    "277" to "Kolar",
    "292" to "Ramanagara",
    "288" to "Tumkur",

    "141" to "Central Delhi",
    "145" to "East Delhi",
    "140" to "New Delhi",
    "146" to "North Delhi",
    "147" to "North East Delhi",
    "143" to "North West Delhi",
    "149" to "South Delhi",
    "144" to "South East Delhi",
    "150" to "South West Delhi",
    "142" to "West Delhi"
)

@ExperimentalTime
fun main(args: Array<String>) {
    val poller = Poller(Cowin(), groups, names)
    val telegramBot = TelegramBot ({group: String -> poller.getStatuses(group) }, args[0])
    telegramBot.startPolling()
}