package me.brunosantana

import org.json.JSONArray
import org.json.JSONObject

fun main() {
    //https://www.baeldung.com/java-org-json
    //https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONObjectIntegrationTest.java
    //https://stackoverflow.com/questions/14898768/how-to-access-nested-elements-of-json-object-using-getjsonarray-method

    /*
    JSONArray
    https://www.baeldung.com/java-org-json#jsonarray
    https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/main/java/com/baeldung/jsonjava/JSONArrayDemo.java
    https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/main/java/com/baeldung/jsonjava/JSONArrayGetValueByKey.java
    https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONArrayGetValueByKeyUnitTest.java
    https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONArrayIntegrationTest.java
     */

    val jo = JSONObject(
        "{" +
                    "\"city\":\"chicago\"," +
                    "\"name\":\"jon doe\"," +
                    "\"age\":\"22\"," +
                    "\"data\": {" +
                        "\"response\": {" +
                            "\"id\":10," +
                            "\"content\": {" +
                                "\"message\": \"my message\"" +
                            "}" +
                        "}" +
                    "}," +
                    "\"USA\": {" +
                        "\"states\": [" +
                            "\"Alabama\", \"Alaska\", \"Arizona\", \"Arkansas\"" +
                        "]" +
                    "}," +
                    "\"Brazil\": {" +
                        "\"states\": [" +
                            "{" +
                                "\"acronym\": \"SP\"," +
                                "\"name\": \"São Paulo\"" +
                            "}," +
                            "{" +
                                "\"acronym\": \"BA\"," +
                                "\"name\": \"São Paulo\"" +
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
    );

    println(jo)
    println(jo.toString())
    println(jo.get("city"))
    println(jo.get("name"))
    println(jo.get("age"))
    println(jo.get("data"))
    println(jo.getJSONObject("data").get("response"))
    println(jo.getJSONObject("data").getJSONObject("response").get("id"))
    println(jo.getJSONObject("data").getJSONObject("response").getInt("id"))
    println(jo.getJSONObject("data").getJSONObject("response").get("content"))
    println(jo.getJSONObject("data").getJSONObject("response").getJSONObject("content").get("message"))
    println(jo.getJSONObject("USA"))
    println(jo.getJSONObject("USA").getJSONArray("states"))
    println(jo.getJSONObject("Brazil"))
    println(jo.getJSONObject("Brazil").getJSONArray("states"))

    val response: JSONObject = jo.getJSONObject("data").get("response") as JSONObject
    println(response.toString())

    val cityAny: Any = jo.get("city")
    println(cityAny)
    val cityString: String = jo.getString("city")
    println(cityString)
    //val itDoesNotExist: String = jo.getString("itDoesNotExist") //throws org.json.JSONException

    //iterate over JSONArray items
    val usaJsonArray: JSONArray = jo.getJSONObject("USA").getJSONArray("states")
    for (index in 0 until usaJsonArray.length()) {
        val state: String = usaJsonArray.getString(index)
        println(state)
    }

    val brazilJsonArray: JSONArray = jo.getJSONObject("Brazil").getJSONArray("states")
    for (index in 0 until brazilJsonArray.length()) {
        val state: JSONObject = brazilJsonArray.getJSONObject(index)
        println(state)
        println(state.getString("acronym"))
        println(state.getString("name"))
    }
}