fun main(args: Array<String>) {

    val readText = args.javaClass.getResource("/input9.txt").readText()

    val lines = readText.split("\r\n")

//    solve("{}")
//    solve("{{{}}}")
//    solve("{{{},{},{{}}}}")
//    solve("{<a>,<c>,<d>}")
//    solve("{{<ab>},{<ab>},{<ab>},{<ab>}}")
//    solve("{{<!!>},{<!!>},{<!!>},{<!!>}}")
//    solve("{{<a!>},{<a!>},{<a!>},{<ab>}}")

    lines.forEach {
        solve(it)
    }

}

fun solve(st : String) {

    val st2 = st.replace(Regex("!."), "")
    val symbols = st2.toCharArray()

    var open = 0
    var garbage = false
    var score = 0
    var gc = 0
    for (c in symbols) {

        if (!garbage) {
            when (c) {
                '{' -> {
                    open++
                    score += open
//                    println("Add score $open")
                }
                '}' -> open--
                '<' -> garbage = true
            }
        } else {
            when (c) {
                '>' -> garbage = false
                else -> gc++
            }
        }

    }
    println("Score = $score")
    println("Garbage = $gc")

}
