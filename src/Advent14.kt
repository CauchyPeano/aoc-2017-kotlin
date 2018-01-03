
fun main(args: Array<String>) {

    val inputText = "ljoxqyyw"
//    val inputText = "flqrgnkx"
//    val readText = "1,2,3"

    var count = 0

    var regions = 0
    val matrix = Array(128) { ByteArray(128) }

    for (i in 0..127) {
        val str = inputText + "-" + i
        val res = calcHash(str)

        var bin = ""
        for ((index, it) in res.withIndex()) {
            bin += String.format("%8s", Integer.toBinaryString(it)).replace(' ', '0')
        }

        for ((index, c) in bin.withIndex()) {
            assert(bin.length == 128)
            matrix[i][index] = if (c == '1') { 1 }  else { 0 }
        }

        count += bin.filter { c -> c == '1'}.length
//        bin = bin.replace("0", ".").replace("1", "#")
    }


    for ((i, arr) in matrix.withIndex()) {
        for ((j, byte) in arr.withIndex()) {
            if (matrix[i][j] == 1.toByte()) {
                regions++
                clearRegion(matrix, i, j)
            }
        }
    }


    println("Regions = $regions")

    println(count)

}

fun clearRegion(matrix: Array<ByteArray>, i: Int, j: Int) {

    matrix[i][j] = 0

    if (i != 0 && matrix[i - 1][j] == 1.toByte()) {
        clearRegion(matrix, i - 1, j)
    }
    if (i != 127 && matrix[i + 1][j] == 1.toByte()) {
        clearRegion(matrix, i + 1, j)
    }

    if (j != 0 && matrix[i][j - 1] == 1.toByte()) {
        clearRegion(matrix, i, j - 1)
    }
    if (j != 127 && matrix[i][j + 1] == 1.toByte()) {
        clearRegion(matrix, i, j + 1)
    }
}

private fun calcHash(readText: String): IntArray {
    val encodedLens = readText.toCharArray().map { it.toInt() }.toIntArray()
    val mutList = encodedLens.asList().toMutableList()
    mutList.addAll(arrayOf(17, 31, 73, 47, 23))

    val lenArr = mutList.toIntArray()

    val nums = (0..255).toList().toIntArray()

    var skip = 0
    var cur = 0
    (1..64).forEach {
        for (l in lenArr) {

            swap(nums, cur, l)

            cur += l + skip
            skip++

            cur %= nums.size
        }
    }

    val denseHash = IntArray(16)
    for (i in 0..(16 - 1)) {
        val l = 16 * i
        denseHash[i] = nums.sliceArray(l..(l + 15)).reduce { a, b -> a xor b }
    }

//    val str = denseHash.toHex()
    return denseHash
}

//private val HEX_CHARS = "0123456789abcdef"

//fun String.hexStringToByteArray() : ByteArray {
//
//    val result = ByteArray(length / 2)
//
//    for (i in 0 until length step 2) {
//        val firstIndex = HEX_CHARS.indexOf(this[i])
//        val secondIndex = HEX_CHARS.indexOf(this[i + 1])
//
//        val octet = firstIndex.shl(4).or(secondIndex)
//        result[i.shr(1)] = octet.toByte()
//    }
//
//    return result
//}

//fun String.hexStringToMyByteArray() : ByteArray {
//
//    val result = ByteArray(length)
//
//    for (i in 0 until length step 2) {
//
//        val firstIndex = HEX_CHARS.indexOf(this[i])
//        val secondIndex = HEX_CHARS.indexOf(this[i + 1])
//
//        val octet = firstIndex.shl(4).or(secondIndex)
//        result[i.shr(1)] = octet.toByte()
//    }
//
//    return result
//}