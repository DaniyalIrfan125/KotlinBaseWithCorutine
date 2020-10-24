package com.daniyalirfan.kotlinbasewithcorutine.data.local.sharedpereference

import android.content.Context
import android.content.SharedPreferences
import com.daniyalirfan.kotlinbasewithcorutine.constants.AppConstants

class SharedPreferenceProvider(val context :Context) {

    private fun getPreferenceProvider(): SharedPreferences {
        return context.getSharedPreferences(AppConstants.SharedPreference.SHARED_PREFERENCE_NAME, 0)
    }

    fun saveLocalization(value: String?) {
        getPreferenceProvider().edit().putString(AppConstants.SharedPreference.LOCALIZATION_KEY_NAME, value).apply()
    }

    fun getLocalizationValue(): String? {
        return getPreferenceProvider().getString(AppConstants.SharedPreference.LOCALIZATION_KEY_NAME, "en")
    }

}