package com.capgemini.poker.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.IntStream;

import com.capgemini.poker.cards.*;

public class CardHelper {

	public static Collection<Card> reevaluateAceInSequence(Collection<Card> cards) {
		Collection<Card> cardsModified = new ArrayList<Card>(cards);
		if (cards.stream().anyMatch(card -> card.getRank().asInt() == 5)) {
			cardsModified.removeIf(card -> Rank.ACE.equals(card.getRank()));
			IntStream.range(0, cards.size() - cardsModified.size()).forEach(i -> 
				cardsModified.add(new Card(Rank.ACE_L, Suit.CLUB)));
		}
		return cardsModified;
	}

	public static Collection<Card> parseCardsFromString(String stringToParse) {
		Collection<Card> cards = new ArrayList<Card>();
		Arrays.asList(stringToParse.split(" ")).forEach(card -> cards.add(parseCard(card)));
		return cards;
	}

	public static Card parseCard(String cardAsString) {
		if (cardAsString.length() < 2) {
			throw new IllegalArgumentException("Input string must be at least two characters");
		}
		Rank rank = Rank.fromChar(cardAsString.toUpperCase(Locale.ENGLISH).charAt(0));
		Suit suit = Suit.fromChar(cardAsString.toUpperCase(Locale.ENGLISH).charAt(1));
		return new Card(rank, suit);
	}
}