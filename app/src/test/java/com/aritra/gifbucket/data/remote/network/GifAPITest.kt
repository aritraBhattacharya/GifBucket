package com.aritra.gifbucket.data.remote.network

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GifAPITest {
    private lateinit var gifAPI: GifAPI
    private lateinit var mockWebServer: MockWebServer

/*    Create shared preference for unit testing
    val mMockSharedPreferences = mContext.getSharedPreferences(GenericConstants.SHARED_PREF_FILENAME, Context.MODE_PRIVATE)
    // getting context and sharedPreference instance
    testContext = ApplicationProvider.getApplicationContext()*/

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        gifAPI = Retrofit.Builder().baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create()).build().create(GifAPI::class.java)

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun enqueueMockResponse(fineName: String, responseCode: Int) {
        // this enqueues fake responses in mockweb server and they are returned by mock web server in that order
        val inputStream =
            javaClass.classLoader!!.getResourceAsStream(fineName) // to read the json from the fake response json file, as mock server cant read on its own
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockResponse.setResponseCode(responseCode)
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun getSearchedGif_search_result_received() {
        runTest {
            enqueueMockResponse("SearchResponse.json", 201)
            val responseBody = gifAPI.getSearchedGif(q = "beach").body()
            //val request = mockWebServer.takeRequest()
            assertThat(responseBody).isNotNull()
        }
    }

}