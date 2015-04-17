package aemyers;

import static org.junit.Assert.*;

import org.junit.Test;

import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestDealCardToWasteMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		Card topCard = ak.stock.peek();
		DealCardToWasteMove deal = new DealCardToWasteMove(ak.stock, ak.waste);
		
		assertTrue(deal.valid(ak)); //assert move is valid
		
		deal.doMove(ak);
		
		assertEquals(103, ak.stock.count()); //assert deck has 1 less card
		assertEquals(topCard, ak.waste.peek()); //assert top card of deck is now top card of waste
	
		
		deal.undo(ak);
		
		assertEquals(104, ak.stock.count());
		
	}

}
