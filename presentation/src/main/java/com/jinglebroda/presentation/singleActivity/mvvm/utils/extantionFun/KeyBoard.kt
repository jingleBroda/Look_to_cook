package com.jinglebroda.presentation.singleActivity.mvvm.utils.extantionFun

import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.closeKeyBoard(windowToken: IBinder){
    //скрываем клавиатуру
    val imm2 =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm2.hideSoftInputFromWindow(windowToken, 0)
}