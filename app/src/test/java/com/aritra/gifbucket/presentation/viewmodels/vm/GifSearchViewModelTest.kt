package com.aritra.gifbucket.presentation.viewmodels.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aritra.gifbucket.data.remote.dtos.GifSearchResponse
import com.aritra.gifbucket.data.remote.dtos.Meta
import com.aritra.gifbucket.data.utils.Resource
import com.aritra.gifbucket.data.utils.Status
import com.aritra.gifbucket.domain.usecase.GifSearchUseCase
import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import testutils.CoroutineTestRule
import testutils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
class GifSearchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule =
        InstantTaskExecutorRule() //To tell junit that we want to run all the tests inside this test class one by one  we need to use InstantTaskExecutorRule

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()


    private lateinit var mockUseCase: GifSearchUseCase

    @Before
    fun setup() {
        mockUseCase = Mockito.mock(GifSearchUseCase::class.java)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_use_case_returns_successful_result(){
        runTest {
            val mockSearchSuccessfulResponse = createFakeResponse("SearchResponse.json", 201)
            `when`(mockUseCase.searchGif("beach")).thenReturn(Resource.success(mockSearchSuccessfulResponse))
            val gifSearchVM = GifSearchViewModel(mockUseCase)
            val listOfResponses = arrayListOf<Resource<GifSearchResponse>>()
            gifSearchVM.getGifSearchResult("beach").observeForever{res ->
                listOfResponses.add(res)
            }
            Truth.assertThat(listOfResponses[0].status).isEqualTo(Status.LOADING)
            Truth.assertThat(listOfResponses[1].status).isEqualTo(Status.SUCCESS)

        }
    }

    private fun createFakeResponse(fileName: String, responseCode: Int): GifSearchResponse {
        val inputStream =
            javaClass.classLoader!!.getResourceAsStream(fileName) // to read the json from the fake response json file, as mock server cant read on its own
        val source = inputStream.source().buffer()
        val mockResString = source.readString(Charsets.UTF_8)
        return Gson().fromJson(mockResString, GifSearchResponse::class.java)
    }

}
