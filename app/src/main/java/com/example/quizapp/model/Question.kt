package com.example.quizapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    @SerializedName("answer")
    val answer: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("optionfour")
    val optionfour: String,
    @SerializedName("optionone")
    val optionone: String,
    @SerializedName("optionthree")
    val optionthree: String,
    @SerializedName("optiontwo")
    val optiontwo: String,
    @SerializedName("question")
    val question: String
) : Serializable
