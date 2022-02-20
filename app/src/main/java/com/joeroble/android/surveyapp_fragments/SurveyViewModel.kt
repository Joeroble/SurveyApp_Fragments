package com.joeroble.android.surveyapp_fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// SurveyViewModel is used to store the counts across fragments, uses MutableLiveData.
class SurveyViewModel: ViewModel() {

    var yesCount = MutableLiveData(0)
    var noCount = MutableLiveData(0)



}