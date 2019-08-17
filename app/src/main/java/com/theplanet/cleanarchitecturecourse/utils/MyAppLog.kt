package com.theplanet.cleanarchitecturecourse.utils

import android.util.Log

object MyAppLog {
    fun log(tag:String="MyAppLogger",message:String){
        Log.wtf(tag,message)
    }
}