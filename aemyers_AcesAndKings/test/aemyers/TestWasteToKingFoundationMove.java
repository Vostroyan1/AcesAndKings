package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.WasteToKingFoundationMove;

import org.junit.Test;

public class TestWasteToKingFoundationMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//an ace foundation to another ace foundation
		WasteToKingFoundationMove move1 = new WasteToKingFoundationMove(ak.waste, new Card(13,1), ak.kingfoundation[2]);
		assertTrue(move1.valid(ak));
		
		move1.doMove(ak);
		assertTrue(ak.getScoreValue() == 1); //assert that score was incremented
		move1.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was decremented
		
		gw.dispose();
		
	}

}
