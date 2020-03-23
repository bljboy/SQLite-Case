package com.example.sqlitecase

import org.jetbrains.anko.db.RowParser

interface MyRowParser<T> {
    fun parseRow(columns: Array<Any>): T
}