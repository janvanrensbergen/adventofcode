package be.moac.adventofcode

object DayEight {

    fun checksum(image: Image): Int =
        with(image.layers.minBy { layer -> layer.count { it == '0' } } ?: "") {
            count { it == '1' } * count { it == '2' }
        }

    fun render(image: Image): String =
        image.layers.fold(image.size.asString("2")) {result, layer ->
            result.zip(layer).map { (left, right) ->
                when(left) {
                    '2' -> right
                    else -> left
                }
            }.joinToString(separator = "")
        }.also {
            for(i in it.indices step (image.width)) {
                println(it.slice(i until (i + (image.width))).map {
                    when(it) {
                        '0' -> '.'
                        '1' -> '0'
                        else -> ' '
                    }
                })
            }
        }

}

data class Image(val value: String, val width: Int, val height: Int) {

    val size = (width * height)

    val layers get() =  mutableListOf<String>().apply {
        for(i in value.indices step (width * height)) {
            add(value.slice(i until (i + (width * height))))
        }
    }.toList()



}

fun Int.asString(value: String): String =
    (0 until this).joinToString(separator = "") { value }

