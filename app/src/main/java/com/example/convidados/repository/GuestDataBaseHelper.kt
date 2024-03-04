
//Esta classe é responsável pela conexão com o banco de dados.

package com.example.convidados.repository

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBaseHelper: RoomDatabase(){
    companion object{
        private lateinit var INSTANCE: GuestDataBaseHelper
        fun getDataBase(context: Context): GuestDataBaseHelper{
            if(!::INSTANCE.isInitialized){
                synchronized(GuestDataBaseHelper::class) {
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDataBaseHelper::class.java, "guestdb")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
        private val MIGRATION_1_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }

}