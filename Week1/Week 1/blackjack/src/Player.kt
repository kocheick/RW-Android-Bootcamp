class Player (val id:Int, val name: String) {

    val Hand: MutableList<Card> = mutableListOf<Card>()

    fun displayCards() {
        println("'$name' has ${Hand.size} cards")
        for (card in Hand) println(card)
        println()
    }
}