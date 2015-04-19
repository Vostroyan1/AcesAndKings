package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.FoundationToAceFoundationMove;
import moves.ReserveToAceFoundationMove;

import org.junit.Test;

public class TestReserveToAceFoundationMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//an ace foundation to another ace foundation
		ReserveToAceFoundationMove move1 = new ReserveToAceFoundationMove(ak.reserve[1], new Card(1,1), ak.acefoundation[2]);
		assertTrue(move1.valid(ak));
		
		move1.doMove(ak);
		assertTrue(ak.getScoreValue() == 1); //assert that score was incremented
		move1.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was decremented
		
		gw.dispose();
	}

}
