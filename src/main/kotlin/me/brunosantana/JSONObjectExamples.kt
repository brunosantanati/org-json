package me.brunosantana

import org.json.JSONObject

fun main() {
    //https://www.baeldung.com/java-org-json
    //https://github.com/eugenp/tutorials/blob/master/json-modules/json/src/test/java/com/baeldung/jsonjava/JSONObjectIntegrationTest.java

    val jo = JSONObject(
        "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
    );

    println(jo)
    println(jo.toString())
    println(jo.get("city"))
    println(jo.get("name"))
    println(jo.get("age"))
}