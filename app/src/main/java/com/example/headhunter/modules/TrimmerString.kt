package com.example.headhunter.modules

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TrimmerString : JsonDeserializer<String> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String {
        return json.asString.trim() // Убираем пробелы
    }
}