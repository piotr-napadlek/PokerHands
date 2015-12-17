package com.capgemini.poker.cards;

import java.util.*;

public enum Suit {
	HEART(0, 'H'), 
	DIAMOND(1, 'D'), 
	CLUB(2, 'C'), 
	SPADE(3, 'S');

	private int suitValue;
	private char suitCharacter;
	
	private final static Map<Character, Suit> suitMap = new HashMap<Character, Suit>(4, 1.0f);
	static {
		for(Suit suit : Suit.values()) {
			suitMap.put(suit.asChar(), suit);
		}
	}
	
	Suit(int value, char character) {
		this.suitValue = value;
		this.suitCharacter = character;
	}

	public int asInt() {
		return suitValue;
	}
	
	public char asChar() {
		return suitCharacter;
	}
	
	public static Suit fromChar(char suitChar) {
		return suitMap.get(suitChar);
	}
}