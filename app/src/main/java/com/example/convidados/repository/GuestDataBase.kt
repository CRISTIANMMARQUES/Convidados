
//Esta classe é responsável pela conexão com o banco de dados.

package com.example.convidados.repository

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import com.example.convidados.constants.DataBaseConstants

class GuestDataBase(context: Context)
    : SQLiteOpenHelper(context, NAME, null, VERSION){

    companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE "+ DataBaseConstants.GUEST.TABLE_NAME + " (" +
                DataBaseConstants.GUEST.COLUMNS.ID  + " integer primary key autoincrement, " +
                DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}