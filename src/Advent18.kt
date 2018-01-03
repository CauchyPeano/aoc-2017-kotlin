import java.util.*

data class Programm(var instr : Int, val rgstr : HashMap<String, Long>, val input : LinkedList<Long>, val output : LinkedList<Long>, var sendAmt : Int)

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input18.txt").readText()

    val cmds = readText.split("\r\n")

//    solve1(cmds)

    solve2(cmds)

}

private fun solve2(cmds : List<String>) {

    val reg1 = hashMapOf<String, Long>()
    reg1.put("p", 0)

    val p1top2 = LinkedList<Long>()
    val p2top1 = LinkedList<Long>()

    val p1 = Programm(0, reg1, p2top1, p1top2, 0)


    val reg2 = hashMapOf<String, Long>()
    reg2.put("p", 1)
    val p2 = Programm(0, reg2, p1top2, p2top1, 0)

    while (true) {

        val r1 = exec(cmds, p1)
        val r2 = exec(cmds, p2)

        if (r1 == 0 && r2 == 0) {
            break
        }

    }
    println("Programms deadlocked")
    println("Send amt ${p2.sendAmt}")

}

fun exec(cmds: List<String>, p: Programm) : Int {

    val c = cmds[p.instr]
    val split = c.split(" ")
    val first = split[1]

    when (split[0]) {
        "set" -> {
            p.rgstr[first] = toValue(split[2], p.rgstr)
            p.instr++
        }
        "snd" -> {
            val sound = toValue(first, p.rgstr)
            p.output.push(sound)
            p.sendAmt++
            p.instr++
        }
        "rcv" -> {
            val k = toValue(first, p.rgstr)
            val pollLast = p.input.pollLast()
            if (pollLast != null) {
                p.rgstr.put(first, pollLast)
                p.instr++
            } else {
                return 0
            }
        }
        "add" -> {
            val v = toValue(first, p.rgstr)
            p.rgstr.put(first, v + toValue(split[2], p.rgstr))
            p.instr++
        }
        "mul" -> {
            val v = toValue(first, p.rgstr)
            p.rgstr.put(first, v * toValue(split[2], p.rgstr))
            p.instr++
        }
        "mod" -> {
            val v = toValue(first, p.rgstr)
            p.rgstr.put(first, v % toValue(split[2], p.rgstr))
            p.instr++
        }
        "jgz" -> {
            val v = toValue(first, p.rgstr)
            if (v > 0) {
                p.instr += toValue(split[2], p.rgstr).toInt()
            } else {
                p.instr++
            }
        }
    }
    return 1

}

private fun solve1(cmds: List<String>) {
    var inst = 0

    val rgstr = hashMapOf<String, Long>()

    var lastPlayedSound = 0L

    while (inst in (0..(cmds.size - 1))) {

        val c = cmds[inst]
        val split = c.split(" ")
        val first = split[1]

        when (split[0]) {
            "set" -> {
                rgstr[first] = toValue(split[2], rgstr)
                inst++
            }
            "snd" -> {
                val sound = rgstr[split[1]]
                if (sound != null) {
                    lastPlayedSound = sound
                }
                inst++
            }
            "add" -> {
                val v = toValue(first, rgstr)
                rgstr.put(first, v + toValue(split[2], rgstr))
                inst++
            }
            "mul" -> {
                val v = toValue(first, rgstr)
                rgstr.put(first, v * toValue(split[2], rgstr))
                inst++
            }
            "mod" -> {
                val v = toValue(first, rgstr)
                rgstr.put(first, v % toValue(split[2], rgstr))
                inst++
            }
            "rcv" -> {
                val k = toValue(first, rgstr)
                if (k != 0L) {
                    println("Recovering $lastPlayedSound")
                }
                inst++
            }
            "jgz" -> {
                val v = toValue(first, rgstr)
                if (v > 0) {
                    inst += toValue(split[2], rgstr).toInt()
                } else {
                    inst++
                }
            }
        }

    }
}

private fun toValue(first: String, rgstr: HashMap<String, Long>): Long {
    return try {
        first.toLong()
    } catch (e: NumberFormatException) {
        rgstr.getOrPut(first) { 0L }
    }
}
