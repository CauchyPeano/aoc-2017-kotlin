import kotlin.math.absoluteValue

fun main(args: Array<String>) {

    val input = 277678

    solve1(input)

    solve2(input)

}

enum class Side {
    TOP, BOTTOM, LEFT, RIGHT
}

fun solve2(input: Int) {
    //http://oeis.org/A141481

    println(" ==== solve2 ==== ")


    val maxval = Int.MAX_VALUE / 1024 / 1024
    val zero = maxval / 2
    val mt = Array(maxval, { IntArray(maxval, { 0 }) })

    var x = 0
    var y = 0
    var level = 0

    var dir = Side.BOTTOM

    var i = 1
    mt[zero][zero] = 1
    for (t in 1..30) {

        val side = level * 2 + 1

        when (dir) {
            Side.RIGHT -> {
                y -= 1
                if (x.absoluteValue + y.absoluteValue == (side - 1)) {
                    dir = Side.TOP
                }
            }
            Side.TOP -> {
                x -= 1
                if (x.absoluteValue + y.absoluteValue == (side - 1)) {
                    dir = Side.LEFT
                }
            }
            Side.LEFT -> {
                y += 1
                if (x.absoluteValue + y.absoluteValue == (side - 1)) {
                    dir = Side.BOTTOM
                }
            }
            Side.BOTTOM -> {
                x += 1
                if (x.absoluteValue + y.absoluteValue == (side)) {
                    dir = Side.RIGHT
                    level += 1
                }
            }
        }

        val dx = zero + x
        val dy = zero + y
        i += 1
//        mt[dx][dy] = i
        mt[dx][dy] = mt[dx + 1][dy] + mt[dx][dy + 1] + mt[dx + 1][dy + 1] + mt[dx - 1][dy] + mt[dx][dy - 1] + mt[dx - 1][dy - 1] + mt[dx - 1][dy + 1] + mt[dx + 1][dy - 1]

        println("($x, $y) = ${mt[dx][dy]}")
    }

    val range = (zero - 6)..(zero + 6)
    val sliceArray = mt.sliceArray(range)
    sliceArray.map { it.sliceArray(range) }
            .forEach { println(it.map { it -> "%4d".format(it) }.joinToString(",")) }

}


private fun solve1(input: Int) {
    val init = 1

    var side = 1

    var lastElem = 1
    while (lastElem < input) {
        side += 2
        val count = (side - 1) * 4

        lastElem += count
    }

    val h = (side - 1)
    val mx = h / 2

    println("side = $side")
    println("lastelem = $lastElem")

    var x = 0
    var y = 0

    if (lastElem - h < input && (input <= lastElem)) {
        x = mx
        y = mx - lastElem + input
    }

    if (lastElem - h * 2 < input && (input <= lastElem - h)) {
        x = mx - (lastElem - h) + input
        y = mx
    }

    if (lastElem - h * 3 < input && (input <= lastElem - h * 2)) {
        x = -mx
        y = -mx + (lastElem - h * 2) - input
    }

    if (lastElem - h * 4 < input && (input <= lastElem - h * 3)) {
        x = -mx + (lastElem - h * 3) - input
        y = mx
    }

    println("($x,$y)")

    println(x.absoluteValue + y.absoluteValue)
}