package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // retrieving information from the inent passed from the quiz activity
        val username = intent.getStringExtra(Constants.USER_NAME)
        val score = intent.getIntExtra(Constants.SCORE, 0)
        val totalQuesions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        // displaying the results on the activity
        tvUsername.text = username
        tvScore.text = "You scored $score out of $totalQuesions"

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}