package com.joeroble.android.surveyapp_fragments


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    // establishes the container and navigation view
    private lateinit var containerView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializes the ui components
        containerView = findViewById(R.id.survey_fragment_container)


        // creates on the back stack the result fragment.
        supportFragmentManager.setFragmentResultListener(SURVEY_COUNT_FRAGMENT,this){
            requestKey, bundle ->

            supportFragmentManager.beginTransaction()
                .add(R.id.survey_fragment_container, ResultFragment.newInstance())
                .addToBackStack("RESULTS")
                .commit()
        }




        }


    }








