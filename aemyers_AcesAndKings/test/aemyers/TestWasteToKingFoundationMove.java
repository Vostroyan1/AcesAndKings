package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.DealCardToWasteMove;
import moves.WasteToKingFoundationMove;

import org.junit.Test;

public class TestWasteToKingFoundationMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//Card topCard = ak.stock.peek();
		DealCardToWasteMove deal = new DealCardToWasteMove(ak.stock, ak.waste);
		deal.doMove(ak);
		
		//WasteToKingFoundationMove kmove = new WasteToKingFoundationMove(ak.waste,ak.waste.get(),ak.kingfoundation[1]);
		//assertEquals(13, kmove.cardBeingDragged.getRank());
		
	}

}
