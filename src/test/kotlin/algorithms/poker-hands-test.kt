package algorithms

import algorithms.Rank.*
import algorithms.Suit.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class `poker-hands-test` {

    @Test
    fun howManyTimesThatPlayer1Wins_test() {
        time {
            assertEquals(376, howManyTimesPlayer1Won())
        }
    }

    @Test
    fun isStraightFlush_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isStraightFlush()
            )
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Four, Spades),
                                    Card(Five, Spades),
                                    Card(Eight, Spades),
                                    Card(Seven, Spades),
                                    Card(Six, Spades)
                            )
                    ).isStraightFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isStraightFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(King, Spades),
                                    Card(Two, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isStraightFlush()
            )
        }
    }

    @Test
    fun isRoyalFlush_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isRoyalFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isRoyalFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(King, Spades),
                                    Card(Two, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isRoyalFlush()
            )
        }
    }

    @Test
    fun isFourOfAKind_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Hearts),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFourOfAKind()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFourOfAKind()
            )
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades)
                            )
                    ).isFourOfAKind()
            )
        }
    }

    @Test
    fun isThreeOfAKind_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Ten, Hearts),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isThreeOfAKind()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isThreeOfAKind()
            )
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades)
                            )
                    ).isThreeOfAKind()
            )
        }
    }

    @Test
    fun isTwoPairs_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Diamonds),
                                    Card(Rank.Queen, Hearts),
                                    Card(Ten, Hearts),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isTwoPairs()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isTwoPairs()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Ten, Spades)
                            )
                    ).isTwoPairs()
            )
        }
    }

    @Test
    fun isOnePairs_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Hearts),
                                    Card(Ten, Hearts),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isOnePair()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Ten, Diamonds),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isOnePair()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Two, Spades),
                                    Card(Rank.Nine, Spades)
                            )
                    ).isOnePair()
            )
        }
    }

    @Test
    fun isFullHouse_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Queen, Hearts),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFullHouse()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFullHouse()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(King, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades)
                            )
                    ).isFullHouse()
            )
        }
    }

    @Test
    fun isStraight_test() {
        time {
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Three, Spades),
                                    Card(Rank.Four, Diamonds),
                                    Card(Six, Spades),
                                    Card(Eight, Hearts),
                                    Card(Seven, Spades)
                            )
                    ).isStraight()
            )
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Five, Spades),
                                    Card(Rank.Four, Diamonds),
                                    Card(Six, Spades),
                                    Card(Eight, Hearts),
                                    Card(Seven, Spades)
                            )
                    ).isStraight()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Five, Spades),
                                    Card(Five, Diamonds),
                                    Card(Six, Spades),
                                    Card(Eight, Hearts),
                                    Card(Seven, Spades)
                            )
                    ).isStraight()
            )
        }
    }

    @Test
    fun isFlush_test() {
        time {
            assertTrue(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Diamonds),
                                    Card(King, Spades),
                                    Card(Rank.Jack, Spades),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).isFlush()
            )
            assertFalse(
                    Hand(
                            listOf(
                                    Card(Ten, Hearts),
                                    Card(King, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades),
                                    Card(Ten, Spades)
                            )
                    ).isFlush()
            )
        }
    }

    @Test
    fun getTheHighestRank_test() {
        time {
            assertEquals(
                    Rank.Ace,
                    Hand(
                            listOf(
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Ace, Spades),
                                    Card(Rank.Queen, Spades),
                                    Card(Rank.Queen, Spades)
                            )
                    ).getHighestRank()
            )

            assertEquals(
                    Rank.Queen,
                    Hand(
                            listOf(
                                    Card(Ten, Spades),
                                    Card(Rank.Queen, Spades),
                                    Card(Six, Spades),
                                    Card(Ten, Spades),
                                    Card(Two, Spades)
                            )
                    ).getHighestRank()
            )
        }
    }

    @Test
    fun whoWins_test() {
        time {
            val hand1 = Hand(listOf(
                    Card(Five, Hearts),
                    Card(Five, Clubs),
                    Card(Six, Spades),
                    Card(Seven, Spades),
                    Card(King, Diamonds)
            ))
            val hand2 = Hand(listOf(
                    Card(Two, Clubs),
                    Card(Three, Spades),
                    Card(Eight, Spades),
                    Card(Eight, Diamonds),
                    Card(Ten, Diamonds)
            ))
            assertEquals(hand2, hand1.whoWins(hand2))
            assertEquals(hand2, hand2.whoWins(hand1))

            val hand3 = Hand(listOf(
                    Card(Five, Diamonds),
                    Card(Eight, Clubs),
                    Card(Nine, Spades),
                    Card(Jack, Spades),
                    Card(Ace, Clubs)
            ))
            val hand4 = Hand(listOf(
                    Card(Two, Clubs),
                    Card(Five, Clubs),
                    Card(Seven, Diamonds),
                    Card(Eight, Spades),
                    Card(Queen, Hearts)
            ))
            assertEquals(hand3, hand3.whoWins(hand4))
            assertEquals(hand3, hand4.whoWins(hand3))

            val hand5 = Hand(listOf(
                    Card(Two, Diamonds),
                    Card(Nine, Clubs),
                    Card(Ace, Spades),
                    Card(Ace, Hearts),
                    Card(Ace, Clubs)
            ))
            val hand6 = Hand(listOf(
                    Card(Three, Diamonds),
                    Card(Six, Diamonds),
                    Card(Seven, Diamonds),
                    Card(Ten, Diamonds),
                    Card(Queen, Diamonds)
            ))
            assertEquals(hand6, hand6.whoWins(hand5))
            assertEquals(hand6, hand5.whoWins(hand6))

            val hand7 = Hand(listOf(
                    Card(Four, Diamonds),
                    Card(Six, Spades),
                    Card(Nine, Hearts),
                    Card(Queen, Hearts),
                    Card(Queen, Clubs)
            ))
            val hand8 = Hand(listOf(
                    Card(Three, Diamonds),
                    Card(Six, Diamonds),
                    Card(Seven, Hearts),
                    Card(Queen, Diamonds),
                    Card(Queen, Spades)
            ))
            assertEquals(hand7, hand7.whoWins(hand8))
            assertEquals(hand7, hand8.whoWins(hand7))

            val hand9 = Hand(listOf(
                    Card(Two, Hearts),
                    Card(Two, Diamonds),
                    Card(Four, Clubs),
                    Card(Four, Diamonds),
                    Card(Four, Spades)
            ))
            val hand10 = Hand(listOf(
                    Card(Three, Clubs),
                    Card(Three, Diamonds),
                    Card(Three, Spades),
                    Card(Nine, Spades),
                    Card(Nine, Diamonds)
            ))
            assertEquals(hand9, hand9.whoWins(hand10))
            assertEquals(hand9, hand10.whoWins(hand9))
        }
    }
}
