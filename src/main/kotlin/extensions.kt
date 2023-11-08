import java.util.Locale
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

val current: LocalDate
    get() = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

fun day(i: Int, year: Int) = LocalDate(year, 1, 1).plus(i, DateTimeUnit.DAY)

val Month.short: String
    get() = toString()
        .take(3)
        .lowercase()
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }