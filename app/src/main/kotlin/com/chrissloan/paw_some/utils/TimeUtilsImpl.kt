package com.chrissloan.paw_some.utils

import com.chrissloan.paw_some.data.utils.TimeUtils
import java.util.concurrent.TimeUnit

class TimeUtilsImpl: TimeUtils {

    override fun currentTime() = System.currentTimeMillis()

    override fun minutesMs(number: Long) = TimeUnit.MINUTES.toMillis(number)
}
