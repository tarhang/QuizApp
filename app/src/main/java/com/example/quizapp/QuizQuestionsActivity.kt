package com.example.quizapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        this.displayNewQuestion()

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

    private fun displayNewQuestion() {
        val question = this.questionsList!![currentQuestion - 1]

        // updating the activity with details of the question
        this.changeOptionsToDefault()
        this.selectedAnswer = 0
        progressBar.progress = this.currentQuestion
        tvProgressBar.text = "${this.currentQuestion} / ${this.questionsList!!.size}"
        tvQuestion.text = question.question
        ivImage.setImageResource(question.image)
        tvOption1.text = question.optionOne
        tvOption2.text = question.optionTwo
        tvOption3.text = question.optionThree
        tvOption4.text = question.optionFour

        if (this.currentQuestion < this.questionsList!!.size) {
            btnSubmit.text = getString(R.string.submit)
        } else {
            btnSubmit.text = getString(R.string.finish)
        }

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
        btnSubmit.text = getString(R.string.submit)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.setTextColor(ContextCompat.getColor(this, R.color.textColour1))
    }

    private fun changeOptionToAnswer(tv: TextView, style: Int) {
        tv.background = ContextCompat.getDrawable(this, style)
    }

    override fun onClick(v: View?) {
        if (v?.id == tvOption1.id) {
            this.changeOptionToSelected(tvOption1, 1)
        } else if (v?.id == tvOption2.id) {
            this.changeOptionToSelected(tvOption2, 2)
        } else if (v?.id == tvOption3.id) {
            this.changeOptionToSelected(tvOption3, 3)
        } else if (v?.id == tvOption4.id) {
            this.changeOptionToSelected(tvOption4, 4)
        } else if (v?.id == btnSubmit.id) {
            if (this.selectedAnswer != 0) {
                // revealing the answer
                val correctAnswer = this.questionsList!![currentQuestion - 1].correctAnswer
                val tvSelected = this.options[selectedAnswer - 1]
                val tvCorrect = this.options[correctAnswer - 1]
                this.selectedAnswer = 0

                if (this.selectedAnswer != correctAnswer) {
                    this.changeOptionToAnswer(tvSelected, R.drawable.incorrect_option)
                }
                this.changeOptionToAnswer(tvCorrect, R.drawable.correct_option)
                if (this.currentQuestion < this.questionsList!!.size) {
                    btnSubmit.text = getString(R.string.nextQ)
                }
                else {
                    btnSubmit.text = getString(R.string.finish)
                }
            }
            else {
                if (this.currentQuestion < this.questionsList!!.size) {
                    this.currentQuestion += 1
                    this.displayNewQuestion()
                } else {
                    Toast.makeText(this, "Quiz Finished!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
