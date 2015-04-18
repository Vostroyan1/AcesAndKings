//Adam Myers cs3733

package moves;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

/**
 * Move card from top of stock pile to top of waste pile
 */
public class DealCardToWasteMove extends Move{

	MultiDeck stock;
	Pile waste;
	
	public DealCardToWasteMove(MultiDeck stock, Pile waste){
		this.stock = stock;
		this.waste = waste;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if (!valid(game)){ return false;}
		
		Card card = stock.get();
		//System.out.println(card.getRank());
		waste.add(card);
		return true; //move successful
	}

	@Override
	public boolean undo(Solitaire game) {
		Card card = waste.get();
		stock.add(card);
		return true; //undo successful
	}

	//validate stock is not empty
	public boolean valid(Solitaire game) {
		return !stock.empty();
	}

}
