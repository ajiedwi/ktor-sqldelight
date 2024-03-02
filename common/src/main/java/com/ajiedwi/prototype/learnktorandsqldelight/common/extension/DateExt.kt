package com.ajiedwi.prototype.learnktorandsqldelight.common.extension

import java.util.Date

fun Date.getDifferentDateInMs(date2: Date) = this.time - date2.time

fun Date.getDifferentDateInSecond(date2: Date) = this.getDifferentDateInMs(date2) / 1000

fun Date.getDifferentDateInMinute(date2: Date) = this.getDifferentDateInSecond(date2) / 60

fun Date.getDifferentDateInHour(date2: Date) = this.getDifferentDateInMinute(date2) / 60

fun Date.getDifferentDateInDay(date2: Date) = this.getDifferentDateInHour(date2) / 24