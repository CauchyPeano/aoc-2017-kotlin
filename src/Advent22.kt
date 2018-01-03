data class Pos(val x: Int, val y: Int) {
    fun go(dir: Direction): Pos {
        return when (dir) {
            Direction.NORTH -> Pos(x, y - 1)
            Direction.SOUTH -> Pos(x, y + 1)
            Direction.EAST -> Pos(x + 1, y)
            Direction.WEST -> Pos(x - 1, y)
        }
    }
}

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input22.txt").readText()
    val lines = readText.split("\r\n")

    val dat = lines.map { it.map { it == '#' } }

    val flagged = mutableSetOf<Pos>()
    val weakened = mutableSetOf<Pos>()
    val infected = mutableSetOf<Pos>()


    val midX = dat[0].size / 2
    val midY = dat.size / 2

    for ((y, list) in dat.withIndex()) {
        for ((x, i) in list.withIndex()) {
            if (i) {
                infected.add(Pos(x - midX, y - midY))
            }
        }
    }

    var infection = 0

    var cdir = Direction.NORTH
    var cpos = Pos(0, 0)
    for (i in 1..10000000) {

//        drawMap(map, cpos, cdir)
//        println("\n\n")

        cdir = when (cpos) {
            in weakened -> cdir
            in infected -> cdir.rotateRight()
            in flagged -> cdir.reverse()
            else -> cdir.rotateLeft()
        }

        when (cpos) {
            in weakened -> {
                weakened -= cpos
                infection++
                infected += cpos
            }
            in infected -> {
                infected -= cpos
                flagged += cpos
            }
            in flagged -> {
                flagged -= cpos
            }
            else -> {
                weakened += cpos
            }
        }

        cpos = cpos.go(cdir)

    }

    println(infection)


}


private fun drawMap(set: Set<Pos>, pos: Pos, dir: Direction) {

    for (i in -4..5) {
        for (j in -4..5) {
            val pos1 = Pos(j, i)
            if (pos1 == pos) {
                print("[")
            } else {
                print(" ")
            }

            if (pos1 in set) {
                print("#")
            } else {
                print(".")
            }

            if (pos1 == pos) {
                print("]")
            } else {
                print(" ")
            }
        }
        println()
    }

}