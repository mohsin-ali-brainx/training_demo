package com.brainx.koindemoproject.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.*
import java.lang.reflect.Modifier


fun createGSon(): Gson {
    val builder = GsonBuilder()
    builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    return builder.create()
}


fun toJson(dataObject: Any?): String {
    val builder = GsonBuilder()
    builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    val gSon = builder.create()
    return gSon.toJson(dataObject)
}

fun Any.toJson(cls: Class<*>): String? {
    return GsonBuilder().create().toJson(this, cls);
}

fun String.toObject(activityClass: Class<*>): Any? {
    return GsonBuilder().create().fromJson(this, activityClass)

}

fun Any.objectToMap(): MutableMap<String, Any> {
    return Gson().fromJson(
        toJson(this),
        MutableMap::class.java
    ) as MutableMap<String, Any>


}


fun Context.readJsonFromRaw(JsonFileId: Int): String {
    val inputStream: InputStream = resources.openRawResource(JsonFileId)
    val writer: Writer = StringWriter()
    val buffer = CharArray(1024)
    inputStream.use { inputStream ->
        val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var n: Int
        while (reader.read(buffer).also { n = it } != -1) {
            writer.write(buffer, 0, n)
        }
    }

    return writer.toString()
}

fun String.toJsonObject(): JSONObject {
    return JSONObject(this)
}




