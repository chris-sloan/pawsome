package com.chrissloan.paw_some.data.utils

interface TimeUtils {
    fun currentTime(): Long

    fun minutesMs(number: Long): Long
}
