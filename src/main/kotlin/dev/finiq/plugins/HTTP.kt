package dev.finiq.plugins

import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.application.*

fun Application.configureHTTP() {
    routing {
        swaggerUI(path = "openapi")
    }
    routing {
        openAPI(path = "openapi")
    }
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
}