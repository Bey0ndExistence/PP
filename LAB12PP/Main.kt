import java.text.*
import java.util.*
import kotlin.properties.*
import kotlin.text.reversed

fun Int.isPrime(): Boolean {
    for (i in 2..this / 2) {
        // condition for nonprime number
        if (this % i == 0) {
            return false
        }
    }

    return true
}

fun pb1() {
    println(12.isPrime())
    println(11.isPrime())
}

fun String.toDate(formatStr: String): Date {
    return SimpleDateFormat(formatStr).parse(this)
}

fun pb2() {
    val date = "22-11-2020".toDate("dd-MM-yyyy")

    println(date)
}

fun pb3() {
    val hashMap =
        hashMapOf(
            "abc" to 1,
            "def" to 2,
            "ghi" to 3,
        )

    val newHashMap = hashMap.entries.associate { it.value to it.key }

    println(hashMap)
    println(newHashMap)
}

fun pb4() {
    var myIntPrime: Int by
    Delegates.vetoable(3) { property, _, newValue ->
        val prime = newValue.isPrime()

        println("${property.name}: ${newValue} is prime:${prime}")

        prime
    }

    myIntPrime = 6

    myIntPrime = 9

    myIntPrime = 11
}

fun pb5() {
    val reader = Scanner(System.`in`)
    print("Enter a number: ")

    var n = reader.nextInt()

    val list = (1..10).map { Random().nextInt() }

    val newList = list.flatMap({ el -> (0..n).map { el } })

    println(list)
    println(newList)
}

fun pb6() {
    val seqText = readLine()

    if (seqText == null) {
        return
    }

    val seq = seqText.splitToSequence("").distinct().joinToString("", "", "")

    println(seq)
}

fun pb7() {
    val seqText = readLine()

    if (seqText == null) {
        return
    }

    var letter = ""
    var letter2 = ""
    var numbers = 0

    val seq =
        seqText
            .reversed()
            .splitToSequence("")
            .filter { it != "" }
            .map {
                if (letter == it) {
                    numbers += 1

                    listOf(it, numbers)
                } else {
                    letter = it
                    numbers = 0

                    listOf(it, numbers)
                }
            }
            .toList()
            .asReversed()
            .filter {
                if (it[0] == letter2) {
                    false
                } else {
                    letter2 = it[0] as String
                    true
                }
            }
            .map {
                if (it[1] == 0) {
                    it[0]
                } else {
                    it[0] as String + it[1].toString()
                }
            }
            .joinToString("", "", "")

    println(seq)
}

fun main() {

    pb2()
}