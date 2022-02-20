package com.joeroble.android.surveyapp_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.absoluteValue

// const tag to use for tracking fragments
const val SURVEY_COUNT_FRAGMENT = "com.joeroble.android.surveyapp_fragments.survey"
/**
 * A simple [Fragment] subclass.
 * Use the [SurveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyFragment : Fragment() {
    // Establishes the components of the UI, uses lateinit var as they will be initialized in onCreate
    private lateinit var yesCountButton: Button
    private lateinit var noCountButton: Button
    private lateinit var noCounterText: TextView
    private lateinit var yesCounterText: TextView
    private lateinit var surveyResultsButton: Button

    // Establishes the view model that will be used to store the counts
    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SurveyViewModel::class.java)
    }

    // variables used to track the counts, which are passed to surveyViewModel.
    var yesCount = 0
    var noCount = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_survey, container, false)




        // Initializes the buttons and text views
        yesCountButton = view.findViewById(R.id.yes_button)
        noCountButton = view.findViewById(R.id.no_button)
        noCounterText = view.findViewById(R.id.no_counter)
        yesCounterText = view.findViewById(R.id.yes_counter)
        surveyResultsButton = view.findViewById(R.id.result_button)


        // Sets up the on click listeners for the buttons, which call the respective functions
        // for each button(increase the yes or no counts, and Results).  It will
        // update the counters at the end

        // Replaces the reset button with a Results button, this will call on the ResultFragment,


        yesCountButton.setOnClickListener{
            increaseYesCount()
        }

        noCountButton.setOnClickListener{
            increaseNoCount()
        }

        // Passes the data from the surveyViewModel  to the updateYes/NoCounters to update the textviews
        surveyViewModel.yesCount.observe(requireActivity()){
            yes -> updateYesCounters(yes)
        }

        surveyViewModel.noCount.observe(requireActivity()){
            no -> updateNoCounters(no)
        }


       surveyResultsButton.setOnClickListener{
           sendResults()
       }

        return view
    }

    // Creates the increaseYesCount() function, which will increase the yesCount by one, and
    // pass that value to surveyViewModel.
    private fun increaseYesCount(){
        yesCount += 1
        surveyViewModel.yesCount.value = yesCount
    }

    //Creates the increaseNoCount() function, which will increase the noCount by one, and
    //pass that value to surveyViewModel.
    private fun increaseNoCount(){
        noCount += 1
        surveyViewModel.noCount.value = noCount
    }
    //Updates the counters with the current counts stored in SurveyViewModel, it takes the values
    // provide by the surveyViewModel, and changes them to a string for display.
    private fun updateYesCounters(yesCounter: Int){
        yesCounterText.text = yesCounter.toString()
    }
    private fun updateNoCounters(noCounter: Int){
       noCounterText.text = noCounter.toString()
    }

    // sends the results, and calls on the other fragment.
    private fun sendResults(){

        surveyViewModel.yesCount.value = yesCount
        surveyViewModel.noCount.value  = noCount
        parentFragmentManager.setFragmentResult(SURVEY_COUNT_FRAGMENT, Bundle.EMPTY)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */

        @JvmStatic
        fun newInstance() = SurveyFragment()
            }
    }
