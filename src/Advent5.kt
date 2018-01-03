fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input5.txt").readText()
    val map = readText.split("\r\n").map { it -> it.toInt() }

    val l = listOf(0, 3, 0, 1, -3)
//    println(solve(l.toIntArray()))
//    println(solve(map.toIntArray()))

    println(solve2(map.toIntArray()))
}

fun solve(v: IntArray) : Int {
    var c = 0
    try {
        var i = 0
        while (true) {
            val x = v[i]
            v[i] = x + 1
            i += x
            c++
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return c
    }
}

fun solve2(v: IntArray): Int {
    var c = 0
    try {
        var i = 0
        while (true) {
            val x = v[i]
            if (x >= 3) {
                v[i] = x - 1
            } else {
                v[i] = x + 1
            }
            i += x
            c++
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return c
    }
}
