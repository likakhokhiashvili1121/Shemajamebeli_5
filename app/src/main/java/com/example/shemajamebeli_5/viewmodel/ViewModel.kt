package com.example.shemajamebeli_5.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shemajamebeli_5.model.DataFields
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class ViewModel : ViewModel() {

    private val _likusFlow = MutableSharedFlow<DataFields>()
    val likusFlow: SharedFlow<DataFields> get() = _likusFlow

    suspend fun fields(){
        val json= "" +
                "[ \n" +
                "[ \n" +
                "{ \n" +
                "\"field_id\":1, \n" +
                "\"hint\":\"UserName\", \n" +
                "\"field_type\":\"input\", \n" +
                "\"keyboard\":\"text\", \n" +
                "\"required\":false, \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" \n" +
                "}, \n" +
                "{ \n" +
                "\"field_id\":2, \n" +
                "\"hint\":\"Email\", \n" +
                "\"field_type\":\"input\", \n" +
                "\"required\":true, \n" +
                "\"keyboard\":\"text\", \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" \n" +
                "}, \n" +
                "{ \n" +
                "\"field_id\":3, \n" +
                "\"hint\":\"phone\", \n" +
                "\"field_type\":\"input\", \n" +
                "\"required\":true, \n" +
                "\"keyboard\":\"number\", \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" \n" +
                "} \n" +
                "], \n" +
                "[ \n" +
                "{ \n" +
                "\"field_id\":4,\n" +
                "\"hint\":\"FullName\", \n" +
                "\"field_type\":\"input\", \n" +
                "\"keyboard\":\"text\", \n" +
                "\"required\":true, \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" }, \n" +
                "{ \n" +
                "\"field_id\":14, \n" +
                "\"hint\":\"Jemali\", \n" +
                "\"field_type\":\"input\", \n" +
                "\"keyboard\":\"text\", \n" +
                "\"required\":false, \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" }, \n" +
                "{ \n" +
                "\"field_id\":89, \n" +
                "\"hint\":\"Birthday\", \n" +
                "\"field_type\":\"chooser\", \n" +
                "\"required\":false, \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" }, \n" +
                "{ \n" +
                "\"field_id\":898, \n" +
                "\"hint\":\"Gender\", \n" +
                "\"field_type\":\"chooser\", \n" +
                "\"required\":\"false\", \n" +
                "\"is_active\":true, \n" +
                "\"icon\":\"https://jemala.png\" } \n" +
                "] \n" +
                "]\n"

        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<DataFields> = moshi.adapter(DataFields::class.java)

        val response = jsonAdapter.fromJson(json)
        if (response != null) {
            _likusFlow.emit(response)
        }

    }
}