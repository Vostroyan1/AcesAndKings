//Adam Myers cs3733

package aemyers;

import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;

public class AcesAndKingsController extends SolitaireReleasedAdapter{
	
	protected AcesAndKings theGame; //the game
	
	protected Pile waste; //waste pile of interest
	
	protected MultiDeck stock; //stock pile(deck) of interest
	
	//AcesAndKingsController constructor
	public AcesAndKingsController(AcesAndKings theGame, MultiDeck d, Pile waste){
		super(theGame);
		
		this.theGame = theGame;
		this.waste = waste;
		this.stock = d;
	}
	
	public void mousePressed(java.awt.event.MouseEvent me){
		//attempt a DealCardToWasteMove
		Move m = new DealCardToWasteMove(stock, waste);
		if(m.doMove(theGame)){
			theGame.pushMove(m); //Successful move has been moved
			theGame.refreshWidgets(); //refresh updated widgets
		}
	}
}
