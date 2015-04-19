package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;
import moves.ReserveToKingFoundationMove;
import moves.TableauToAceFoundationMove;

import org.junit.Test;

public class TestTableauToAceFoundationMove {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		//an ace foundation to another ace foundation
		TableauToAceFoundationMove move1 = new TableauToAceFoundationMove(ak.tableau[1], new Card(1,1), ak.acefoundation[2]);
		assertTrue(move1.valid(ak));
		
		move1.doMove(ak);
		assertTrue(ak.getScoreValue() == 1); //assert that score was incremented
		move1.undo(ak);
		assertTrue(ak.getScoreValue() == 0); //assert that score was decremented
		
		gw.dispose();
	}

}
