package algorithms

import java.io.File

/*
Poker hands
Problem 54
In the card game poker, a hand consists of five cards and are ranked,
from lowest to highest, in the following way:

High Card: Highest value card. For ties, the next highest, the next highest, etc // lowest
One Pair: Two cards of the same value. For ties, the highest pair wins, then the highest side card, then the next, etc.
Two Pairs: Two different pairs. first highest pair, then second highest pair, then fifth.
Three of a Kind: Three cards of the same value. The tie breaker is who has the highest card of the threes, then the next highest, then the next highest
Straight: All cards are consecutive values. For ties, the highest card wins. Note ace can be (1, 2, 3, 4, 5) or (10, J, Q, K, A)
Flush: All cards of the same suit. For ties, the highest card wins, then the second highest, etc.
Full House: Three of a kind and a pair. The higher of the threes wins. Then the higher of the pair
Four of a Kind: Four cards of the same value. The tie breaker is the higher of the fours! then the higher of the fifth
Straight Flush: All cards are consecutive values of same suit. The tie breaker is the highest card!, if same, no further breaks
Royal Flush: Ten, Jack, Queen, King, Ace, in same suit. No ties allowed

The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace. // highest

If two players have the same ranked hands then the rank made up of the highest value wins;
for example, a pair of eights beats a pair of fives (see example 1 below).
But if two ranks tie, for example, both players have a pair of queens,
then highest cards in each hand are compared (see example 4 below); if the highest
cards tie then the next highest cards are compared, and so on.

Consider the following five hands dealt to two players:

Hand	 	Player 1	 	     Player 2	 	        Winner
1	 	    5H 5C 6S 7S KD  	 2C 3S 8S 8D TD    	    Player 2
            Pair of Fives        Pair of Eights

2   	 	5D 8C 9S JS AC       2C 5C 7D 8S QH         Player 1
            Highest card Ace     Highest card Queen

3   	 	2D 9C AS AH AC       3D 6D 7D TD QD         Player 2
            Three Aces           Flush with Diamonds

4   	 	4D 6S 9H QH QC       3D 6D 7H QD QS   	    Player 1
            Pair of Queens       Pair of Queens
            Highest card Nine    Highest card Seven

5   	 	2H 2D 4C 4D 4S       3C 3D 3S 9S 9D         Player 1
            Full House           Full House
            With Three Fours     with Three Threes


The file, poker.txt, contains one-thousand random hands dealt to two players.
Each line of the file contains ten cards (separated by a single space):
the first five are Player 1's cards and the last five are Player 2's cards.
You can assume that all hands are valid (no invalid characters or repeated cards),
each player's hand is in no specific order, and in each hand there is a clear winner.


How many hands does Player 1 win?
 */
enum class Suit {
    Clubs, Diamonds, Hearts, Spades
}

enum class Rank {
    Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
}


data class Card(val rank: Rank, val suit: Suit) {
    object CardRankComparator : Comparator<Card> {
        override fun compare(a: Card, b: Card): Int = b.rank.ordinal - a.rank.ordinal
    }
}

data class Hand(private val cards: List<Card>) {
    private fun isSameSuit(): Boolean = cards
            .map { it.suit }
            .distinct()
            .count() == 1

    fun isStraight(): Boolean = isConsecutive()

    fun isFlush(): Boolean = isSameSuit()

    fun isRoyalFlush(): Boolean =
            isSameSuit() &&
                    cards.map { it.rank }
                            .containsAll(
                                    setOf(
                                            Rank.Ten,
                                            Rank.Jack,
                                            Rank.Queen,
                                            Rank.King,
                                            Rank.Ace
                                    )
                            )

    fun isStraightFlush(): Boolean = isSameSuit() && isConsecutive()

    // A, 2, 3, 4, 5 is a sequence with Ace at the beginning
    // 10, J, Q, K, A is a sequence with Ace at the end
    private fun isConsecutive(): Boolean {
        val sortedCards = cards.sortedBy { it.rank.ordinal }
        return generateSequence(1) { it + 1 }
                .take(cards.size - 1)
                .all { sortedCards[it].rank.ordinal - sortedCards[it - 1].rank.ordinal == 1 }
                .or(
                        cards.map { it.rank }.containsAll(
                                listOf(Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)
                        )
                )
                .or(
                        cards.map { it.rank }.containsAll(
                                listOf(Rank.Ace, Rank.Two, Rank.Three, Rank.Four, Rank.Five)
                        )
                )
    }

    fun isFourOfAKind(): Boolean = cards
            .groupBy { it.rank }
            .any { it.value.size == 4 }

    fun isThreeOfAKind(): Boolean = oneTriple() && noPairs()

    private fun oneTriple(): Boolean = cards
            .groupBy { it.rank }
            .count { it.value.size == 3 } == 1

    private fun noPairs(): Boolean = cards
            .groupBy { it.rank }
            .none { it.value.size == 2 }

    fun isFullHouse(): Boolean = cards
            .groupBy { it.rank }
            .all { it.value.size == 3 || it.value.size == 2 }

