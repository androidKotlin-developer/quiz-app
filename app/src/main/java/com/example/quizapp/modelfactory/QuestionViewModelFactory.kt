package com.example.quizapp.modelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuestionsRepository
import com.example.quizapp.viewmodel.QuestionsViewModel

class QuestionViewModelFactory(private val repository: QuestionsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            QuestionsViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
