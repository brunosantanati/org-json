package me.brunosantana.utils

import me.brunosantana.enums.ReturnType
import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.getItem1LevelDeep(itemName: String, firstLevelName: String): String {
    return this.getJSONObject(firstLevelName).getString(itemName)
}

fun JSONObject.getItem2LevelsDeep(itemName: String, firstLevelName: String, secondLevelName: String): String {
    return this.getJSONObject(firstLevelName).getJSONObject(secondLevelName).getString(itemName)
}

fun JSONObject.getStringAnyLevelDeep(
    jsonObject: JSONObject,
    itemName: String,
    vararg levelNames: String
): String {
    return getItemAnyLevelDeep(jsonObject, itemName, null, null, ReturnType.STRING, *levelNames) as String
}

fun JSONObject.getJSONArrayAnyLevelDeep(
    jsonObject: JSONObject,
    itemName: String,
    vararg levelNames: String
): JSONArray {
    return getItemAnyLevelDeep(jsonObject, itemName, null, null, ReturnType.JSON_ARRAY, *levelNames) as JSONArray
}

fun JSONObject.getListAnyLevelDeep(
    jsonObject: JSONObject,
    itemName: String,
    itemToBuildList: String,
    vararg levelNames: String
): List<String> {
    return getItemAnyLevelDeep(jsonObject, itemName, itemToBuildList, null, ReturnType.LIST_STRING, *levelNames) as List<String>
}

fun JSONObject.getMapAnyLevelDeep(
    jsonObject: JSONObject,
    itemName: String,
    itemsToBuildMap: Map<String, String>,
    vararg levelNames: String
): Map<String, String> {
    return getItemAnyLevelDeep(jsonObject, itemName, null, itemsToBuildMap, ReturnType.MAP_STRING_STRING, *levelNames) as Map<String, String>
}

private fun getItemAnyLevelDeep(
    jsonObject: JSONObject,
    itemName: String,
    itemToBuildList: String?,
    itemsToBuildMap: Map<String, String>?,
    returnType: ReturnType,
    vararg levelNames: String
): Any {

    return if(levelNames.isEmpty()){
        when(returnType) {
            ReturnType.STRING -> jsonObject.getString(itemName)
            ReturnType.JSON_ARRAY -> jsonObject.getJSONArray(itemName)
            ReturnType.LIST_STRING -> buildList(jsonObject.getJSONArray(itemName), itemToBuildList)
            ReturnType.MAP_STRING_STRING -> buildMap(jsonObject.getJSONArray(itemName), itemsToBuildMap)
            else -> "Invalid return type"
        }
    }else{
        val levelName = levelNames[0]
        val nextJsonObject = jsonObject.getJSONObject(levelName)

        var updatedLevelNames = arrayOf<String>()
        levelNames.forEachIndexed { index, element ->
            if(index > 0){
                updatedLevelNames = updatedLevelNames.plus(element)
            }
        }

        getItemAnyLevelDeep(nextJsonObject, itemName, itemToBuildList, itemsToBuildMap, returnType, *updatedLevelNames)
    }
}

fun buildList(jsonArray: JSONArray, itemToBuildList: String?): List<String>{
    val list = mutableListOf<String>()
    for (index in 0 until jsonArray.length()) {
        val arrayItem: JSONObject = jsonArray.getJSONObject(index)
        list.add(arrayItem.getString(itemToBuildList))
    }
    return list
}

fun buildMap(jsonArray: JSONArray, itemsToBuildMap: Map<String, String>?): Map<String, String>{
    val map = mutableMapOf<String, String>()
    for (index in 0 until jsonArray.length()) {
        val arrayItem: JSONObject = jsonArray.getJSONObject(index)
        map[arrayItem.getString(itemsToBuildMap?.get("key"))] = arrayItem.getString(itemsToBuildMap?.get("value"))
    }
    return map
}