    fun isTwoPairs(): Boolean = cards
            .groupBy { it.rank }
            .count { it.value.size == 2 } == 2

    fun isOnePair(): Boolean = cards
            .groupBy { it.rank }
            .count { it.value.size == 2 } == 1


    fun getHighestRank(): Rank = cards
            .sortedBy { it.rank.ordinal }
            .last()
            .rank

    private fun type(): HandType = when {
        this.isRoyalFlush() -> HandType.RoyalFlush
        this.isStraightFlush() -> HandType.StraightFlush
        this.isFourOfAKind() -> HandType.FourOfAKind
        this.isFullHouse() -> HandType.FullHouse
        this.isFlush() -> HandType.Flush
        this.isStraight() -> HandType.Straight
        this.isThreeOfAKind() -> HandType.ThreeOfAKind
        this.isTwoPairs() -> HandType.TwoPairs
        this.isOnePair() -> HandType.OnePair
        else -> HandType.HighestCard
    }

    fun whoWins(that: Hand): Hand {
        val thisHandType = this.type()
        val thatHandType = that.type()
        return when {
            thisHandType > thatHandType -> this
            thisHandType < thatHandType -> that
            else -> thisHandType.breakTie(this, that)
        }
    }

    enum class HandType {
        HighestCard {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val result = HandRankComparator.compare(hand1, hand2)
                return when {
                    result < 0 -> hand1
                    result > 0 -> hand2
                    else -> throw IllegalStateException()
                }
            }
        },
        OnePair {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val hand1PairRank = hand1.cards
                        .groupBy { it.rank }
                        .filter { it.value.size == 2 }
                        .map { it.key }
                        .first()
                val hand2PairRank = hand2.cards
                        .groupBy { it.rank }
                        .filter { it.value.size == 2 }
                        .map { it.key }
                        .first()

                val hand1Singles = hand1.cards
                        .groupBy { it.rank }
                        .filter { it.value.size == 1 }
                        .values
                        .flatMap { it }
                val hand2Singles = hand2.cards
                        .groupBy { it.rank }
                        .filter { it.value.size == 1 }
                        .values
                        .flatMap { it }

                // create a hand and use the highest rank from the three cards left
                val result = HandRankComparator.compare(Hand(hand1Singles), Hand(hand2Singles))

                return when {
                    hand1PairRank > hand2PairRank -> hand1
                    hand1PairRank < hand2PairRank -> hand2
                    result < 0 -> hand1
                    result > 0 -> hand2
                    else -> throw IllegalStateException()
                }
            }
        },
        TwoPairs {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val hand1Pairs = hand1.cards.groupBy { it.rank }.filter { it.value.size == 2 }
                val hand2Pairs = hand1.cards.groupBy { it.rank }.filter { it.value.size == 2 }

                val hand1SingleRank = hand1.cards.groupBy { it.rank }.filter { it.value.size == 1 }.entries.first().key
                val hand2SingleRank = hand1.cards.groupBy { it.rank }.filter { it.value.size == 1 }.entries.first().key

                return when {
                    hand1Pairs.maxBy { it.key }!!.key > hand2Pairs.maxBy { it.key }!!.key -> hand1
                    hand1Pairs.maxBy { it.key }!!.key < hand2Pairs.maxBy { it.key }!!.key -> hand2
                    hand1Pairs.minBy { it.key }!!.key > hand2Pairs.minBy { it.key }!!.key -> hand1
                    hand1Pairs.minBy { it.key }!!.key < hand2Pairs.minBy { it.key }!!.key -> hand2
                    hand1SingleRank > hand2SingleRank -> hand1
                    hand1SingleRank < hand2SingleRank -> hand2
                    else -> throw IllegalStateException("could not break tie for Two Pairs $hand1, $hand2")
                }
            }
        },
        ThreeOfAKind {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val hand1GroupedByRank = hand1.cards.groupBy { it.rank }
                val hand2GroupedByRank = hand2.cards.groupBy { it.rank }

                val hand1ThreeCardRank = hand1GroupedByRank.filter { it.value.size == 3 }.keys.first()
                val hand2ThreeCardRank = hand2GroupedByRank.filter { it.value.size == 3 }.keys.first()

                val hand1OneCardRank = hand1GroupedByRank.filter { it.value.size == 1 }.keys
                val hand2OneCardRank = hand2GroupedByRank.filter { it.value.size == 1 }.keys

                return when {
                    hand1ThreeCardRank > hand2ThreeCardRank -> hand1
                    hand1ThreeCardRank < hand2ThreeCardRank -> hand2
                    hand1OneCardRank.first() > hand2OneCardRank.first() -> hand1
                    hand1OneCardRank.first() < hand2OneCardRank.first() -> hand2
                    hand1OneCardRank.last() > hand2OneCardRank.last() -> hand1
                    hand1OneCardRank.last() < hand2OneCardRank.last() -> hand2
                    else -> throw IllegalStateException()
                }
            }
        },
        Straight {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand = HighestCard.breakTie(hand1, hand2)
        },
        Flush {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand = HighestCard.breakTie(hand1, hand2)
        },
        FullHouse {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val hand1GroupedByRank = hand1.cards.groupBy { it.rank }
                val hand2GroupedByRank = hand2.cards.groupBy { it.rank }

                val hand1ThreeCardRank = hand1GroupedByRank.filter { it.value.size == 3 }.keys.first()
                val hand2ThreeCardRank = hand2GroupedByRank.filter { it.value.size == 3 }.keys.first()

                val hand1TwoCardRank = hand1GroupedByRank.filter { it.value.size == 2 }.keys.first()
                val hand2TwoCardRank = hand2GroupedByRank.filter { it.value.size == 2 }.keys.first()

                return when {
                    hand1ThreeCardRank > hand2ThreeCardRank -> hand1
                    hand1ThreeCardRank < hand2ThreeCardRank -> hand2
                    hand1TwoCardRank > hand2TwoCardRank -> hand1
                    hand1TwoCardRank < hand2TwoCardRank -> hand2
                    else -> throw IllegalStateException("could not break tie for Full House $hand1, $hand2")
                }
            }
        },
        FourOfAKind {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand {
                val hand1GroupedByRank = hand1.cards.groupBy { it.rank }
                val hand2GroupedByRank = hand2.cards.groupBy { it.rank }

                val hand1FourCardRank = hand1GroupedByRank.filter { it.value.size == 4 }.keys.first()
                val hand2FourCardRank = hand2GroupedByRank.filter { it.value.size == 4 }.keys.first()

                val hand1OneCardRank = hand1GroupedByRank.filter { it.value.size == 1 }.keys.first()
                val hand2OneCardRank = hand2GroupedByRank.filter { it.value.size == 1 }.keys.first()

                return when {
                    hand1FourCardRank > hand2FourCardRank -> hand1
                    hand1FourCardRank < hand2FourCardRank -> hand2
                    hand1OneCardRank > hand2OneCardRank -> hand1
                    hand1OneCardRank < hand2OneCardRank -> hand2
                    else -> throw IllegalStateException("could not break tie for Four Of A Kind $hand1, $hand2")
                }
            }
        },
        StraightFlush {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand =
                    when {
                        hand1.getHighestRank() > hand2.getHighestRank() -> hand1
                        hand1.getHighestRank() < hand2.getHighestRank() -> hand2
                        else -> throw IllegalStateException("could not break tie for Straight Flushes $hand1, $hand2")
                    }
        },
        RoyalFlush {
            override fun breakTie(hand1: Hand, hand2: Hand): Hand =
                    throw UnsupportedOperationException("cannot break tie for Roayl Flush")
        };

        abstract fun breakTie(hand1: Hand, hand2: Hand): Hand

    }

    object HandRankComparator : Comparator<Hand> {
        override fun compare(hand1: Hand, hand2: Hand): Int {
            requireNotNull(hand1)
            requireNotNull(hand2)
            require(hand1.cards.size == hand2.cards.size)

            val hand1CardsSorted = hand1.cards.sortedWith(Card.CardRankComparator)
            val hand2CardsSorted = hand2.cards.sortedWith(Card.CardRankComparator)

            return hand1CardsSorted
                    .zip(hand2CardsSorted)
                    .map { it.second.rank.ordinal - it.first.rank.ordinal }
                    .filter { it != 0 }
                    .getOrElse(0, { 0 })
        }
    }
}

