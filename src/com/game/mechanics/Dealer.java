package com.game.mechanics;

public class Dealer extends Cards {
   protected String cardHand = "Dealer's Hand:";
   protected String cardReveal;
   protected int DealerValue = 0;
   
   public int setHand(String card, int index) {
      this.cardHand = cardHand + " " + card;
      this.DealerValue += getPoints(card);
      this.cardReveal = "X " + card;
      index++;
      return index;
    }
    
    public void getHand() {
       System.out.println(cardHand + "   Card Points: " + DealerValue);
    }
    
    public void revealHand() {
       System.out.println("Dealer's Hand: " + cardReveal);
    }
    
    public void clearHand() {
       this.cardHand = "Dealer's Hand:";
       this.DealerValue = 0;
    }
    
    public int drawCard(int DeckTrack, String card) {
       this.cardHand = this.cardHand + " " + card;
       this.cardReveal = this.cardReveal + " X";
       this.DealerValue += getPoints(card);
       if(card == "Ace") {
          this.Aces++;
       }
       DeckTrack++;
       return DeckTrack;
    }
}