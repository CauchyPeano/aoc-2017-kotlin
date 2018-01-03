import kotlin.math.E

enum class Direction {
    NORTH {
        override fun next(x: Int, y: Int) = x to (y - 1)
    },
    SOUTH {
        override fun next(x: Int, y: Int) = (x) to (y + 1)
    },
    EAST {
        override fun next(x: Int, y: Int) = (x + 1) to y
    },
    WEST {
        override fun next(x: Int, y: Int) = (x - 1) to y
    };

    fun rotateLeft() : Direction {
        return when (this) {

            Direction.NORTH -> WEST
            Direction.WEST -> SOUTH
            Direction.SOUTH -> EAST
            Direction.EAST -> NORTH
        }
    }

    fun rotateRight() : Direction {
        return when (this) {

            Direction.NORTH -> EAST
            Direction.EAST -> SOUTH
            Direction.SOUTH -> WEST
            Direction.WEST -> NORTH
        }
    }

    fun reverse(): Direction {
        return when (this) {
            Direction.NORTH -> SOUTH
            Direction.EAST -> WEST
            Direction.SOUTH -> NORTH
            Direction.WEST -> EAST
        }

    }

    abstract fun next(x: Int, y: Int): Pair<Int, Int>
}

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input19.txt").readText()
    val cmds = readText.split("\r\n")

    var x = cmds[0].indexOf('|')
    var y = 0

    val res = StringBuilder()

    var dir = Direction.SOUTH
    var steps = 0

    loop@ while (true) {

        val (x1, y1) = dir.next(x, y)
        val currSign = getC(cmds, x, y)
        val nextSign = getC(cmds, x1, y1)

        println("Standing at $currSign")

        if (currSign == '+' && nextSign == ' ') {

            val dirs = when (dir) {
                Direction.NORTH -> {listOf(Direction.EAST, Direction.WEST)}
                Direction.SOUTH -> {listOf(Direction.EAST, Direction.WEST)}
                Direction.EAST -> {listOf(Direction.NORTH, Direction.SOUTH)}
                Direction.WEST -> {listOf(Direction.NORTH, Direction.SOUTH)}
            }

            for (d1 in dirs) {

                val (xn, yn) = d1.next(x, y)
                val tryC = getC(cmds, xn, yn)

                if (d1 == Direction.SOUTH && (tryC == '|' || tryC.isLetter())) {
                    dir = Direction.SOUTH
                    break
                } else if (d1 == Direction.NORTH && (tryC == '|' || tryC.isLetter())) {
                    dir = Direction.NORTH
                    break
                } else if (d1 == Direction.EAST && (tryC == '-' || tryC.isLetter())) {
                    dir = Direction.EAST
                    break
                } else if (d1 == Direction.WEST && (tryC == '-' || tryC.isLetter())) {
                    dir = Direction.WEST
                    break
                }
            }

            val (xn, yn) = dir.next(x, y)
            x = xn
            y = yn
            steps++

        } else if (nextSign == '|' || nextSign == '-' || nextSign.isLetter() || nextSign == '+') {

            if (currSign.isLetter()) {
                res.append(currSign)
            }

            x = x1
            y = y1
            steps++
        } else if (currSign.isLetter()) {
            res.append(currSign)

            if (nextSign == ' ') {
                println("Program ended!")
                break@loop
            }
            x = x1
            y = y1
            steps++

        } else {
            throw IllegalStateException("$currSign, $nextSign, $dir")
        }
    }

    println("$res")
    println("${steps + 1}")

}

private fun getC(cmds: List<String>, x1: Int, y1: Int) : Char {
    return if (y1 in 0 until cmds.size && x1 in 0 until cmds[y1].length) {
        cmds[y1][x1]
    } else {
        ' '
    }
}