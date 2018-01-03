import java.lang.Math.abs

data class Hex(var x : Int, var y : Int, var z : Int)

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input11.txt").readText()

    val cmds = readText.split(",")

    val pos = Hex(0, 0, 0)

    var maxDist = 0

    for (c in cmds) {
        when (c) {
            "n" -> {
                pos.x++; pos.z--
            }
            "ne" -> {
                pos.x++;pos.y--
            }
            "nw" -> {
                pos.y++;pos.z--
            }
            "s" -> {
                pos.x--;pos.z++
            }
            "sw" -> {
                pos.x--;pos.y++
            }
            "se" -> {
                pos.y--; pos.z++
            }
        }
        val dist = dist(pos)
        if (dist > maxDist) {
            maxDist = dist
        }

    }

    println(pos.toString())


    println("Distance to 0 = ${dist(pos)}")
    println("MaxDist = $maxDist")

}

private fun dist(pos: Hex) = (abs(pos.x) + abs(pos.y) + abs(pos.z)) / 2