package com.aritra.gifbucket.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aritra.gifbucket.BuildConfig

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value =  BuildConfig.API_KEY;
    }
    val text: LiveData<String> = _text
}