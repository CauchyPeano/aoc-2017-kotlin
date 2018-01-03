
fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input4.txt").readText()


    val map = readText.split("\r\n").map { it -> it.split(" ") }

    solve1(map)

    println("bcdaed".toCharArray().sorted().joinToString(""))

    solve2(map)

//    val map = readText.split("\n").map {
//        val tokens = it.split(" ")
//        val res = if (tokens.distinct().size == tokens.size) {
//            1
//        } else {
//            0
//        }
//        println(tokens.joinToString(",") + " = $res")
//        res
//    }.sum()

//    println(map)
}

fun solve2(input: List<List<String>>) {

    val sum = input.map {

        val mapd = it.map { it.toCharArray().sorted().joinToString("") }
        if (mapd.size == mapd.distinct().size) {
            1
        } else {
            0
        }

    }.sum()

    println("Result2 = $sum")

}

private fun solve1(map: List<List<String>>) {
    var i = 0
    map.forEach {
        val t = it.joinToString(",")

        val b = if (it.distinct().size == it.size) {
            1
        } else {
            0
        }

        i += b
//        println("$t = " + b)
    }

    println(i)
}