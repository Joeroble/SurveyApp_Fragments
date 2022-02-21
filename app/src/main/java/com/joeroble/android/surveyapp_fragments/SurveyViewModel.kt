package com.joeroble.android.surveyapp_fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// SurveyViewModel is used to store the counts across fragments, uses MutableLiveData.
class SurveyViewModel: ViewModel() {

    val yesCount = MutableLiveData(0)
    val noCount = MutableLiveData(0)

//    val yesCount: LiveData<Int> get() = mutableYesCount
//    val noCount: LiveData<Int> get() = mutableNoCount




}