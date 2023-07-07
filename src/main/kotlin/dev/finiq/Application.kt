package dev.finiq

import io.ktor.server.application.*
import dev.finiq.plugins.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    configureHTTP()
    configureRouting()
    configureSecurity()
}
