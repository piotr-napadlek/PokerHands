package com.capgemini.poker.sequences.sets;

import static com.capgemini.poker.helpers.SequenceHelper.hasRepeats;
import static com.capgemini.poker.helpers.SequenceHelper.isMonotonicByOne;
import static com.capgemini.poker.helpers.SequenceHelper.findRepeats;
import static com.capgemini.poker.helpers.CardHelper.reevaluateAceInSequence;
import static com.capgemini.poker.sequences.PokerSequence.*;

import java.util.*;
import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.*;
import com.capgemini.poker.sequences.specific.*;

public class SequenceDetector {
	SortedSet<Card> cardsProcessed = new TreeSet<Card>();
	
	public Sequence detectSequence(Collection<Card> cards) {
		PokerSequence detected = PokerSequence.fromMask(getSequenceCombinationMask(cards));
		Sequence resultingSequence;
		
		switch (detected) {
		case STRAIGHT_FLUSH:
		case STRAIGHT:
			resultingSequence = new StraightSequence(detected, new TreeSet<Card>(cardsProcessed));
			break;
		case FULL_HOUSE:
			resultingSequence = new FullHouseSequence(detected, cardsProcessed);
			break;
		case FOUR_OF_A_KIND:
		case THREE_OF_A_KIND:
		case PAIR:
		case TWO_PAIRS:
			resultingSequence = new CardSequence(detected, new TreeSet<Card>(cardsProcessed));
			break;
		case FLUSH:
		case HIGH_CARD:
			resultingSequence = new CardSequence(detected, new TreeSet<Card>(cards));
			break;
		default:
			throw new IllegalArgumentException("Incorrect card combination!");
		}
		return resultingSequence;
	}

	private int getSequenceCombinationMask(Collection<Card> cards) {
		int combinationMask = hasRepeats(cards, Card::getSuit, 5) ? FLUSH.bitMask() : 0;
		boolean hasStraight = 
				isMonotonicByOne(reevaluateAceInSequence(cards), c -> c.getRank().asInt());
		if (hasStraight) {
			combinationMask += STRAIGHT.bitMask();
			cardsProcessed.addAll(cards);
		}
		combinationMask += cardsProcessed
				.addAll(findRepeats(cards, Card::getRank, 4)) ? FOUR_OF_A_KIND.bitMask() : 0;
		combinationMask += cardsProcessed
				.addAll(findRepeats(cards, Card::getRank, 3)) ? THREE_OF_A_KIND.bitMask() : 0;
		Collection<Card> pairs = findRepeats(cards, Card::getRank, 2);
		combinationMask += cardsProcessed.addAll(pairs) ? pairs.size() / 2 * PAIR.bitMask() : 0;
		
		return combinationMask;
	}
}