package com.game.mechanics;

public class BlackJack extends playBlackJack{
   public static void main(String[] args) {
   int DeckTrack = 0;
   boolean Shuffle = false, BlackJack = false;
    
String [] Ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
     
     BlackJack Game = new BlackJack();
     Deck gameDeck = new Deck();
     gameDeck.createDeck(Ranks);
     
     for(int i=0; i<Ranks.length*4; i++) {
        System.out.print(gameDeck.DeckList[i] + " ");
     }
     
     System.out.println("\n" + "------============WELCOME TO BLACKJACK THE GAME============------");
     
        do {
           Game.RoundStart();
           
           /*try {
              Game.Bet = Game.sc.nextInt();
              } catch(Exception e) {
                 System.out.println("Please enter a valid numeric value.");
                 Game.RoundStart();
              }*/
           
           Game.BetHandler(Game.Bet, Game.P1.Money);
           
           for(int i=0; i<2; i++) {
              DeckTrack = Game.P1.setHand(gameDeck.getDeck(DeckTrack), DeckTrack);
              DeckTrack = Game.D.setHand(gameDeck.getDeck(DeckTrack), DeckTrack);
           }
           
           Game.P1.getHand();
           BlackJack = Game.checkAces();
           
           if(BlackJack == false) {
              while(Game.D.DealerValue < 16) {
                 DeckTrack = Game.D.drawCard(DeckTrack, gameDeck.getDeck(DeckTrack));
                 Game.D.DealerValue = Game.D.AceHandler(Game.D.Aces, Game.D.DealerValue);
              }
              Game.D.revealHand();
              
              while(Game.P1.PlayerValue < 21 && Game.P1.isDraw == true) {
                 Game.PlayerMove(Game.P1.PlayerValue);
                 DeckTrack = Game.P1.drawCard(DeckTrack, gameDeck.getDeck(DeckTrack), Game.P1.isDraw);
              }
           }
           
           Game.D.getHand();
           
           Game.gameResult(Game.P1.PlayerValue, Game.D.DealerValue);
           Game.MoneyHandler(Game.Bet, Game.P1.Money, Game.Match);
           
           Game.P1.clearHand();
           Game.D.clearHand();
           
           DeckTrack = gameDeck.ShuffleDeck(DeckTrack, Shuffle, Ranks);
           System.out.println("============================================================");
        } while(Game.P1.Money > 0);
        
        System.out.println("You ran out of Money...");
        Game.sc.close();
     
   }
   
   void gameResult(int PlayerValue, int DealerValue) {
      if(DealerValue==21) {
         Match = MatchResult.LOSE;
      }
      else if(PlayerValue>21) {
         Match = MatchResult.LOSE;
      }
      else if(DealerValue>21) {
         Match = MatchResult.WIN;
      }
      else if(PlayerValue==21 && DealerValue!=21) {
         Match = MatchResult.WIN;
      }
      else if(DealerValue == PlayerValue && PlayerValue<22) {
         Match = MatchResult.DRAW;
      }
      else if(DealerValue > PlayerValue && DealerValue<22) {
         Match = MatchResult.LOSE;
      }
      else if(DealerValue < PlayerValue && PlayerValue<22) {
         Match = MatchResult.WIN;
      }
   }
}