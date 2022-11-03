package me.brunosantana.utils

import org.json.JSONObject

fun JSONObject.getItem1LevelDeep(firstLevelName: String, itemName: String): String {
    return this.getJSONObject(firstLevelName).getString(itemName)
}

fun JSONObject.getItem2LevelsDeep(firstLevelName: String, secondLevelName: String, itemName: String): String {
    return this.getJSONObject(firstLevelName).getJSONObject(secondLevelName).getString(itemName)
}

fun JSONObject.getItemAnyLevelDeep(jsonObject: JSONObject, vararg levelNames: String): String {

    return if(levelNames.size == 1){
        jsonObject.getString(levelNames[0])
    }else{
        val levelName = levelNames[0]
        val nextJsonObject = jsonObject.getJSONObject(levelName)

        var updatedLevelNames = arrayOf<String>()
        levelNames.forEachIndexed { index, element ->
            if(index > 0){
                updatedLevelNames = updatedLevelNames.plus(element)
            }
        }

        getItemAnyLevelDeep(nextJsonObject, *updatedLevelNames)
    }
}