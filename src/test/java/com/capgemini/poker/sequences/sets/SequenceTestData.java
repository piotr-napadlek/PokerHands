package com.capgemini.poker.sequences.sets;

import java.util.*;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.helpers.CardHelper;
import com.capgemini.poker.sequences.PokerSequence;

public class SequenceTestData {
	private List<Card> cards;
	private PokerSequence primary;
	private Optional<Card> highestAuxillaryCard;
	private String testName;
	private Optional<Class<? extends Throwable>> expectedException; 

	private SequenceTestData(Builder builder) {
		this.cards = builder.cards;
		this.primary = builder.primarySequence;
		this.highestAuxillaryCard = Optional.ofNullable(builder.highestAuxillaryCard);
		this.testName = builder.testSetName;
		this.expectedException =  Optional.ofNullable(builder.expectedException);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public PokerSequence getPrimary() {
		return primary;
	}
	
	public Optional<Card> getHighestAuxillaryCard() {
		return highestAuxillaryCard;
	}
	
	public Optional<Class<? extends Throwable>> getExpectedException() {
		return expectedException;
	}
	
	public String getTestName() {
		return testName;
	}
	
	public static Builder FluentBuilder() {
		return new Builder();
	}

	static class Builder {
		private List<Card> cards;
		private PokerSequence primarySequence;
		private Card highestAuxillaryCard;
		private String testSetName = "";
		private Class<? extends Throwable> expectedException = null;
		
		public Builder() {
			this.cards = new ArrayList<Card>();
		}
		
		public Builder withAddedCardToHand(String card) {
			cards.add(CardHelper.parseCard(card));
			return this;
		}
		
		public Builder withAddedCardToHand(Card card) {
			cards.add(card);
			return this;
		}
		
		public Builder withAddedMultipleCardsToHand(String cardsString) {
			cards.addAll(CardHelper.parseCardsFromString(cardsString));
			return this;
		}
		
		public Builder withAddedMultipleCardsToHand(Collection<Card> cards) {
			cards.addAll(cards);
			return this;
		}
		
		public Builder withExpectedPrimaryPokerSequenceOf(PokerSequence sequence){
			this.primarySequence = sequence;
			return this;
		}
		
		public Builder withRemainingHighestAuxillaryCard(String card) {
			this.highestAuxillaryCard = CardHelper.parseCard(card);
			return this;
		}
		
		public Builder withRemainingHighestAuxillaryCard(Card card) {
			this.highestAuxillaryCard = card;
			return this;
		}
		
		public Builder withExpectedExceptionClass(Class<? extends Throwable> expectedException) {
			this.expectedException = expectedException;
			return this;
		}
		
		public Builder withANameOf(String name) {
			this.testSetName = name;
			return this;
		}
		
		public SequenceTestData build() {
			return new SequenceTestData(this);
		}
	}
}