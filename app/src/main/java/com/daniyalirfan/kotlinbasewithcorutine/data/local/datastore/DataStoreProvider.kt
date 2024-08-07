package com.daniyalirfan.kotlinbasewithcorutine.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.daniyalirfan.kotlinbasewithcorutine.constants.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreProvider @Inject constructor(@ApplicationContext context: Context) {

    //Create the dataStore
    private val Context.userPreferencesDataStore by preferencesDataStore(
        name = AppConstants.DataStore.DATA_STORE_NAME
    )

    val dataStore = context.userPreferencesDataStore


    //Create some keys
    companion object {
        val IS_LOCALIZATION_KEY = booleanPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = stringPreferencesKey(AppConstants.DataStore.USER_NAME_KEY)

    }

    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String) {
        dataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
        }
    }

    //Create an Localization flow
    val localizationFlow: Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

    //Create a name flow
    val userNameFlow: Flow<String> = context.userPreferencesDataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }


}