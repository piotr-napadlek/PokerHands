package com.capgemini.poker.sequences;

import java.util.SortedSet;

import com.capgemini.poker.cards.Card;

public interface Sequence {
	public SortedSet<Card> getCardsInSequence();
	public int sequenceStrength();
	public PokerSequence getSequence();
	public int compareToSequence(Sequence other);
}
