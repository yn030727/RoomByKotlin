package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//声明版本号以及包含哪些实体类(多个实体类用逗号隔开)
@Database(version = 1,entities = [User::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao():UserDao
    //编写单例模式
    companion object {
        private var instance :AppDataBase?=null
        fun getDatabase(context: Context): AppDataBase {
            //如果不为空，就直接返回
            instance ?.let{
                return it
            }
            //为空的话就构建一个AppDatabase的实例
            return Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"app_database")
                .addMigrations(MIGRATION_1_2)
                .build().apply {
                instance=this
            }
        }

        //更新Room
        val MIGRATION_1_2=object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //处理升级数据库的逻辑操作
                //例如通过SQL语言添加新数据表
            }
        }
    }
}