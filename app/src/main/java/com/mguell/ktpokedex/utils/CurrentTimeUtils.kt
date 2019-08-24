package com.mguell.ktpokedex.utils

import java.text.SimpleDateFormat
import java.util.*

object CurrentTimeUtils {

    private const val CATCHED_TIME_FORMAT = "dd/MM/yyyy hh:mm"

    /**
     * Returns the current time in dd/MM/yyyy hh:mm format
     *
     * @return String with the current time formatted as catched time
     */
    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat(CATCHED_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date())
    }
}