package com.foo.server.controllers

import io.ktor.application.call
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.locations.*

fun Route.defaultRoutes(){
    route("") {
        get("/") {
            call.respondHtml {
                body {
                    h1 { +"Accessing the default route" }
                    p { +"Running >.ktor"}
                    i { +"Rendered using kotlin DSL for HTML"}
                }
            }
        }
        // Static files is served here
        static("/static") {
            resources("static")
        }
        // Compiled SPA is served here
        static("/client") {
            resources("client")
        }
    }
}