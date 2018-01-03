import java.util.*

val spinlock = 344

val times1 = 2017
val times2 = 50_000_000

fun main(args: Array<String>) {

    solve1()

    solve2()
}

private fun solve2() {

    val ints = arrayListOf<Int>()
    ints.add(0)

    var curr = 0
    var size = 1
    for (i in 1..times2) {

        val npos = (curr + spinlock) % size
        if (npos == 0) {
            ints.add(npos + 1, i)
        }
        size++

        curr = (npos + 1)

        if (i % 500_000 == 0) {
            println("$i")
        }

//        println("$curr = ${ints.joinToString()}")

    }

    println("${ints[1]}")

}

private fun solve1() {
    val ints = arrayListOf<Int>()

    ints.add(0)

    var curr = 0

    for (i in 1..times1) {

        val npos = (curr + spinlock) % ints.size
        ints.add(npos + 1, i)

        curr = (npos + 1)

        if (i % 500_000 == 0) {
            println("$i")
        }

//        println("$curr = ${ints.joinToString()}")

    }

    println("${ints[curr + 1]}")
}