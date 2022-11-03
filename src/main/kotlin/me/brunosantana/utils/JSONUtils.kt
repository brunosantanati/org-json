package me.brunosantana.utils

import org.json.JSONObject

fun getItemAnyLevelDeep(jsonObject: JSONObject, vararg levelNames: String): String {

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