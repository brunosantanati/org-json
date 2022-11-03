package me.brunosantana.utils

import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class JSONUtilsTest {

    val jsonString = "{" +
                        "\"city\":\"chicago\"," +
                        "\"name\":\"jon doe\"," +
                        "\"age\":\"22\"," +
                        "\"data\": {" +
                            "\"book\": \"A vida de David Brainerd\"," +
                            "\"response\": {" +
                                "\"id\": \"10\"," +
                                "\"content\": {" +
                                    "\"message\": \"my message\"" +
                                "}" +
                            "}" +
                        "}" +
                     "}"

    @Nested
    @DisplayName("Testing first implementation of getItemAnyLevelDeep")
    inner class TestingFirstImplementation {
        @Test
        fun `should get an item 1 level deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = getItemAnyLevelDeep(jsonObject, "data", "book")
            println("result: $result")
            Assertions.assertEquals("A vida de David Brainerd", result)
        }

        @Test
        fun `should get an item 2 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = getItemAnyLevelDeep(jsonObject, "data", "response", "id")
            println("result: $result")
            Assertions.assertEquals("10", result)
        }

        @Test
        fun `should get an item 3 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = getItemAnyLevelDeep(jsonObject, "data", "response", "content", "message")
            println("result: $result")
            Assertions.assertEquals("my message", result)
        }
    }

    @Nested
    @DisplayName("Testing second implementation of getItemAnyLevelDeep")
    inner class TestingSecondImplementation {

        @Test
        fun `should get an item 1 level deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.getItemAnyLevelDeep(jsonObject, "data", "book")
            println("result: $result")
            Assertions.assertEquals("A vida de David Brainerd", result)
        }

        @Test
        fun `should get an item 2 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.getItemAnyLevelDeep(jsonObject, "data", "response", "id")
            println("result: $result")
            Assertions.assertEquals("10", result)
        }

        @Test
        fun `should get an item 3 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.getItemAnyLevelDeep(jsonObject, "data", "response", "content", "message")
            println("result: $result")
            Assertions.assertEquals("my message", result)
        }
    }

}