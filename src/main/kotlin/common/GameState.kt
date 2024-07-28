package game

data class GameState(val players: List<PlayerState> = listOf(PlayerState.getDefault(), PlayerState.getDefault())) {
    fun print() {
        println(players[0].handToString())
        println()
        println(players[0].rowToString())
        println(players[1].rowToString())
        println()
        println(players[1].handToString())
    }

    fun prompt() {
        print("Choose a card to play: ")

        val hand = players[1].hand
        repeat(hand.size) { print("[$it]") }
        println()

        var index = readln().toIntOrNull()
        while (index == null || !hand.indices.contains(index)) {
            println("Invalid input!")
            index = readln().toIntOrNull()
        }

        players[1].row.add(hand[index])
        hand.removeAt(index)
    }
}