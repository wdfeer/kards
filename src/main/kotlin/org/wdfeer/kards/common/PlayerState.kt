package org.wdfeer.kards.common

data class PlayerState(val field: MutableList<Card>, val hand: MutableList<Card> ) {
    companion object {
        fun getDefault(): PlayerState =
            PlayerState(mutableListOf(), mutableListOf(Card.Archer, Card.Warrior, Card.Mage))
    }

    fun playCard(index: Int) {
        field.add(hand[index])
        hand.removeAt(index)
    }
}