fun readPokerFile(): Sequence<String> =
        File(ClassLoader.getSystemResource("p054_poker.txt").file)
                .bufferedReader()
                .lineSequence()

fun howManyTimesPlayer1Won(): Int =
        readPokerFile()
                .map { it.split("\\s+".toRegex()) }
//                .onEach { println("reading line: $it") }
                .map {
                    Pair(
                            toHand(it.subList(0, 5)), // first 5 cards go to first player
                            toHand(it.subList(5, it.size)) // second 5 cards go to second player
                    )
                }
                .count { it.first.whoWins(it.second) == it.first }

fun toHand(cardsAsText: List<String>): Hand =
        Hand(cardsAsText.map { toCard(it) })

fun toCard(cardText: String): Card =
        Card(toRank(cardText[0]), toSuit(cardText[1]))

fun toSuit(c: Char): Suit =
        when (c) {
            'C' -> Suit.Clubs
            'H' -> Suit.Hearts
            'D' -> Suit.Diamonds
            'S' -> Suit.Spades
            else -> throw IllegalArgumentException("unrecognized suit $c")
        }

fun toRank(c: Char): Rank =
        when (c) {
            'A' -> Rank.Ace
            '2' -> Rank.Two
            '3' -> Rank.Three
            '4' -> Rank.Four
            '5' -> Rank.Five
            '6' -> Rank.Six
            '7' -> Rank.Seven
            '8' -> Rank.Eight
            '9' -> Rank.Nine
            'T' -> Rank.Ten
            'J' -> Rank.Jack
            'Q' -> Rank.Queen
            'K' -> Rank.King
            else -> throw IllegalArgumentException("unrecognized rank $c")
        }
