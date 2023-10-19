package br.com.githubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserLocalModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {
        val DATABASE_NAME = "AppDatabase"
    }
}
