class Game {
   var gameOver = false

   init {
      println("WELCOME TO BLACKJACK")
      println(); println()
      println("Enter your name below:")
      var nameInput = readLine().toString()

      val player = Player(1, nameInput)

      println("Hello ${player.name}! you are player #${player.id}")
      println()
      val deck = Deck()   // deck of 52 cards initiated
      deck.shuffleCards()

      deck.deal(2, deck.cards, player)
      println()


      while (!gameOver) {

         println("Please, make a choice: \n 'c' ( call another card from dealer ) \n 'd' ( display cards in hands )\n 'e' ( evaluate hand ) \n 'q' ( quit )")

         var input = readLine().toString().toLowerCase()
         println()
         if (input == "c" || input == "call") {

            deck.deal(1, deck.cards, player)

            // set up for game restart
            var total = evaluateHand(player.Hand)
            if (total > 21) {
               println("Do you wish to play again ?")
               var playAgain = readLine().toString().toLowerCase()
               if (playAgain == "y" || playAgain == "yes") {
                  deck.cards.addAll(player.Hand)
                  player.Hand.clear()

                  println("New Game starting..")  /* add player's cards to Deck
                                                   then clears player hand. New
                                                   game start with dealing cards to player */
                  deck.deal(2, deck.cards, player)

               } else if (playAgain == "n" || playAgain == "no") {
                  gameOver = true
               } else {
                  println("Wrong command, try again.")
               }
            }

         } else if (input == "d" || input == "display") {
            player.displayCards()

         } else if (input == "q" || input == "quit") {
            gameOver = true

         } else if (input == "e" || input == "evaluate") {
            var total = evaluateHand(player.Hand)
            printResult(total, player.Hand)

         } else {
            println("Wrong command, try again.")
         }
      }
   }


   fun evaluateHand(Hand: MutableList<Card>): Int {

      var total = 0
      for (card in Hand) {
         total += card.value
      }

      if (total == 21) {
         println("You win!")
         printResult(total, Hand)
         gameOver = true
      } else if (total > 21) {
         println("Game over, you got bust!")
         printResult(total, Hand)

      } else {
         println("Getting close..")
      }
      return total
   }

   fun printResult(total: Int, Hand: MutableList<Card>) {
      println("Your hand was:")
      for (card in Hand) println(card)

      println("For a total of: $total")
      println()

   }

}
