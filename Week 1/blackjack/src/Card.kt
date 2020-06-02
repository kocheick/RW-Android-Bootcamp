class Card (val rank : String, val suit : String, val value : Int) {

    val redHeart = StringBuilder("${27.toChar()}[31m\u2665${27.toChar()}[0m")
    val redDiamond = StringBuilder("${27.toChar()}[31m\u2666${27.toChar()}[0m")

    val ansy = when(suit) {
        "DIAMONDS" -> redDiamond
        "CLUBS" -> '\u2663'
        "SPADES" -> '\u2660'
        else -> redHeart
    }

    override fun toString(): String {
        return "$rank$ansy  | $value"
    }
}