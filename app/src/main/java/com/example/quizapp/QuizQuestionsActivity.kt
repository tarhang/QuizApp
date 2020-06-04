package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionsList: ArrayList<Question>? = null
    private var currentQuestion = 1
    private var selectedAnswer = 0
    private val options = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        this.questionsList = Constants.getQuestions()
        this.displayQuestion()

        // adding text views to the list of text views (options to select)
        this.options.add(tvOption1)
        this.options.add(tvOption2)
        this.options.add(tvOption3)
        this.options.add(tvOption4)

        // adding onClickListern functionality to text views (options to select) and the button
        for (tv in this.options) {
            tv.setOnClickListener(this)
        }
        btnSubmit.setOnClickListener(this)
    }

    private fun displayQuestion() {
        val question = this.questionsList!![currentQuestion - 1]
        this.changeOptionsToDefault()

        // updating the activity with details of the question
        progressBar.progress = this.currentQuestion
        tvProgressBar.text = "${this.currentQuestion} / ${this.questionsList!!.size}"
        tvQuestion.text = question.question
        ivImage.setImageResource(question.image)
        tvOption1.text = question.optionOne
        tvOption2.text = question.optionTwo
        tvOption3.text = question.optionThree
        tvOption4.text = question.optionFour

        this.changeOptionsToDefault()
    }

    private fun changeOptionsToDefault() {
        for (option in this.options) {
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option)
            option.typeface = Typeface.DEFAULT
            option.setTextColor(ContextCompat.getColor(this, R.color.textColour2))
        }
    }

    private fun changeOptionToSelected(tv: TextView, optionNumber: Int) {
        this.changeOptionsToDefault()
        this.selectedAnswer = optionNumber
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.setTextColor(ContextCompat.getColor(this, R.color.textColour1))
    }

    private fun changeOptionToCorrect(tv: TextView) {

    }

    private fun changeOptionToIncorrect(tv: TextView) {

    }

    override fun onClick(v: View?) {
        if (v?.id == tvOption1.id) {
            this.changeOptionToSelected(tvOption1, 1)
        }
        else if (v?.id == tvOption2.id) {
            this.changeOptionToSelected(tvOption2, 2)
        }
        else if (v?.id == tvOption3.id) {
            this.changeOptionToSelected(tvOption3, 3)
        }
        else if (v?.id == tvOption4.id) {
            this.changeOptionToSelected(tvOption4, 4)
        }
        else if (v?.id == btnSubmit.id) {

        }
    }
}
