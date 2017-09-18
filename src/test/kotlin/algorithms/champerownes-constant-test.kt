package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `champerownes-constant-test` {

    @Test
    fun champerowne_s_constant_test() {
        // 0.1234567891 // 10
        //   0111213141 // 20
        //   5161718192 // 30
        //   0212223242 // 40
        //   5262728293 // 50
        //   0313233343 // 60
        //   5363738394 // 70
        //   0414243444 // 80
        //   5464748495 // 90
        //   0515253545 // 100
        //   5565758596 // 110
        //   0616263646 // 120
        //   5
        time { assertEquals(5, champerownesDigitAtIndex(5)) }
        time { assertEquals(0, champerownesDigitAtIndex(11)) }
        time { assertEquals(1, champerownesDigitAtIndex(12)) }
        time { assertEquals(1, champerownesDigitAtIndex(20)) }
        time { assertEquals(2, champerownesDigitAtIndex(30)) }
        time { assertEquals(4, champerownesDigitAtIndex(99)) }
        time { assertEquals(5, champerownesDigitAtIndex(102)) }
        time { assertEquals(4, champerownesDigitAtIndex(119)) }
        time { assertEquals(6, champerownesDigitAtIndex(120)) }

        // 0.123456789<1>0
        time { assertEquals(1, champerownesDigitAtIndex(10)) }
        time { assertEquals(5, champerownesDigitAtIndex(100)) }
        time { assertEquals(3, champerownesDigitAtIndex(1000)) }
        time { assertEquals(7, champerownesDigitAtIndex(10000)) }
        time { assertEquals(2, champerownesDigitAtIndex(100000)) }
        time { assertEquals(1, champerownesDigitAtIndex(1000000)) }

        //d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
        time {
            assertEquals(
                    210,
                    champerownesDigitAtIndex(1) *
                    champerownesDigitAtIndex(10) *
                    champerownesDigitAtIndex(100) *
                    champerownesDigitAtIndex(1000) *
                    champerownesDigitAtIndex(10000) *
                    champerownesDigitAtIndex(100000) *
                    champerownesDigitAtIndex(1000000)
            )
        }
    }
}
