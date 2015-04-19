package aemyers;

import static org.junit.Assert.*;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;

import org.junit.Test;

public class TestAcesAndKings {

	@Test
	public void test() {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		ak.updateScore(104);
		assertTrue(ak.hasWon());
		
		gw.dispose();
	}

}
