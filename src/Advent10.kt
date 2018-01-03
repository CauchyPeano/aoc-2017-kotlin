
fun main(args: Array<String>) {

    solve1()
    solve2()
}

private fun solve2() {

    println("=== solve2 ===")
    val readText = "227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144"
//    val readText = "1,2,3"

    val encodedLens = readText.toCharArray().map { it.toInt() }.toIntArray()
    val mutList = encodedLens.asList().toMutableList()
    mutList.addAll(arrayOf(17, 31, 73, 47, 23))

    val lenArr = mutList.toIntArray()

    val nums = (0..255).toList().toIntArray()

    var skip = 0
    var cur = 0
    (1..64).forEach {
        for (l in lenArr) {

            println(nums.joinToString())

            swap(nums, cur, l)

            cur += l + skip
            skip++

            cur %= nums.size
        }
    }

    val denseHash : IntArray = IntArray(16)
    for (i in 0..(16-1)) {
        val l = 16 * i
        denseHash[i] = nums.sliceArray(l..(l + 15)).reduce { a, b -> a xor b }
    }

    val str = denseHash.toHex()
    println(str)

}

private fun solve1() {
    val readText = "227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144"
//    val readText = "3,4,1,5"

    val lens = readText.split(",").map { it.toInt() }

    val nums = (0..255).toList().toIntArray()
//    val nums = (0..4).toList().toIntArray()

    var skip = 0
    var cur = 0
    for (l in lens) {

//        println(nums.joinToString())

        swap(nums, cur, l)

        cur += l + skip
        skip++

    }

//    println(nums.joinToString())

    println(nums[0] * nums[1])
    println("cur = ${cur % nums.size}, skip = $skip")
    //2 1 0 [3] 4
    //4 3 0 [1] 2
    //4 [3] 0 1 2
    //3 4 2 1 [0]
}

fun swap(arr: IntArray, i: Int, d: Int) {

    val dd = (d / 2) -1
    val j = i + d - 1

    for (k in 0..dd) {
        val tmp = arr[(j - k) % arr.size]
        arr[(j - k) % arr.size] = arr[(i + k) % arr.size]
        arr[(i + k) % arr.size] = tmp
    }

}

private val HEX_CHARS = "0123456789abcdef".toCharArray()

fun IntArray.toHex() : String{
    val result = StringBuffer()

    forEach {
        val octet = it
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}