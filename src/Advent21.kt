

fun main(args: Array<String>) {
    val readText = args.javaClass.getResource("/input21.txt").readText()

    val cmds = readText.split("\r\n")

    val beginPattern = ".#./..#/###"
    val split = beginPattern.split("/")

    var mtx = split.map { it -> it.toCharArray() }.toTypedArray()

    val dict = hashMapOf<String, String>()

    for (c in cmds) {
        val split1 = c.split(" => ")
        val pattern = split[0]
        val value = split[1]
        dict.put(pattern, value)
    }

    for (i in 0..1) {

        if (mtx.size % 3 == 0) {




        } else if (mtx.size % 2 == 0) {

        }

    }

}