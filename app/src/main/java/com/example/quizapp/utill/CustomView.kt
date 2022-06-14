package com.example.quizapp.utill
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View

import androidx.appcompat.app.AlertDialog
import com.example.quizapp.R
import com.example.quizapp.callback.ISC_AlertClickPositiveOnly
import com.example.quizapp.databinding.CustomAlertSuccessBinding


object CustomView {
    @SuppressLint("UseCompatLoadingForDrawables")
    fun alertDialogToast(
        context: Context,
        title: String?,
        type: Int,
        callback: ISC_AlertClickPositiveOnly
    ) {
        val binding : CustomAlertSuccessBinding = CustomAlertSuccessBinding.inflate(LayoutInflater.from(context))
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val alertView: View = binding.root

        if (type == Constant.SuccessAlertDialog) {
            binding.customAlertToastLlHeader.background = context.resources.getDrawable(R.drawable.ring_primary,null)
            binding.customAlertToastHeaderIvImgView.setImageDrawable(
                context.resources.getDrawable(R.drawable.ic_up, null)
            )
            binding.customAlertToastBodyTvTitle.text = Uitll.nullChecker(title)
            binding.customAlertToastBodyBtnBtn1.background = context.resources.getDrawable(R.drawable.round_corner_primary,null)
            binding.customAlertToastBodyBtnBtn1.text = "OK"
            binding.customAlertToastBodyBtnBtn2.visibility = View.GONE
        } else if (type == Constant.FailedAlertDialog) {
            binding.customAlertToastLlHeader.background = context.resources.getDrawable(R.drawable.ring_red,null)
            binding.customAlertToastHeaderIvImgView.setImageDrawable(
                context.resources.getDrawable(R.drawable.ic_down,null)
            )
            binding.customAlertToastBodyTvTitle.text = Uitll.nullChecker(title)
            binding.customAlertToastBodyBtnBtn1.background = context.resources.getDrawable(R.drawable.round_ripple_red,null)
            binding.customAlertToastBodyBtnBtn1.text = "OK"
            binding.customAlertToastBodyBtnBtn2.visibility = View.GONE
        } else if (type == Constant.CompleteAlertDialog) {
            binding.customAlertToastLlHeader.background = context.resources.getDrawable(R.drawable.ring_red,null)
            binding.customAlertToastHeaderIvImgView.setImageDrawable(
                context.resources.getDrawable(R.drawable.ic_down,null)
            )
            binding.customAlertToastBodyTvTitle.text = Uitll.nullChecker(title)
            binding.customAlertToastBodyBtnBtn1.background = context.resources.getDrawable(R.drawable.bg_view,null)
            binding.customAlertToastBodyBtnBtn1.text = "OK"
            binding.customAlertToastBodyBtnBtn2.visibility = View.GONE
        }else {
            return
        }
        builder.setView(alertView)
        builder.setCancelable(false)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.window!!.setBackgroundDrawableResource(R.color.transperent)
        alertDialog.show()
        binding.customAlertToastBodyBtnBtn1.setOnClickListener { v ->
            alertDialog.dismiss()
            callback.onPositiveButtonClicked(v)
        }
    }
}


