package com.cowin.vaccine

import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(args: Array<String>) {
    val poller = Poller(Cowin())
    val telegramBot = TelegramBot ({group: String -> poller.getStatuses(group) }, args[0])
    telegramBot.startPolling()
}