class Deck {
    var SUITS = setOf<String>("DIAMONDS", "CLUBS", "HEARTS", "SPADES")
    var RANKS = setOf<String>("2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "K", "Q", "J")
    var cards: MutableList<Card> = mutableListOf()


    init { // Building Deck
println("Building deck..")
        for (suit in SUITS) {
            for (rank in RANKS) {
                // set the card.value based on the rank
                val value = when (rank) {
                    "2" -> 2
                    "3" -> 3
                    "4" -> 4
                    "5" -> 5
                    "6" -> 6
                    "7" -> 7
                    "8" -> 8
                    "9" -> 9
                    "10" -> 10
                    "A" -> 11
                    "K" -> 10
                    "Q" -> 10
                    "J" -> 10
                    else -> 0
                }
                val card = Card(rank, suit, value)  // creating a card object on each iteration of the for-loop

                cards.add(card) // add each card to the cards collection ( or the deck)
            }
        }
    }


    //Display cards in Deck
    fun displayCards() {
        for (card in cards) println(card)
    }

    // Shuffle cards in Deck
    fun shuffleCards() {
        return cards.shuffle()

    }

    //Deal cards to players
    fun deal(
        amount: Int = 1,
        cards: MutableList<Card>,
        vararg players: Player
    ) {
        println("Dealing cards..")

        for (player in players) {
            println("$amount cards were dealt to ${player.name} ")
            for (index in amount - 1 downTo 0) {
                player.Hand.add(cards[index])
                cards.removeAt(index)
            }
        }
    }
}

