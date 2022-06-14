package com.example.quizapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Question
import com.example.quizapp.repository.QuestionsRepository
import retrofit2.Callback

class QuestionsViewModel(private val repository: QuestionsRepository) : ViewModel() {
    val getQuestionsData = MutableLiveData<List<Question>>()
    val getError = MutableLiveData<String>()

    fun getQuestions(){
        val questions = repository.getQuestions()
        questions.enqueue(object : Callback<List<Question>>{
            override fun onFailure(call: retrofit2.Call<List<Question>>, t: Throwable) {
                getError.postValue(t.message)
            }

            override fun onResponse(call: retrofit2.Call<List<Question>>, response: retrofit2.Response<List<Question>>) {
                getQuestionsData.postValue(response.body())
            }
        })
    }
}
