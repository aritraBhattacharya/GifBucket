package com.aritra.gifbucket.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aritra.gifbucket.data.local.entities.GifResponseEntity
import com.aritra.gifbucket.data.local.entities.UserEntity

/*
When room.schemaLocation is set, Room will check this variable and if it is set to true, the database schema will be exported into the given folder.
exportSchema is true by default but you can disable it for databases when you don't want to keep history of versions (like an in-memory only database).
Returns:
Whether the schema should be exported to the given folder when the room.schemaLocation argument is set. Defaults to true.*/

/*exportSchema has been set to false here, in order to avoid a build warning.
In a real app, consider setting a directory for Room to use to export the schema
so you can check the current schema into your version control system.*/

@Database(
    entities = [GifResponseEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GifDataBase : RoomDatabase() {

    abstract fun getGifDao(): GifDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GifDataBase? = null

        //Migrations
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                //        "PRIMARY KEY(`id`))")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("ALTER TABLE Book ADD COLUMN pub_year INTEGER")
            }
        }

        fun getDatabase(context: Context): GifDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val roomBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    GifDataBase::class.java,
                    "gif_database"
                )

                //roomBuilder.addMigrations(MIGRATION_1_2,MIGRATION_2_3) // add your migrations
                //roomBuilder.addTypeConverter(DateTypeConverter::class) // add type converters for complex data types
                //roomBuilder.allowMainThreadQueries() // to allow queries on  main thread
                //roomBuilder.enableMultiInstanceInvalidation() // to allow queries on  main thread
                //roomBuilder.fallbackToDestructiveMigration() // in case of failed migration recreate the db
                //roomBuilder.enableMultiInstanceInvalidation() // ????
                //roomBuilder.createFromFile(File("/sk/db file path")) // create db from file path




                val instance = roomBuilder.build()


                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}