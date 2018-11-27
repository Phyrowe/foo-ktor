package com.foo.api.v1.controllers

import io.ktor.application.call
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.locations.*

// Just some test routes
fun Route.testRoutes(){
    route("/api/v1/test") {
        get("/") {
            call.respondText("Running >.ktor", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(User(191, "Guest"))
        }

        get<CustomLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }
    }
}


class User(val id: Int, val name: String, val password: String = "******")

@Location("/location/{name}")
class CustomLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}