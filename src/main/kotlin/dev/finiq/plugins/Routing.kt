package dev.finiq.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        swaggerUI(path = "openapi")
    }
    routing {
        openAPI(path = "openapi")
    }
    routing {
        route("/databases") {
            route("/{dbName}") {
                route("/tables") {
                    route("/{tableName}") {
                        get {
                            val dbName = call.parameters["dbName"]
                            val tableName = call.parameters["tableName"]
                            call.respondText("Hello World! Here it's $dbName.$tableName")
                        }
                    }
                }
            }
        }
    }
}


/*
GET /databases
GET /databases/{dbName}/tables
GET /databases/{dbName}/tables/{tableName}/columns

DELETE /databases/{dbName}/tables/{tableName}
DELETE /databases/{dbName}/tables/{tableName}/columns/{columnName}

POST /databases/{dbName}/tables
 */