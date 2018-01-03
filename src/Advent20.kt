import java.math.BigInteger
import kotlin.test.assertTrue

data class Point(var id: Int, var x : BigInteger, var y : BigInteger, var z : BigInteger) {
    fun add(other : Point) {
        x += other.x
        y += other.y
        z += other.z
    }
    fun dist() : BigInteger {
        return x.abs() + y.abs() + z.abs()
    }
    fun eq(other : Point) : Boolean {
        return other.x == x && other.y == y && other.z == z
    }
}

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input20.txt").readText()

    val lines = readText.split("\r\n")


    val pnts = lines.withIndex().map { (idx, line) ->

        val r = Regex("[\\w]=<([-]?\\d+),([-]?\\d+),([-]?\\d+)>")
        val findAll = r.findAll(line)

        val list = mutableListOf<Point>()
        for (matchResult in findAll) {
            val (x, y, z) = matchResult.destructured

            val p = Point(idx, x.toBigInteger(), y.toBigInteger(), z.toBigInteger())
            list.add(p)
        }
        list
    }.toMutableList()

    assertTrue(pnts.map { it -> it.size }.all { it == 3 })


    for (i in 0..2_000_000) {
        tick(pnts)
        println("${closest(pnts)} - total ${pnts.size}")
//        println(pnts.map { it[0].dist() }.joinToString())
    }


//    println(pnts.size)

}

fun closest(pnts: List<MutableList<Point>>) : Int {

    var closest = BigInteger.valueOf(Long.MAX_VALUE)
    var res = 0
    for (list in pnts) {
        val dist = list[0].dist().abs()
        if (dist < closest) {
            closest = dist
            res = list[0].id
        }
    }
    return res

}

fun tick(pnts: MutableList<MutableList<Point>>) {

    val groupBy = pnts.map { it[0] }.groupBy { p -> p.x.toString() + "," + p.y.toString() + "," + p.z.toString() }
    val filterValues = groupBy.filterValues { it.size > 1 }

    filterValues.values
            .flatMap { it }
            .forEach { e -> pnts.removeIf { it[0].id == e.id } }

    pnts.forEach {
        val a = it[2]
        val v = it[1]
        val p = it[0]

        v.add(a)
        p.add(v)
    }

}
