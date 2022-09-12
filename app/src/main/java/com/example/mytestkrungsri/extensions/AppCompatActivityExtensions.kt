package com.example.mytestkrungsri.extensions

import android.content.Context.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

inline fun AppCompatActivity.hideSoftKeyboard(){
    if(currentFocus == null){
        return
    }
    val imm = getSystemService(INPUT_METHOD_SERVICE)
    (imm as InputMethodManager).hideSoftInputFromWindow(currentFocus?.windowToken,0)
}