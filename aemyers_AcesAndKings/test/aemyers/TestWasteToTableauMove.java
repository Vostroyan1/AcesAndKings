package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.WasteToAceFoundationMove;
import moves.WasteToTableauMove;

import org.junit.Test;

public class TestWasteToTableauMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//an ace foundation to another ace foundation
		WasteToTableauMove move1 = new WasteToTableauMove(ak.stock, ak.waste, ak.tableau[1]);
		assertFalse(move1.valid(ak)); //assert move cannot be done due to full tableau pile
		assertFalse(move1.doMove(ak));
		
		Card card= ak.tableau[1].get();
		ak.kingfoundation[1].add(card);
		assertTrue(move1.valid(ak)); //assert move can now be done now that tableau is empty and cards are available in stock/waste
		Card topStock = new Card(ak.stock.peek().getRank(),ak.stock.peek().getSuit());
		move1.doMove(ak);
		assertEquals(topStock,ak.tableau[1].peek()); //assert card was drawn from stock
		move1.undo(ak);
		assertEquals(topStock,ak.stock.peek()); //test card goes back to stock
		
		ak.waste.add(ak.stock.get());
		ak.stock.removeAll();
		ak.tableau[1].removeAll();
		assertTrue(move1.valid(ak)); //assert the move is valid with an empty stock and a non-empty waste
		move1.doMove(ak);
		move1.undo(ak);
		assertEquals(topStock,ak.waste.peek()); //test card goes back to waste
		
		gw.dispose();
	}

}
