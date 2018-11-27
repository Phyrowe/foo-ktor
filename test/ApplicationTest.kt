package com.foo.application

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.locations.*
import org.slf4j.event.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/api/v1/test/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Running >.ktor", response.content)
            }
        }
    }
}
