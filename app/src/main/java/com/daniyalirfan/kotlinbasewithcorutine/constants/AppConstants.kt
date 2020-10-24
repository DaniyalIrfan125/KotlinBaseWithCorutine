package com.daniyalirfan.kotlinbasewithcorutine.constants

import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.BASE_URL)
    annotation class ApiConfiguration {
        companion object {
            const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        }
    }

    @StringDef(DbConfiguration.DB_NAME)
    annotation class DbConfiguration {
        companion object {
            const val DB_NAME = "BaseProject"
        }
    }


    @StringDef(SharedPreference.SHARED_PREFERENCE_NAME,SharedPreference.LOCALIZATION_KEY_NAME)
    annotation class SharedPreference {
        companion object {
            const val SHARED_PREFERENCE_NAME = "BaseProject"
            const val LOCALIZATION_KEY_NAME = "lang"
        }
    }

}