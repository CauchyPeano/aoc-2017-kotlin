import java.util.*

val range = ('a'..'p')
val tmp = CharArray(range.count())

val COUNT = range.count()

val total = 1_000_000_000

fun main(args: Array<String>) {

    println("range = ${range.toString()}")

    val readText = args.javaClass.getResource("/input16.txt").readText()

    val commands = readText.split(",")

    val dance = CharArray(COUNT)

    var i = 0
    for (c in range) {
        dance[i++] = c
    }

//    val tt = CharArray(5)
//    for ((index, it) in ('a'..'e').withIndex()) {
//        tt[index] = it
//    }
//    danceShift("s3", tt)
//    println(tt.joinToString())

    performDance(commands, dance)

    println(dance.joinToString(separator = ""))

    val shifts = IntArray(range.count())
    for ((index, c) in range.withIndex()) {
        shifts[index] = dance.indexOf(c)
    }

    println(shifts.joinToString())

    var count = 1
    for (i in 2..(1_000_000_000 % 63)) {
        count ++
        performDance(commands, dance)

        val v = dance.joinToString(separator = "")
//        if (v.equals("abcdefghijklmnop")) {
//            println("got! $v at $count")
//        }
    }

    println(dance.joinToString(separator = ""))

}

fun applyShifts(shifts: IntArray, dance: CharArray) {
    for (index in 0 until dance.size) {
        tmp[index] = dance[shifts[index]]
    }

    for (index in 0 until dance.size) {
        dance[index] = tmp[index]
    }
}

private fun performDance(commands: List<String>, dance: CharArray) {
    commands.forEach {
        val cmd = it[0]
//        println(dance.joinToString(separator = ""))
        when (cmd) {
            's' -> {
                danceShift(it, dance)
            }

            'p' -> {
                pair(it, dance)
            }
            'x' -> {
                swap(it, dance)
            }
        }

    }
}

private fun swap(it: String, dance: CharArray) {
    val split = it.removePrefix("x").split("/")
    val c1 = split[0].toInt()
    val c2 = split[1].toInt()

    val tmp = dance[c1]
    dance[c1] = dance[c2]
    dance[c2] = tmp

}

private fun pair(it: String, dance: CharArray) {
    val split = it.removePrefix("p").split("/")
    val c1 = split[0].toCharArray()[0]
    val c2 = split[1].toCharArray()[0]

    val i1 = dance.indexOf(c1)
    val i2 = dance.indexOf(c2)

    dance[i1] = c2
    dance[i2] = c1
}

private fun danceShift(it: String, dance: CharArray) {
    val shift = it.removePrefix("s").toInt()

//    val s1 = dance.copyOfRange(0, dance.size - shift)
//    val s2 = dance.copyOfRange(dance.size - shift, dance.size)

    for (i in 0 until COUNT) {
       tmp[i] = dance[(COUNT - shift + i) % COUNT]
    }

    for (i in 0 until COUNT) {
        dance[i] = tmp[i]
    }

//    var idx = 0
//    for (c in s2) {
//        dance[idx] = c
//        idx++
//    }
//    for (c in s1) {
//        dance[idx] = c
//        idx++
//    }


}
