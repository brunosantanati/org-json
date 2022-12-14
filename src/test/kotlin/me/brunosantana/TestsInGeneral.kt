package me.brunosantana

import org.json.JSONObject
import org.junit.jupiter.api.Test
import net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson
import org.json.JSONException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import kotlin.test.assertTrue

class TestsInGeneral {

    //https://www.baeldung.com/java-org-json
    //https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONObjectIntegrationTest.java

    @Test
    fun `should use the put method to create the json`() {
        val jo = JSONObject()
        jo.put("name", "jon doe")
        jo.put("age", "22")
        jo.put("city", "chicago")

        val expectedJsonString = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"

        assertThatJson(jo)
            .isEqualTo(expectedJsonString)

        assertEquals("chicago", jo.get("city"))
        assertEquals("jon doe", jo.get("name"))
        assertEquals("22", jo.get("age"))
    }

    @Test
    fun `should create the json from a map`() {
        val map = mutableMapOf<String, String>(
            "name" to "jon doe",
            "age" to "22",
            "city" to "chicago"
        )

        val jo = JSONObject(map)

        val expectedJsonString = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"

        assertThatJson(jo)
            .isEqualTo(expectedJsonString)

        assertEquals("chicago", jo.get("city"))
        assertEquals("jon doe", jo.get("name"))
        assertEquals("22", jo.get("age"))
    }

    @Test
    fun `should create the json from a json string`() {
        val jo = JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}")

        val expectedJsonString = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"

        assertThatJson(jo)
            .isEqualTo(expectedJsonString)

        assertEquals("chicago", jo.get("city"))
        assertEquals("jon doe", jo.get("name"))
        assertEquals("22", jo.get("age"))
    }

    @Test
    fun `should use getString to get a string value from JSONObject`() {
        val jo = JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}")
        assertEquals("chicago", jo.getString("city"))
    }

    @Test
    fun `should throw an JSONException when the key does not exist`() {
        val jo = JSONObject("{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}")

        val ex: JSONException = assertThrows(JSONException::class.java) {
            jo.getString("key-that-does-not-exist")
        }

        assertTrue(ex.message!!.startsWith("JSONObject"))
        assertTrue(ex.message!!.endsWith("not found."))

    }

}