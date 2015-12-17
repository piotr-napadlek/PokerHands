package com.capgemini.poker.hands;

import com.capgemini.poker.sequences.sets.CardGameSequenceSet;

public interface CardGameHand {
	public CardGameSequenceSet getSequenceSet();
	public int compareToHand(CardGameHand other);
}