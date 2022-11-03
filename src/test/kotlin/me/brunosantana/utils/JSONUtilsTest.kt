package me.brunosantana.utils

import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

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
                                    "\"message\": \"my message\"," +
                                    "\"family\": {" +
                                        "\"father\": \"John\"," +
                                        "\"children\": {" +
                                            "\"first\": \"Elizabeth\"" +
                                        "}" +
                                    "}" +
                                "}" +
                            "}" +
                        "}," +
                        "\"Brazil\": {" +
                            "\"states\": [" +
                                "{" +
                                    "\"acronym\": \"SP\"," +
                                    "\"name\": \"São Paulo\"" +
                                "}," +
                                "{" +
                                    "\"acronym\": \"BA\"," +
                                    "\"name\": \"Bahia\"" +
                                "}," +
                                "{" +
                                    "\"acronym\": \"MG\"," +
                                    "\"name\": \"Minas Gerais\"" +
                                "}," +
                                "{" +
                                    "\"acronym\": \"PE\"," +
                                    "\"name\": \"Pernambuco\"" +
                                "}" +
                            "]" +
                        "}" +
                     "}"

    @Test
    fun `should get an item 1 level deep (getItem1LevelDeep)`() {
        val jsonObject = JSONObject(jsonString)
        val result = jsonObject.getItem1LevelDeep("book", "data")
        Assertions.assertEquals("A vida de David Brainerd", result)
    }

    @Test
    fun `should get an item 2 levels deep (getItem2LevelsDeep)`() {
        val jsonObject = JSONObject(jsonString)
        val result = jsonObject.getItem2LevelsDeep("id", "data", "response")
        Assertions.assertEquals("10", result)
    }

    @Nested
    @DisplayName("Test functions that call the recursive function")
    inner class TestRecursiveFunctionality {

        @Test
        fun `should get an item 1 level deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.getStringAnyLevelDeep(jsonObject, "book", "data")
            println("result: $result")
            Assertions.assertEquals("A vida de David Brainerd", result)
        }

        @Test
        fun `should get an item 2 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.getStringAnyLevelDeep(jsonObject, "id", "data", "response")
            println("result: $result")
            Assertions.assertEquals("10", result)
        }

        @Test
        fun `should get an item 3 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result =
                jsonObject.getStringAnyLevelDeep(jsonObject, "message", "data", "response", "content")
            println("result: $result")
            Assertions.assertEquals("my message", result)
        }

        @Test
        fun `should get an item 4 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result =
                jsonObject.getStringAnyLevelDeep(
                    jsonObject,
                    "father",
                    "data",
                    "response",
                    "content",
                    "family"
                )
            println("result: $result")
            Assertions.assertEquals("John", result)
        }

        @Test
        fun `should get an item 5 levels deep`() {
            val jsonObject = JSONObject(jsonString)
            val result =
                jsonObject.getStringAnyLevelDeep(
                    jsonObject,
                    "first",
                    "data",
                    "response",
                    "content",
                    "family",
                    "children"
                )
            println("result: $result")
            Assertions.assertEquals("Elizabeth", result)
        }

        @Test
        fun `should get JSONArray 1 level deep`() {
            val jsonObject = JSONObject(jsonString)
            val result: JSONArray = jsonObject.getJSONArrayAnyLevelDeep(
                jsonObject,
                "states",
                "Brazil"
            )

            println("result: $result")

            val sp = JSONObject()
            sp.put("acronym", "SP")
            sp.put("name", "São Paulo")

            val ba = JSONObject()
            ba.put("acronym", "BA")
            ba.put("name", "Bahia")

            val mg = JSONObject()
            mg.put("acronym", "MG")
            mg.put("name", "Minas Gerais")

            val pe = JSONObject()
            pe.put("acronym", "PE")
            pe.put("name", "Pernambuco")

            val jsonArray = JSONArray()
            jsonArray.put(sp)
            jsonArray.put(ba)
            jsonArray.put(mg)
            jsonArray.put(pe)

            JSONAssert.assertEquals(jsonArray.toString(), result.toString(), JSONCompareMode.NON_EXTENSIBLE);
        }

        @Test
        fun `should get String List 1 level deep for acronym`() {
            val jsonObject = JSONObject(jsonString)
            val result: List<String> = jsonObject.getListAnyLevelDeep(
                jsonObject,
                "states",
                "acronym",
                "Brazil"
            )

            println("result: $result")

            Assertions.assertTrue(result.containsAll(listOf("SP", "BA", "MG", "PE")))
        }

        @Test
        fun `should get String List 1 level deep for name`() {
            val jsonObject = JSONObject(jsonString)
            val result: List<String> = jsonObject.getListAnyLevelDeep(
                jsonObject,
                "states",
                "name",
                "Brazil"
            )

            println("result: $result")

            Assertions.assertTrue(result.containsAll(listOf("São Paulo", "Bahia", "Minas Gerais", "Pernambuco")))
        }

        @Test
        fun `should get Map String String 1 level deep for acronym and name`() {
            val jsonObject = JSONObject(jsonString)
            val result: Map<String, String> = jsonObject.getMapAnyLevelDeep(
                jsonObject,
                "states",
                mapOf("key" to "acronym", "value" to "name"),
                "Brazil"
            )

            println("result: $result")

            Assertions.assertEquals("São Paulo", result["SP"])
        }
    }

}