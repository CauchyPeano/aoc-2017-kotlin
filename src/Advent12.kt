data class MyNode(val id: String, val rels: List<String>)

fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input12.txt").readText()

    val lines = readText.split("\r\n")

    val map = linkedMapOf<String, MyNode>()

    for (it in lines) {
        val split = it.split("<->")
        val first = split[0].trim()
        val rels = split[1].split(",").map { it.trim() }

        map.put(first, MyNode(first, rels))
    }

    var groups = 0
    while (!map.isEmpty()) {
        groups++
        val first = map.values.first()
        val traversed = getGroup(map, first)
        println("Size $groups = ${traversed.size}")

        traversed.forEach {
            val remove = map.remove(it.id) ?: throw IllegalStateException()
        }
    }

    print("Total groups = $groups")

}

private fun getGroup(map: HashMap<String, MyNode>, startItem: MyNode): MutableSet<MyNode> {

    val traversed = mutableSetOf<MyNode>()
    traversed.add(startItem)
    val toTraverse = mutableListOf<String>()
    toTraverse.addAll(startItem.rels)

    while (!toTraverse.isEmpty()) {

        val nextId = toTraverse.removeAt(0)

        val item = map[nextId] ?: throw IllegalStateException("Error ! $nextId")

        if (!traversed.contains(item)) {
            toTraverse.addAll(item.rels)
            traversed.add(item)
        }

    }
    return traversed
}