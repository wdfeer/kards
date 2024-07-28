package org.wdfeer.kards.common.client

import org.wdfeer.kards.common.Card

data class ClientState(val fields: List<List<Card>>, val myCards: List<Card>, val opponentCardCount: Int)