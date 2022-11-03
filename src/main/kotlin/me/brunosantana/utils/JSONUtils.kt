package me.brunosantana.utils

import org.json.JSONObject

fun JSONObject.getItem1LevelDeep(itemName: String, firstLevelName: String): String {
    return this.getJSONObject(firstLevelName).getString(itemName)
}

fun JSONObject.getItem2LevelsDeep(itemName: String, firstLevelName: String, secondLevelName: String): String {
    return this.getJSONObject(firstLevelName).getJSONObject(secondLevelName).getString(itemName)
}

fun JSONObject.getItemAnyLevelDeep(jsonObject: JSONObject, itemName: String, vararg levelNames: String): String {

    return if(levelNames.isEmpty()){
        jsonObject.getString(itemName)
    }else{
        val levelName = levelNames[0]
        val nextJsonObject = jsonObject.getJSONObject(levelName)

        var updatedLevelNames = arrayOf<String>()
        levelNames.forEachIndexed { index, element ->
            if(index > 0){
                updatedLevelNames = updatedLevelNames.plus(element)
            }
        }

        getItemAnyLevelDeep(nextJsonObject, itemName, *updatedLevelNames)
    }
}