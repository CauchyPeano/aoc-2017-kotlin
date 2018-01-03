import java.util.HashMap

sealed class CMD()
data class SET(val reg: String, val arg: String) : CMD()
data class SUB(val reg: String, val arg: String) : CMD()
data class MUL(val reg: String, val arg: String) : CMD()
data class JNZ(val reg: String, val arg: String) : CMD()

fun main(args: Array<String>) {
    val readText = args.javaClass.getResource("/input23.txt").readText()
    val cmd = readText.split("\r\n").map { it -> it.split(" ") }
            .map {
                when (it[0]) {
                    "set" -> SET(it[1], it[2])
                    "sub" -> SUB(it[1], it[2])
                    "mul" -> MUL(it[1], it[2])
                    "jnz" -> JNZ(it[1], it[2])
                    else -> {
                        throw IllegalStateException()
                    }
                }
            }


    val reg = hashMapOf<String, Int>()
    ('a'..'h').forEach {
        reg.put(it.toString(), 0)
    }

    reg.put("a", 1)

    var idx = 0

    var mulTimes = 0
    var cnt = 0L
    while (true) {

        if (idx < 0 || idx >= cmd.size) {
            println(mulTimes)
            return
        }

        if (cnt % 10000 == 0L) {
            println("$cnt")
        }


        val command = cmd[idx]
        when (command) {
            is SET -> {
                val eval = toValue(command.arg, reg)
                reg.put(command.reg, eval)
                idx++
            }
            is SUB -> {
                val l = reg[command.reg]
                val eval = l!! - toValue(command.arg, reg)
                reg.put(command.reg, eval)
                idx++
            }
            is MUL -> {
                val eval = reg[command.reg]!! * toValue(command.arg, reg)
                reg.put(command.reg, eval)
                mulTimes++
                idx++
            }
            is JNZ -> {
                if (toValue(command.reg, reg) != 0) {
                    val eval = toValue(command.arg, reg)
                    idx += eval
                } else {
                    idx++
                }
            }

        }

        cnt++
    }


}

private fun toValue(first: String, rgstr: HashMap<String, Int>): Int {
    return try {
        first.toInt()
    } catch (e: NumberFormatException) {
        rgstr.getOrPut(first) { 0 }
    }
}
