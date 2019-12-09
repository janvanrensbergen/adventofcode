package be.moac.adventofcode

object DayEight {

    fun checksum(image: Image): Int {

        val layers = mutableListOf<String>()

        for(i in image.value.indices step (image.width * image.height)) {
            layers.add(image.value.slice(i until (i + (image.width * image.height))))
        }

        val selectedLayer = layers.minBy { layer -> layer.count { it == '0' } } ?: ""
        return selectedLayer.count { it == '1' } * selectedLayer.count { it == '2' }
    }

}

data class Image(val value: String, val width: Int, val height: Int)
