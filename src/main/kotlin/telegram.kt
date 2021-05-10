import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.ParseMode
import com.google.common.util.concurrent.RateLimiter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Every 30 seconds
private val rateLimiter: RateLimiter = RateLimiter.create(0.03)

class TelegramBot(private val supplier: () -> List<String>, private val telegramApiToken: String) {
    private val tasks = mutableSetOf<String>()

    fun startPolling() {
        getBot().startPolling()
    }

    private fun getBot(): Bot {
        return bot {
            token = telegramApiToken
            dispatch {
                command("status") {

                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Retrieving status")
                    for (status in supplier.invoke()) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = status)
                    }
                }

                command("poll") {
                    if (tasks.add(message.chat.id.toString())) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Starting poller")
                        keepPolling(bot, message)
                    } else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Poller is already running!")
                    }
                }

                command("stoppolling") {
                    bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Stopping poller")
                    println("Stopping poller")
                    tasks.remove(message.chat.id.toString())
                }
            }
        }
    }

    private fun keepPolling(bot: Bot, message: Message) {
        GlobalScope.launch {
            while (tasks.contains(message.chat.id.toString())) {
                rateLimiter.acquire()
                for (status in supplier.invoke()) {
                    val result = bot.sendMessage(
                        chatId = ChatId.fromId(message.chat.id),
                        text = status,
                        parseMode = ParseMode.MARKDOWN_V2
                    )
                    println(result)
                }
            }
        }
        println("keep going")
    }
}

