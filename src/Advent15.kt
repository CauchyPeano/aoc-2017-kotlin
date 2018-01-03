
fun main(args: Array<String>) {

//    Generator A starts with 783
//    Generator B starts with 325

//    val total = 40_000_000
    val total = 5_000_000

//    var genA = 65L
//    var genB = 8921L
    var genA = 783L
    var genB = 325L

    val facA : Long = 16807L
    val facB : Long = 48271L

    val divider = 2147483647L

    var count = 0
    for (i in 1..total) {

        genA = (genA * facA) % divider
        while (genA and 0x3L != 0x0L) {
            genA = (genA * facA) % divider
        }

        genB = (genB * facB) % divider
        while (genB and 0x7L != 0x0L) {
            genB = (genB * facB) % divider
        }

//        println(String.format("%10d %10d", genA, genB))
//        val str1 = String.format("%32s", genA.toString(2)).replace(' ', '0')
//        val str2 = String.format("%32s", genB.toString(2)).replace(' ', '0')

        if (genA and 0xFFFF == genB and 0xFFFF) {
            count++
        }

//        println(String.format("%32s %32s", str1, str2))

    }

    println("count = ${count}")


}