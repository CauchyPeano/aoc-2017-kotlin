fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input8.txt").readText()
    val lines = readText.split("\r\n")


    val regs = hashMapOf<String, Int>()

    var maxVal = Int.MIN_VALUE

    lines.forEach {
        val tokens = it.split(" ")

        val regName = tokens[0]
        val op = tokens[1]
        val dx = tokens[2].toInt()
        val val1 = tokens[4]
        val comp = tokens[5]
        val val2 = tokens[6].toInt()

        val regVal1 : Int = regs.getOrPut(val1) {
            0
        }

        val proceed : Boolean = when (comp) {
            "<=" -> regVal1 <= val2
            "<" -> regVal1 < val2
            ">=" -> regVal1 >= val2
            ">" -> regVal1 > val2
            "==" -> regVal1 == val2
            "!=" -> regVal1 != val2
            else -> {
                throw IllegalArgumentException(comp)
            }
        }

        if (proceed) {
            val mainRegVal : Int = regs.getOrPut(regName) { 0 }

            val endval = when (op) {
                "inc" -> mainRegVal + dx
                "dec" -> mainRegVal - dx
                else -> {
                    throw IllegalArgumentException(op)
                }
            }

            if (endval > maxVal) {
                maxVal = endval
            }
            regs.put(regName, endval)
        }

    }

    println(regs.maxBy { (_,v) -> v })
    println("MaxVal = $maxVal")

}