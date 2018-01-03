fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input13.txt").readText()

    val lines = readText.split("\r\n")

    val net = arrayListOf<IntArray?>()

    (0..100).forEach { net.add(null) }

    for (line in lines) {

        val arr = line.split(":").map { it.trim() }.map { it.toInt() }

        val layer = arr[0]
        val depth = arr[1]

        net.add(layer, IntArray(depth))
    }

    val score2 = firstPart(net, 0)
    println("First solution = $score2")

    var delay = 10
    var score = -1
    while (score != 0) {
        delay++
        score = firstPart(net, delay)
//        println("$delay Got score $score")
    }

    println("Delay $delay")

//    for (it in 0..20) {
//        println("$it = ${isFirewall(IntArray(5), it)}")
//    }

}


private fun firstPart(net: ArrayList<IntArray?>, delay : Int) : Int {

    var cursor = 0
    var score = 0

    var tick = delay

    var caught = 0
    while (cursor < net.size) {
        if (net[cursor] != null && isFirewall(net[cursor]!!, tick)) {
            val add = net[cursor]!!.size * cursor
            score += add
            caught ++
        }
        tick++
        cursor++
    }

    if (caught == 0) {
        println("Not caught")
    }

    return caught
}

private fun isFirewall(ints: IntArray, time : Int) : Boolean {
    return time % (ints.size * 2 - 2) == 0

}
