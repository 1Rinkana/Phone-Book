package com.example.phonebook.data.remote.dto

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ClientApi {
    private val client = HttpClient (CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getNewClients(): DataResponse {
        val result = client.get("https://randomuser.me/api") {
            parameter("inc", "name,phone,picture")
            parameter("results", "10")
        }
        return result.body()
    }
}
