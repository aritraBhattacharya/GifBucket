package com.aritra.gifbucket.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aritra.gifbucket.data.local.entities.GifResponseEntity
import com.aritra.gifbucket.data.remote.dtos.User
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GifDataBaseTest {
    private lateinit var gifDao: GifDao
    private lateinit var db: GifDataBase

    @Before
    public fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, GifDataBase::class.java).allowMainThreadQueries().build()
        gifDao = db.getGifDao()
    }

    @Test
    public fun db_insertion_works() = runBlocking {
        val gifdata = getGifsToSave()
        gifDao.insertGif(gifdata)
        val list = gifDao.getAllGifs()
        Truth.assertThat(list).isNotEmpty()
    }


    @Test
    public fun db_insertion_works_i() {

        Truth.assertThat("yy").contains("y")

    }

    @After
    public fun tearDown() {
        db.close()
    }

    fun getGifsToSave(): GifResponseEntity {
        val user = User(
            "", "", "", "", "", "", true, "", "", "")

        return GifResponseEntity(
            "sdhgjf",
            "ejhfjwe",
            "hdsfjw",
            "djjg",
            "dkjbg",
            "12345",
            "af",
            1,
            "5",
            "AA",
            "AAA",
            "AAAA",
            "AAA",
            "my gif",
            "wefef",
            "ewfwef",
            "wefwef",
            user,
            "username")
    }
}