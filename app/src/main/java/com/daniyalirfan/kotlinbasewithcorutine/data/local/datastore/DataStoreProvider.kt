package com.daniyalirfan.kotlinbasewithcorutine.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.daniyalirfan.kotlinbasewithcorutine.constants.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(context: Context) {

    //Create the dataStore
    private val dataStore = context.createDataStore(name = AppConstants.DataStore.DATA_STORE_NAME)

    //Create some keys
    companion object {
        val IS_LOCALIZATION_KEY = preferencesKey<Boolean>(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = preferencesKey<String>(AppConstants.DataStore.USER_NAME_KEY)

    }

    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String) {
        dataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
        }
    }

    //Create an Localization flow
    val localizationFlow: Flow<Boolean> = dataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

    //Create a name flow
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }


}