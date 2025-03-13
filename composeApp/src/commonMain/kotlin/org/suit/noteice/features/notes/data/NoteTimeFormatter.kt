package org.suit.noteice.features.notes.data

import noteice.composeapp.generated.resources.Res
import noteice.composeapp.generated.resources.today
import org.jetbrains.compose.resources.getString
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NoteTimeFormatter(
    private val locale: Locale,
    private val clock: Clock,
) {
    suspend fun format(timeToFormat: Instant): String {
        val currTime = Date.from(clock.instant())
        val currCalendar = Calendar.getInstance().apply {
            time = currTime
        }
        val targetCalendar = Calendar.getInstance().apply {
            time = Date.from(timeToFormat)
        }
        val isDaySame = currCalendar.get(Calendar.DAY_OF_YEAR) == targetCalendar.get(Calendar.DAY_OF_YEAR)
        val isYearSame = currCalendar.get(Calendar.YEAR) == targetCalendar.get(Calendar.YEAR)
        val pattern = if (!isYearSame) {
                "MMM dd, yyyy, HH:mm"
            } else {
                if (!isDaySame) "MMM dd, HH:mm" else "'${getString(Res.string.today)}', HH:mm"
            }
        return SimpleDateFormat(pattern, locale).format(Date.from(timeToFormat))
    }
}