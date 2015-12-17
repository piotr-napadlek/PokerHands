package com.capgemini.poker.sequences.specific;

import static com.capgemini.poker.helpers.SequenceHelper.findRepeats;

import java.util.*;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.*;

public class FullHouseSequence extends CardSequence {

	public FullHouseSequence(PokerSequence sequence, Collection<Card> cardsInSequence) {
		super(sequence, new TreeSet<Card>(cardsInSequence));
	}

	@Override
	public int sequenceStrength() {
		int threeCardValue = findRepeats(getCardsInSequence(), Card::getRank, 3).get(0)
				.getRank().asInt();
		return threeCardValue;
	}
}