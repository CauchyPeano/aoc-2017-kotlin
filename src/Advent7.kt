
data class Node(val id : String, var weight : Int = 0, var total : Int = 0, val rels : MutableList<Node> = mutableListOf(), var parent : Node? = null)

fun main(args: Array<String>) {

    //ckavnt (68) -> umheofb, blsyfi, qijkhbz, ithmj

    val readText = args.javaClass.getResource("/input7.txt").readText()
    val lines = readText.split("\r\n")

    val nodes = hashMapOf<String, Node>()

    lines.forEach {
        val str = it.split(" ")[0]
        val n = Node(str)

        nodes.put(str, n)
    }

    lines.forEach {
        val s1 = it.split("->")
        val s2 = s1[0].split(" ")

        val id = s2[0]
        val parent = nodes[id]
        val weight = s2[1].removeSuffix(")").removePrefix("(").toInt()

        parent?.weight = weight

        if (s1.size > 1) {
            val items = s1[1].split(",").map { it.trim() }


            val childs = items.map {
                nodes[it]!!
            }

            for (nn in childs) {
                nn.parent = parent
            }

            parent?.rels?.addAll(childs)

//            val node = nodes[id]
//            node?.rels?.addAll(items.map { nodes[it]!! })
        }
    }

    val filterValues = nodes.filterValues { it.parent == null }
    val vals = filterValues.keys.first()
    val root = filterValues.values.first()

    println(vals)

    setWeights(root, 0)


}

fun setWeights(n: Node, level : Int) : Int {

    if (n.rels.isEmpty()) {
        n.total = n.weight
        return n.weight
    } else {
        val sum = n.rels.map { setWeights(it, level + 1) }.sum() + n.weight
        n.total = sum
        return sum
    }
}
