package com.joeroble.android.surveyapp_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ResultFragment : Fragment() {


    // Pre-initialization for the button and textviews.
    private lateinit var resetCountersButton: Button
    private lateinit var noCounterText: TextView
    private lateinit var yesCounterText: TextView


    // Establishes the surveyViewModel to interact with it, and pull data.
    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Links up the button and textviews with variables.
        resetCountersButton = view.findViewById(R.id.reset_button)
        noCounterText = view.findViewById(R.id.no_counter)
        yesCounterText = view.findViewById(R.id.yes_counter)


        // pulls in the data from the surveyViewModel to populate the counts.
        surveyViewModel.yesCount.observe(requireActivity()){
            yes -> updateYesCounters(yes)
        }

        surveyViewModel.noCount.observe(requireActivity()){
            no -> updateNoCounters(no)
        }

        // Calls on resetCounters to reset the counter totals.
        resetCountersButton.setOnClickListener{
            resetCounters()
        }
        // Calls on finish and moves back to main activity while changing nothing.

        return view
    }

    // Will reset the surveyViewModel to 0
    private fun resetCounters(){
        surveyViewModel.yesCount.value = 0
        surveyViewModel.noCount.value = 0

    }



    // Updates the counts by taking the value provide from surveyViewModel.
    private fun updateYesCounters(yesCounter: Int){
        yesCounterText.text = yesCounter.toString()
    }
    private fun updateNoCounters(noCounter: Int){
        noCounterText.text = noCounter.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        @JvmStatic
        fun newInstance() = ResultFragment()
            }
    }
