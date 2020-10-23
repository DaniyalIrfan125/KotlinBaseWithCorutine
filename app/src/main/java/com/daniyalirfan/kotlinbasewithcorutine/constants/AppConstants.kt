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

}