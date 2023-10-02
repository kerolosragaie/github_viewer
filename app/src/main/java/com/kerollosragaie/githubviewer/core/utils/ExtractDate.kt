package com.kerollosragaie.githubviewer.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun extractDate(dateString: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    if(dateString==null){
        return "No date!";
    }
    val date = inputFormat.parse(dateString)!!
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return outputFormat.format(date)
}