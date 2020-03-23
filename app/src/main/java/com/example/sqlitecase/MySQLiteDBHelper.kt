package com.example.sqlitecase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class MySQLiteDBHelper private constructor(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "MyDatabase.db", null, 1) {

    init {

        instance = this
    }

    companion object {
        private var instance: MySQLiteDBHelper? = null
        fun getInstance(ctx: Context) = instance ?: MySQLiteDBHelper(ctx.applicationContext)
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            "Info", true,
            "_id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "name" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}

val Context.database: MySQLiteDBHelper
    get() = MySQLiteDBHelper.getInstance(this)
