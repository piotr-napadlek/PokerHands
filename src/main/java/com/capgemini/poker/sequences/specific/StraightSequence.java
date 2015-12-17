package com.capgemini.poker.sequences.specific;

import java.util.Collection;
import java.util.TreeSet;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.helpers.CardHelper;
import com.capgemini.poker.sequences.CardSequence;
import com.capgemini.poker.sequences.PokerSequence;

public class StraightSequence extends CardSequence {

	public StraightSequence(PokerSequence sequence, Collection<Card> cardsInSequence) {
		super(sequence, new TreeSet<Card>(cardsInSequence));
	}

	@Override
	public int sequenceStrength() {
		return CardHelper.reevaluateAceInSequence(getCardsInSequence()).stream()
				.max((c1, c2) -> c1.getRank().asInt() - c2.getRank().asInt())
				.get().getRank().asInt();
	}
}