package com.cowin.vaccine

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.ParseMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

class TelegramBot(private val supplier: (group: String) -> List<String>, private val telegramApiToken: String) {
    private val tasks = mutableSetOf<String>()

    fun startPolling() {
        getBot().startPolling()
    }

    private fun getBot(): Bot {
        return bot {
            token = telegramApiToken
            dispatch {
                command("status") {
                    if (args.isNotEmpty() && groups.containsKey(args[0])) {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Retrieving status")
                        for (status in supplier.invoke(args[0])) {
                            bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = status)
                        }
                    }
                }

                command("poll") {
                    if (tasks.add(message.chat.id.toString())) {
                        if (args.isNotEmpty() && groups.containsKey(args[0])) {
                            bot.sendMessage(
                                chatId = ChatId.fromId(message.chat.id),
                                text = "Looking for vaccine availability"
                            )
                            keepPolling(bot, message, args[0])
                        } else {
                            bot.sendMessage(
                                chatId = ChatId.fromId(message.chat.id),
                                text = "Invalid group passed!"
                            )
                        }
                    } else {
                        bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Already running!")
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

    // May not be the most ideal solution but since this is a task that has to be live for the lifecycle of the
    // application, I think it's okay to use GlobalScope.
    @OptIn(kotlin.time.ExperimentalTime::class)
    private fun keepPolling(bot: Bot, message: Message, group: String) {
        CoroutineScope(Dispatchers.Default).launch {
            while (tasks.contains(message.chat.id.toString())) {
                delay(Duration.Companion.seconds(1))
                println("Updating for group: $group")
                try {
                    for (status in supplier.invoke(group)) {
                        sendMessage(bot, message, status)
                    }
                } catch (e: Exception) {
                    // TODO: Do something about this later. Right now, the application is being monitored regularly.
                    println(e)
                }
            }
        }
        println("keep going")
    }

    private fun sendMessage(bot: Bot, message: Message, status: String) {
        val result = bot.sendMessage(
            chatId = ChatId.fromId(message.chat.id),
            text = status.replace('-', ' '), // Telegram doesn't like '-'
            parseMode = ParseMode.MARKDOWN
        )
        if (result.first?.isSuccessful != true) {
            println(result)
            // try without the markdown parser
            bot.sendMessage(
                chatId = ChatId.fromId(message.chat.id),
                text = sanitizeMessage(status),
            )
        }
    }

    // Telegram doesn't like '-', '.' when using MARKDOWN
    private fun sanitizeMessage(message: String): String {
        return message
            .replace('-', ' ')
            .replace('.', ' ')
    }
}

