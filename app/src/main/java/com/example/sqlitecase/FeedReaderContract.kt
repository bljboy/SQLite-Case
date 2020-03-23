package com.example.sqlitecase

import android.provider.BaseColumns

object FeedReaderContract {
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "Info"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_Name = "name"
    }
}