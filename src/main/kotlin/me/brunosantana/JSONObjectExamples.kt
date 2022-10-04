package me.brunosantana

import org.json.JSONObject

fun main() {
    //https://www.baeldung.com/java-org-json
    //https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONObjectIntegrationTest.java
    //https://stackoverflow.com/questions/14898768/how-to-access-nested-elements-of-json-object-using-getjsonarray-method

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

    val response: JSONObject = jo.getJSONObject("data").get("response") as JSONObject
    println(response.toString())

    val cityAny: Any = jo.get("city")
    println(cityAny)
    val cityString: String = jo.getString("city")
    println(cityString)
    //val itDoesNotExist: String = jo.getString("itDoesNotExist") //throws org.json.JSONException
}