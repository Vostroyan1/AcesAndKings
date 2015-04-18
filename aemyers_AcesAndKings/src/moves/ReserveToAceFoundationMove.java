//Adam Myers cs3733

package moves;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Move;
import ks.common.model.Pile;

/**
 * Move card from top of waste pile to top of tableau pile
 */
public class ReserveToAceFoundationMove extends Move{

	Column reserve;
	Card cardBeingDragged;
	Pile acefoundation;
	
	public ReserveToAceFoundationMove(Column from, Card c, Pile to){
		this.reserve = from;
		this.cardBeingDragged = c;
		this.acefoundation = to;
	}
	
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { return false; }
		
		acefoundation.add(cardBeingDragged);
		game.updateScore(+1);
		return true;
	}
	
	public boolean undo(Solitaire game) {
		reserve.add(acefoundation.get());
		game.updateScore(-1);
		return true;
	}
	
	public boolean valid(Solitaire game) {
		if(acefoundation.count() == 12) {return false;} //full acefoundation
		if(cardBeingDragged.getRank() == 13){return false;}
		
		if(acefoundation.empty() && cardBeingDragged.isAce()) {return true;}
		
		if (!acefoundation.empty()){
			Card nextCard = new Card(acefoundation.peek().getRank() + 1, cardBeingDragged.getSuit());
			if(cardBeingDragged.sameRank(nextCard))
			{return true;}
		}
		return false; //else false
	}
}
