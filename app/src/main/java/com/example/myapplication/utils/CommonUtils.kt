package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.util.*

fun changeDateFormat(timeStamp:Long,outputPattern:String):String{
    val outputDateFormat = SimpleDateFormat(outputPattern)
    return outputDateFormat.format(Date(timeStamp))
}