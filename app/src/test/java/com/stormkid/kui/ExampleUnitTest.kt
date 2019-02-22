package com.stormkid.kui

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        deCodeMd5("0B27E6F6753955F62FB42E390FAC5A117BFB0991")
    }

    fun hex(color:Int){
        var decimal = color
        var hex = ""
        while (decimal != 0) {
            val hexValue = decimal % 16
            hex = toHexChar(hexValue) + hex
            decimal /= 16
        }
        print(hex)
    }

    fun toHexChar(hexValue: Int): Char {
        return if (hexValue in 0..9)
            (hexValue + '0'.toInt()).toChar()
        else
            (hexValue - 10 + 'A'.toInt()).toChar()
    }

    fun deCodeMd5(value: String){
        
    }
}
