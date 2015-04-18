//Adam Myers cs3733

package moves;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

/**
 * Move card from top of waste pile to top of tableau pile
 */
public class WasteToTableauMove extends Move{

	MultiDeck stock;
	Pile waste;
	Pile tableau;
	boolean wasStock;
	
	public WasteToTableauMove(MultiDeck from,Pile fromAlt , Pile to){
		this.stock = from;
		this.waste = fromAlt;
		this.tableau = to;
	}
	
	public boolean doMove(Solitaire game) {
		if(!valid(game)){return false;}
		
		if (!stock.empty()){
			Card card = stock.get();
			tableau.add(card);
			wasStock = true;
		}
		else{
			Card card = waste.get();
			tableau.add(card);
			wasStock = false;
		}
		return true; //move successful
	}
	
	public boolean undo(Solitaire game) {
		if (wasStock){
			Card card = tableau.get();
			stock.add(card);
		}
		else{
			Card card = tableau.get();
			waste.add(card);
		}
		return true; //undo successful
	}
	
	public boolean valid(Solitaire game) {
		if (!stock.empty() && tableau.empty()){return true;}
		if (!waste.empty() && tableau.empty()){return true;}
		return false;
	
	}
}
