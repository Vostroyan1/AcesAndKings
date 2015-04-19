package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.DealCardToWasteMove;
import moves.FoundationToAceFoundationMove;

import org.junit.Test;

public class TestFoundationToAceFoundationMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//an ace foundation to another ace foundation
		FoundationToAceFoundationMove move1 = new FoundationToAceFoundationMove(ak.acefoundation[1], new Card(1,1), ak.acefoundation[2]);
		assertTrue(move1.valid(ak));
		move1.doMove(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move1.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move1.doMove(ak);
		
		//a king foundation to an ace foundation
		FoundationToAceFoundationMove move2 = new FoundationToAceFoundationMove(ak.kingfoundation[1], new Card(2,1), ak.acefoundation[2]);
		assertTrue(move2.valid(ak));
		move2.doMove(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move2.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		
		gw.dispose();
	}

}
