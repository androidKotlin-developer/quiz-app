package com.example.quizapp.repository

import com.example.quizapp.network.ApiService

class QuestionsRepository(private val apiService: ApiService) {
     fun getQuestions() = apiService.getQuizzes()

}

