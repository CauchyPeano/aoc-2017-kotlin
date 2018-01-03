fun main(args: Array<String>) {
    val input = "5 1 10 0 1 7 13 14 3 12 8 10 7 12 0 6"
//    val input = "0 2 7 0"

    val items = input.split(" ").map { it.toInt() }.toIntArray()

    solv(items)


}

fun solv(input: IntArray) {

    val map = hashMapOf<String, Int>()

    var st = input
    var cycle = 0
    while (!map.contains(st.joinToString())) {
        cycle ++
        println(st.joinToString())
        map.put(st.joinToString(), cycle)

        val mi = maxIndex(st)

        val maxBank = st[mi]

        st[mi] = 0

        for (k in 1..maxBank) {
            val size = st.size
            st[(mi + k) % size] += 1
        }
    }

    println("cycles = $cycle")
    val t : Int = map[st.joinToString()] ?: 0
    val len = cycle - t + 1
    println("length = $len")

}

fun maxIndex(st: IntArray) : Int {
    var max = 0
    var mi = 0
    for ((index, i) in st.withIndex()) {

        if (i > max) {
            mi = index
            max = i
        }

    }
    return mi
}
