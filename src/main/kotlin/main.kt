import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main(args: Array<String>) {
    val poller = Poller(Cowin())
    val telegramBot = TelegramBot ({ poller.getStatuses() }, args[0])
    telegramBot.startPolling()
}