import com.lordcodes.turtle.shellRun
import javax.swing.text.html.HTML.Tag.P
import kotlinx.datetime.LocalDate

fun main() {
    val message = "Welcome"//readMessage()
    val fontWeight = 10//readFontWeight()
    val year = 2023//readYear()
    val dryRun = true

    if (dryRun) {
        produceAsciiArt(message, fontWeight, year, ::draw)
    } else {
        produceAsciiArt(message, fontWeight, year, ::commit)
    }
}

private fun produceAsciiArt(message: String, fontWeight: Int, year: Int, produce: (String, Int, Int, Int) -> Unit) {
    val daysInYear = LocalDate(year, 12, 31).dayOfYear

    message
        .uppercase()
        .map { alphabet[it] ?: error("Character $it is not supported") }
        .forEachIndexed { letterIndex, letter ->
            println("Letter ${letterIndex + 1}: ${message[letterIndex]}")
            letter.forEachIndexed { rowIndex, row ->
                println("- Row ${rowIndex + 1} (day ${letterIndex * 7 + rowIndex + 1}): ${row.joinToString("") { if (it) "X" else "." }}")
                row.forEachIndexed { pixelIndex, pixel ->
                    val day = letterIndex * 7 + rowIndex * 5 + pixelIndex
                    println("- - Pixel ${pixelIndex + 1} (day ${day + 1}): ${if (pixel) "X" else "."}")
//                    if (pixel) {
//                        // Produce multiple commits to make the text bolder on GitHub
//                        produce(message, day, year, fontWeight)
//                    } else {
//                        // Color the background with a single commit
//                        produce(message, day, year, 1)
//                    }
                }
            }
            println("")
        }
}

private fun commit(message: String, day: Int, year: Int, fontWeight: Int) {
    val date = day(day, year)
    val dateString = "Mon, ${date.dayOfMonth} ${date.month.short} $year 12:00:00"

    (1..fontWeight).forEach {
        shellRun("git", listOf("commit", "--allow-empty", "-m", "($it/$fontWeight) $dateString", "--date", dateString, "--quiet"))
    }
}

private fun draw(message: String, day: Int, year: Int, fontWeight: Int) {
    if (day % 5 == 0) {
        println()
    }
    if (fontWeight == 1) {
        print(".")
    } else {
        print("X")
    }

}

private fun readMessage(): String {
    while (true) {
        println("Enter a message (default: Welcome): ")
        val message = readln().ifEmpty { "Welcome" }

        if (message.length > 8) {
            println("Message can only be 8 or less characters. We know it's limiting..")
        } else {
            return message
        }
    }
}

private fun readFontWeight(): Int {
    while (true) {
        println("Enter a front weight between 1 and 20 (default: 5): ")

        val fontWeight = readln().toIntOrNull() ?: 5

        if (fontWeight !in 1..20) {
            println("Font weight should be between 1 and 20, to not get too crazy with commits.")
        } else {
            return fontWeight
        }
    }
}

private fun readYear(): Int {
    val currentYear = current.year

    while (true) {
        println("Enter the year you would like the message in (default: $currentYear): ")
        val year = readln().toIntOrNull() ?: currentYear

        if (year !in 2000..currentYear) {
            println("Let's try a year between 2000 and $currentYear.")
        } else {
            return year
        }
    }
}