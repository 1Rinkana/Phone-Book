package com.example.phonebook.data.remote.dto

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get

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

    suspend fun getNewClient(): DataResponse {
        return client.get("https://randomuser.me/api/?inc=name,phone,picture").body()
    }
}
