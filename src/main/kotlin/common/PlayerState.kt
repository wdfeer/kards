package game

data class PlayerState(val row: MutableList<Card>, val hand: MutableList<Card> ) {
    companion object {
        fun getDefault(): PlayerState =
            PlayerState(mutableListOf(), mutableListOf(Card.Archer, Card.Warrior, Card.Mage))
    }

    fun rowToString() = row.joinToString(" ")
    fun handToString() = hand.joinToString(" ")
}
