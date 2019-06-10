package codewars

fun partlist(arr:Array<String>):Array<Array<String>> {
    return Array(arr.size - 1) { count ->
        Array(2) {
            if (it == 0) {
                arr.sliceArray(0..count).joinToString(" ")
            } else {
                arr.sliceArray(count+1 until arr.size).joinToString(" ")
            }
        }
    }
}
