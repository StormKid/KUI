package com.stormkid.kui

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun hex(){
        var decimal = 195
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
}
