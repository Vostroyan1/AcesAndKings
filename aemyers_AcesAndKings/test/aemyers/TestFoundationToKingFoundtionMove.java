package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.FoundationToAceFoundationMove;
import moves.FoundationToKingFoundationMove;

import org.junit.Test;

public class TestFoundationToKingFoundtionMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//a king foundation to another king foundation
		FoundationToKingFoundationMove move1 = new FoundationToKingFoundationMove(ak.kingfoundation[1], new Card(13,1), ak.kingfoundation[2]);
		assertTrue(move1.valid(ak));
		move1.doMove(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move1.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move1.doMove(ak);
		
		//an ace foundation to a king foundation
		FoundationToKingFoundationMove move2 = new FoundationToKingFoundationMove(ak.acefoundation[1], new Card(12,1), ak.kingfoundation[2]);
		assertTrue(move2.valid(ak));
		move2.doMove(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		move2.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was unchanged
		
		gw.dispose();
	}

}
