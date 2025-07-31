package org.smartroots.data.utils

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object Converters {
    @TypeConverter
    @OptIn(kotlin.time.ExperimentalTime::class)
    fun fromTimestamp(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String {
        return date.toString()
    }
}
