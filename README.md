## What and why
This is a rudimentary telegram bot server to poll cowin vaccination APIs and publish vaccine availability to a telegram bot. 

Some features:
- Dedupe updates over a 5 minute window to avoid sending multiple back to back duplicate notifications.
- Ability to configure district groups as needed and start bot pollers for a specific group. 
- API calls to cowin are cached (Guava cache loader), rate limited and auto refreshed every X seconds. 

## Usage
- Checkout the repository and build it (Recommend using Intellij to begin with). See below for building from scratch using CLI
- Execute with Telegram API token as a parameter.
  - If using the compiled Jar: `java -jar <executable>.jar <API Token>`
  - If using Intellij's run configuration, simply add the API token in the program arguments in the edit configuration menu.

## Building from CLI
- Install gradle: https://docs.gradle.org/current/userguide/installation.html (Note: Be sure to install gradle-6.x. Build will fail with gradle 7).
- Run `gradle wrapper` from the repository root to generate build scripts
- Run `./gradlew build` 

## Credit to
- https://github.com/kotlin-telegram-bot/kotlin-telegram-bot
- https://github.com/cbeust/klaxon
- https://github.com/kittinunf/fuel
- https://github.com/mockk/mockk

## References
- https://core.telegram.org/bots/api
- https://docs.gradle.org/current/samples/sample_building_java_applications.html#run_the_init_task
