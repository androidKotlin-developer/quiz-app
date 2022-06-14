package com.example.quizapp.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.quizapp.R
import com.example.quizapp.callback.ISC_AlertClickPositiveOnly
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.model.Question
import com.example.quizapp.modelfactory.QuestionViewModelFactory
import com.example.quizapp.network.ApiService
import com.example.quizapp.repository.QuestionsRepository
import com.example.quizapp.utill.Constant
import com.example.quizapp.utill.CustomView
import com.example.quizapp.utill.MySharedPref
import com.example.quizapp.viewmodel.QuestionsViewModel
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewModel: QuestionsViewModel
    private val mySharedPref = MySharedPref("myPref", Context.MODE_PRIVATE, this)
    lateinit var binding: ActivityMainBinding
    var mPosition = 1
    var mSelectedPosition = 0
    private var question: List<Question>? = null
    var mCorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = ApiService.getInstance(this)
        viewModel =
            ViewModelProvider(this, QuestionViewModelFactory(QuestionsRepository(retrofit!!)))
                .get(QuestionsViewModel::class.java)

        viewModel.getQuestionsData.observe(this) {
            mySharedPref.putList(Constant.DATA_KEY, it)
        }
        viewModel.getQuestions()

        val handler = Handler()
        val runnable = Runnable(){
            setQuestions()
        }
        handler.postDelayed(runnable, 3000)


        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestions() {
        val type: Type = object : TypeToken<List<Question>>() {}.type
        question = mySharedPref.getList(Constant.DATA_KEY, null, type)
        val question1 = question!![mPosition - 1]
        binding.pbProgress.visibility = View.GONE
        binding.tvQuestion.text = question1.question
        Glide.with(this).load(question1.img).into(binding.ivHint)
        binding.tvOptionOne.text = question1.optionone
        binding.tvOptionTwo.text = question1.optiontwo
        binding.tvOptionThree.text = question1.optionthree
        binding.tvOptionFour.text = question1.optionfour
        binding.pbQuizProgress.progress = mPosition
        binding.tvProgress.text = "$mPosition / 10"

    }

    override fun onDestroy() {
        super.onDestroy()
        mySharedPref.deleteExtra(Constant.DATA_KEY)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.tvOptionOne.id -> {
                checkAnswer(1, binding.tvOptionOne, 1)
            }
            binding.tvOptionTwo.id -> {
                checkAnswer(2, binding.tvOptionTwo, 2)
            }
            binding.tvOptionThree.id -> {
                checkAnswer(3,binding.tvOptionThree,3)
            }
            binding.tvOptionFour.id -> {
                checkAnswer(4,binding.tvOptionFour,4)
            }
        }
    }

    private fun defaultOptionView() {
        val option = ArrayList<TextView>()
        binding.tvOptionOne.let {
            option.add(0, it)
        }
        binding.tvOptionTwo.let {
            option.add(1, it)
        }
        binding.tvOptionThree.let {
            option.add(2, it)
        }
        binding.tvOptionFour.let {
            option.add(3, it)
        }
        for (i in option) {
            i.setTextColor(Color.parseColor("#7A8089"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(this, R.drawable.bg_option)
        }
    }

    private fun selectedOption(textView: TextView, position: Int) {
        defaultOptionView()
        mSelectedPosition = position
        textView.setTextColor(Color.parseColor("#7A8089"))
        textView.setTypeface(textView.typeface, Typeface.NORMAL)
        textView.background = ContextCompat.getDrawable(this, R.drawable.bg_option)
    }

    private fun answerView(textView: TextView, answer: Int, drawableView: Int, position: Int) {
        when (answer) {
            1 -> {
                defaultOptionView()
                mSelectedPosition = position
                textView.setTextColor(Color.parseColor("#363A43"))
                textView.setTypeface(textView.typeface, Typeface.BOLD)
                textView.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                defaultOptionView()
                mSelectedPosition = position
                textView.setTextColor(Color.parseColor("#363A43"))
                textView.setTypeface(textView.typeface, Typeface.BOLD)
                textView.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                defaultOptionView()
                mSelectedPosition = position
                textView.setTextColor(Color.parseColor("#363A43"))
                textView.setTypeface(textView.typeface, Typeface.BOLD)
                textView.background = ContextCompat.getDrawable(this, drawableView)

            }
            4 -> {
                defaultOptionView()
                mSelectedPosition = position
                textView.setTextColor(Color.parseColor("#363A43"))
                textView.setTypeface(textView.typeface, Typeface.BOLD)
                textView.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun checkAnswer(mSelectedPosition: Int, textView: TextView, position: Int) {

            val question1 = question!![mPosition - 1]
            if (question1.answer != mSelectedPosition) {
                answerView(textView, question1.answer, R.drawable.wrong_bg_option, position)
                CustomView.alertDialogToast(this, "Wrong Answer",2,object : ISC_AlertClickPositiveOnly{
                    override fun onPositiveButtonClicked(view: View?) {
                        mPosition++
                        if (mPosition==11){
                            CustomView.alertDialogToast(this@MainActivity,"Quiz Completed \n and your final score is $mCorrectAnswers",3,object : ISC_AlertClickPositiveOnly{
                                override fun onPositiveButtonClicked(view: View?) {
                                    finish()
                                }
                            })
                        }
                        when {
                            mPosition <= question!!.size -> {
                                setQuestions()
                                selectedOption(textView, position)


                            }
                        }
                    }

                })

            }else{
                answerView(textView, question1.answer, R.drawable.correct_bg_option, position)
                CustomView.alertDialogToast(this, "Right Answer",1,object : ISC_AlertClickPositiveOnly{
                    override fun onPositiveButtonClicked(view: View?) {
                        mPosition++
                        mCorrectAnswers++
                        if (mPosition==11){
                            CustomView.alertDialogToast(this@MainActivity,"Quiz Completed \n" + " and your final score is $mCorrectAnswers",3,object : ISC_AlertClickPositiveOnly{
                                override fun onPositiveButtonClicked(view: View?) {
                                    finish()
                                }
                            })
                        }
                        when {
                            mPosition <= question!!.size -> {
                                setQuestions()
                                selectedOption(textView, position)


                            }
                        }
                    }

                })
            }
            this.mSelectedPosition = 0
        }
}


