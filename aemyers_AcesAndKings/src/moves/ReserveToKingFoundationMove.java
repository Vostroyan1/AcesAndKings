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
public class ReserveToKingFoundationMove extends Move{

	Column reserve;
	Card cardBeingDragged;
	Pile kingfoundation;
	
	public ReserveToKingFoundationMove(Column from, Card c, Pile to){
		this.reserve = from;
		this.cardBeingDragged = c;
		this.kingfoundation = to;
	}
	
	public boolean doMove(Solitaire game) {
		if (!valid(game)) { return false; }
		kingfoundation.add(cardBeingDragged);
		game.updateScore(+1);
		return true;
	}
	
	public boolean undo(Solitaire game) {
		reserve.add(kingfoundation.get());
		game.updateScore(-1);
		return true;
	}
	
	public boolean valid(Solitaire game) {
		if(kingfoundation.count() == 12) {return false;} //full kingfoundation
		if(cardBeingDragged.isAce()){return false;}
		
		if(kingfoundation.empty() && cardBeingDragged.getRank() == 13) {return true;}
		
		if (!kingfoundation.empty()){
			Card nextCard = new Card(kingfoundation.peek().getRank() - 1, cardBeingDragged.getSuit());
			if(cardBeingDragged.sameRank(nextCard))
			{return true;}
		}
		
		return false;
	}
}
