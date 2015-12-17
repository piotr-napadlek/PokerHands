package com.capgemini.poker.sequences;

import java.util.*;

public enum PokerSequence {
	HIGH_CARD (0, 0b000000),
	PAIR (1, 0b000001),
	TWO_PAIRS (2, PAIR.bitMask() * 2),
	THREE_OF_A_KIND (3, 0b000100),
	STRAIGHT (4, 0b001000),
	FLUSH (5, 0b010000),
	FULL_HOUSE (6, PAIR.bitMask() + THREE_OF_A_KIND.bitMask()),
	FOUR_OF_A_KIND (7, 0b100000),
	STRAIGHT_FLUSH (8, STRAIGHT.bitMask() + FLUSH.bitMask());
	
	private int sequenceValue;
	private int bitMask;
	
	private static Map<Integer, PokerSequence> sequenceMap =
										new HashMap<Integer, PokerSequence>(9, 1.0f);
	static {
		for(PokerSequence sequence : PokerSequence.values()) {
			sequenceMap.put(sequence.bitMask, sequence);
		}
	}
	
	PokerSequence(int value, int bitMask) {
		this.sequenceValue = value;
		this.bitMask = bitMask;
	}
	
	public int asInt() {
		return sequenceValue;
	}
	
	public int bitMask() {
		return bitMask;
	}
	
	public static PokerSequence fromMask(int bitMask) {
		return sequenceMap.get(bitMask);
	}
}