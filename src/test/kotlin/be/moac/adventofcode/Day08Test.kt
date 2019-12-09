package be.moac.adventofcode

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day08Test {

    @Test
    fun `that correct value is calculated`() {

        //Given:
        val input = "123456789012"

        //When:
        val result = DayEight.checksum(Image(input, 3, 2))

        //Then:
        assertThat(result).isEqualTo(1)

    }


    @Test
    fun `part one`() {

        //Expect:
        assertThat(DayEight.checksum(Image(input, 25, 6))).isEqualTo(1950)
    }

    private val input = "122222022202200222222222222222201222222222220122222222222002222222221012220222221222222222222222202222220202221222222212222202222212222222222022022222222222022222200222202222222222210222222222222022222222222022222222222102221222222222222222222222222222221212221222222222222222222222222222222222222222022222022212222222212222222222222222222222221022222222222002222222221122202222220222222222222222202222222212222222222202222212222202222222222122222222022222122212222222202222222222200222222222221222222222222122220222222122212222222222221222222222222222221222222222222202222222222200222222222222122222022222122212202222222222222222200222222222221222222222221102220222220022212222222222221222222222222222221222220222222222222212222220222222221222222222122222122202202222222222222222202222222222222022222222222002221222220222210222221222222222222222222222221212222222222222222212222222222222222222222222022222022202122222202222222222202222222222220122222222220012221222221222202222220222222222222222212222221212221222222222222212222200222222221222022222122222122222022222222222222222221222222212222202222222221122222222121212210222222222222222222222222222222212220222222212222212222222222222221022122222122222222222112222222222222222202222222212222112222222221202220222020002211222222222221222222222222222220222221222222202222202222202222222220122122222222222222222212222212222222222222222222202220122222222222222221222121212211222021222222222222222202222220212221222212222222202222211222222220122222222022222022212210222222222222222212222222222222002222222222112220222122212210222220222221222222222212222220222222222222212222202222212222222222222222222222222122222101222222222222222201221222202221202222222220222221222020012221222021222222222222222212222222202221222222212222212222202222222222122122222122222122202121222212222222222200222222202222002222222221022221222221212201222022222221222220222222222222202220222222202222202222222222222222222122222122222022202221222222222222222201221222212211222222222222202222222022102222202122222222222220222212222220222220222202202222202222201222222222122222222022222222212212222202222222222221220222202202112222222220102222222020112210222221222222222220222212222220202222222202212222202222221222222222222222222022222222222022222202222222222200222222212221002222222222022220222121022222222020222221222222222212222221222221222202202222202222202222222220222022222121222022202220222202222222222200221222202212102222222222002220222221112202212022222222222222222212222220202220222202202222222222202222222220122222222221222122202202222202222222222222222222222220102222222220012222222121212212222222222222222221222222222220222221222202212222212222210222222221122222222121222122212000222212222222222211222222222221222222222220202222222221002220212121222220220221222212222222222220222202212222202222201222222222022222222120222222212200222202222222222200220222222221002222222220102222222020122222222021222220221220222212222201212220222222222222212222222222222220122122222021222222212201222222222222222200222222222220002222222222212222222120222201212020222221220220222222222201222221222212202222222222221222222222022122222120222022212201222222222222222210222222222210202222222221102220222021202202222020222220221222222212222202222222222212222222212222202212222221222022222221222222202102222212222222222221222222212221122222222221022222222220112211102020222220120221222222222211212222222210202222202220222202222220222122222222222022212010222202222222222220221222212210112222222221022220222122122211022020222220221220222222222221202222222221222222212201201212222220022022222221202122222020222212222222222222220222202220012222222220012222222122012211022021222222020221222202222201222221222200202222202201221222122220022022222121202122212111222202222222222220220222222211222222222222222120222221112202112222222222121221222202222201212221221221212222212211200222022220122222222022222222222002222212222222222201221222220210002222222222202020222122202200112021222210021220222212222201212221220200222222222221222202022220222022222121202122212020222212222222222210222222211210022222222222122222222220002210022120222212020220222012222211222222221220222222212210220222222221222122222221212022212000222222222222222210222222210202012222222222112120222120012222212021222211122222222122222221202222222212222222212200211202022222022222222120212122212110222202222222222110222222220222112222222222012221222220012201222222222210022222222212222221222220221202222222212211200212022221222122212122212022212120222202222222222220222222210202102222222220002120222222202200122122222221210222222212222210202221222200212222212200210202022222222222222220222022202100222222222222222121221222210222222222222220102121222020112210112221222002122222222220222222202222220221222222222221202222222221222022212222212122212002222212222222222200222222220202122202222222022121222120222211222222222121100222222110222221212222220201222222222200200202122222122122212021202122222021222202222222222022220222212212202212222220012221222022102220212222222022110222222200222211202221221201212222212212200212122221221022212221202222212102122212022222222211222222222201102212222220212220222221022212002222222022102220222000222200212221221220212222202200210222122221220022212221112222212001022212122022222120220222200200012202222220022021221210102221212022022120221222222201222222222222222221222222212202211212022222122122212220002022202112222212122222222110221222200220122222222221012222220101202221022121122112000220222111222201202220220211222222222201212202122222222222212122202022202110122202022122222220220222200202222212222221212222222011222212012021022201012221222102222211202220222201202222222202211222022220120222202021122022222222122212022122222000222222200222202212222220012122221210102212222021122010221222222212222200202221220222202222212210201222122221121022222222002222202100122222122122202101221222202202202222222221102020222201202200012122222112122221222222222221212220220200212222212201210222022220120122222121202022212110122222022122222202220222220221222212222222022220221022112202222220122121220221222000222210202220221220202222202201201202122220122222222020202022202211122222122022202102222222220211012202222220002222222210122020002222122010202222222221222212222220222222212222222201202222022222220222212221222122212101122222022122212102220222200220122212222221122220221122212000212122022202220120222100222202222222221202212222222220212212122221022022202022112222202210122222022222202101222222210200212202222222002021121001002110012221122202122022222102222210222222201212212222202221210222022221122022212020102022222212122222122122212111220222210202022221222221212122121221022201112022222221211221222102222211222220221200202222222211220212022222222022202021102222222201122212022022202222221221211220122222222221102120222110202010102022222101012020222221222222202220212202212222222200202212222220122222202020022122222212222212122022222201221220212202222222222221012222222000212001002121022210211222222202222201202220210201202222202222202012022220022022212222122222222120222202222122202221222222212200122200222222100122220111212220202020222221001120222200222222202221221211202222212211212122022220022122202122122022212022122222022122222202221021202212212201222222210221220020112202222221222221001222222111222210202222212211212222202212210002122122122122222222112022212021022222022122222202221120221201112200222222012120122100202110002020212201010221222110222220212222210212212222222220201102022120022222202121122122202222222202022222222101221022202201212220222220220022022221222012022020122111101220222100222200222221211202222222222210202012122022020222212220202022202102022222022222202211220121222222222210222220001220200210002202002222002102011020222210222202212220222202202222212210220202122220121022202220202022202211102202222222202102221122211222002201222222012021212210202002112222122222111020222120222202202021211210222222222201222212122220021022222021102122212021012212022122222202222121200201012210222221122222002012002202222121002001211121222200222201202122202212222222212210202112222022122022212120112022222021022222122022202020222020211212102210222222011221012012012220022222202121202120222012222210222221221201222222202212212012122020020222202022112022222110102222122222222202220222202201012211222220210022201001202011202020102011000221202021222210212120200220222222212212201022022220122222202122222122212220002212222222212012220122200210022210222222120122210101112001202121222122210122212121222212212220221222202222222211212122122021020222202021202122222100102202122122202002222120221221212021222220000222202212202020002122102122021222202211220211222122200200212222212201210112222122220222202021202122202200122222022220222002222022202202022121222220121121201200012202202022002022002220202001221202212121200201222222222212220112022021121022212221212122212010122202222222202022220021221202212101222222202122202122112111102021022122120022222200220202202021211212222222212221212212222222221122212222202022222021022202022020022112222222222212102210222222000222012212122010212022012101102220222011221211202222221201202222202222220112122120121022222020222022212012012222122222022121220021210210122220222222010222112211112202102120022020212022222100221221102101220200202222222222220022022021220222202021011022212121102212222220122112222120220221222100220222101022012020202211102120212221002020212102222201022010200221212222202221200002222020020222222110110022212222112202222220022100220222200222102111221220010020210022212001122122022200110222212010220220202120201202222222212222212202122122121022202100210022222021022212122222212211222222022220122002222222001021122102222021122020122111201022202210221221102010202221222222222212212202122021021222202201210022202022212222222020112020222222212220102200220222021121001012022112102120012110110021222110220200122111212220202222222222220002122021221022222211122222022210202212222022202121221222012212012021222221112122221222122220222122002112221021202201022202212121212211212222222200212012122020120122212111010022122011122202122221222122220221021220022221221222110020200101122210222021010221120221212011020221112210210222222222202200211202022122120122222122211122002220202222122020112100222021012220212112220221022220001010212100212220011210112121212201222221101010221212222222212211220122220120021022202210221222002010222222222020212102221122201220102000220220020121120002212101112222201002121120222122121201002222201211222222202201200112221121021212212022222222122021002202022121002200220021000212222221220220120021210212112121202022010111112121222222121200110221202212222222202222210202120221121202222002100022102200222202122220112111220120110202112012221220220222211110220212102221022112220202212200220220101011222222212222212221222212021220122222202100000022122100022202022222222021220022120221022100221222221220111121120201122122112221211012212220022200222101220220202222222200202212020122120202222020010222012101002202122220112122221020210202202120222222102022122200100022222122101022102021212111222212012010212212202222221210220122021222122012212220102022102210222202022022222110222020202200002122222222111222011002101110102222000120121002222021021222112102221221212222222221220002022020021202222020000022122102202202222121212102222221120212202122221221221022002220011200112222112010120101212111220212210111220202212222212210201222022020120012222110220222212011012212222222022110222022022222122001220222010220101122211001022120222111011222202002222211221200200221222222220210202012122220120102222222112022102220122202122021002010221220022221202020221222202022021001111121112020220002010002121120221212112200222222202222212201201122222121022112212020022222002210122222022121112121221222020201212222221221202120001000120012202120101220101022002210220221012100222211202222220212210012121220020002202002221222202102222212222022102122222122210200012210120220111222111210200220112222210111221021222200022221122012200201222222202010222202020121021212222120110222212001202222022020122022221121110220112000021220022022001210111211002222011210022210202021220222000202201221222222220012212212222120022222212202022222202212112202122220112121222122210201212120220220211222021222222211222220210012201202222002122200212112212211202222220202200012222221022212212220110022022112202202222122022120220222122022102210120221100221120121220102102022202202012202012002121220001120222220222222210001200202120021120222202212201122112201122222122021012221220222121211122020022221101021211202011200122121121210012200011110220200202011212222212222202202222012221020122222222201100222222011212212022220102120221122001212202021222222001221221201122001202121221100112211111120021202121121220201212222211000210122122022022102202202220022212002102222122220102011222221212202212200122220021221222112022211220022222222021211100212221202111212211222212222201211222022022222120012222221122222002210022202122020122200220120121112112220022021020020222012111012202022222001011220122211021222000200210212202222211000210122121120120202222112000222001010222212222222202022220222101120002010121122210220101020020120002222100021002200022222222212002210210212202222222022222102221121020212202020202122021122222212022222112021221220221220222022120210220221212012021112201220021001012220020222222210122222200210202222020210201222121120222022222112012022002201212212122020012012220121101210012101021100221122022102200100210020100210121101211100222200110000202222222222012222221212122121020122212121211022211121002222122221222210222221101100002211221100202122121020100012121000110221012112010222020210221200210202212222100102201022020121222222202111010122210000222212022122022110221001202122012001122120012120110100212020220211100110022112011111120220200221202220202222220121222122122020220012202202210122200020022222222120102220221221110110202202022022200021111000212112212101221010100101111121121210110220212220222222121212210100020220021112112211100122002202222222222021202200222021012222202112221212002021101212011200202212221011211120110100221211112111202221222222021212202222121220020222002212000222000012002222222220202021220101220001202221220101001222110202100001220021022002121222000200120220211120002221212222001202210002122022121222112210112122220221002212222020202210221212202001202020220121202222201212202100002010210210101222222001020010210021221200222222100020200200220222021122122112221222200220122212222222012020220020101110122121221222021021101110022110210120022021120000122210222102111201020200222222111112220121222222220102112000110022001100212222022020022010221021222120022210020010100120121002200200222211121100212212222021220000100122100221222222212001210002222021021012122100000222110020222212222221200120220222222102002120120210001020220222220021212111201000002200022201221010110201110221212222201200211112220220020012002202122201010101120101100212200022002201022221110211211002211201222121011020200211012111011002002120011010120211121000010100220021020010200001200201200"
}
