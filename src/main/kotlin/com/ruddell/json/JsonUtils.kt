package com.ruddell.json

import kotlinx.serialization.json.Json

val jsonSerializer = Json { ignoreUnknownKeys = true }
val jsonDeserializer = Json { ignoreUnknownKeys = true }
