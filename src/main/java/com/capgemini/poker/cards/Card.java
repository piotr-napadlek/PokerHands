package com.capgemini.poker.cards;

public final class Card implements Comparable<Card> {
	private final Suit suit;
	private final Rank rank;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int compareTo(Card otherCard) {
		return (this.rank.asInt() - otherCard.rank.asInt()) * 4 + 
				this.suit.asInt()- otherCard.suit.asInt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime * 1 + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object otherObj) {
		if (this == otherObj) {
			return true;
		}
		if (otherObj == null || getClass() != otherObj.getClass()) {
			return false;
		}
		Card other = (Card) otherObj;
		return this.rank == other.rank && this.suit == other.suit;
	}
}