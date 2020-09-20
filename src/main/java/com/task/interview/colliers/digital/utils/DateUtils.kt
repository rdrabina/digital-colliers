package com.task.interview.colliers.digital.utils

import com.task.interview.colliers.digital.exception.InvalidDateParametersException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @JvmStatic
    fun createDateWithFormat(date: String, format: String): Date {
        val simpleDateFormat = SimpleDateFormat(format)
        return try {
            simpleDateFormat.parse(date)
        } catch (e: ParseException) {
            throw InvalidDateParametersException()
        }
    }

}