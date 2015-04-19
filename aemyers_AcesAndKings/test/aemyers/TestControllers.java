package aemyers;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import ks.client.gamefactory.GameWindow;
import ks.common.model.Deck;
import ks.launcher.Main;

import org.junit.Test;

public class TestControllers {

	@Test
	public void test() throws AWTException {
		AcesAndKings ak = new AcesAndKings();
		GameWindow gw = Main.generateWindow(ak, Deck.OrderBySuit);
		
		Robot bot = new Robot();
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		bot.setAutoDelay(200);
		bot.mouseMove(ak.stockview.getX()+10, ak.stockview.getY()+100); //test DeckController
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		assertEquals(67, ak.stock.count()); //assert deck has 1 less card
		
		bot.mouseMove(ak.wasteview.getX()+10,ak.wasteview.getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.acefoundationview[1].getX()+10, ak.acefoundationview[1].getY()+100); //test waste dragging and failed release on acefoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.wasteview.getX()+10,ak.wasteview.getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.kingfoundationview[1].getX()+10, ak.kingfoundationview[1].getY()+100);//test waste dragging and failed release on kingfoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.tableauview[1].getX()+10,ak.tableauview[1].getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.kingfoundationview[1].getX()+10, ak.kingfoundationview[1].getY()+100);//test tableau dragging and a successful release on a kingfoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.tableauview[2].getX()+10,ak.tableauview[2].getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.kingfoundationview[1].getX()+10, ak.kingfoundationview[1].getY()+100);//test tableau dragging and a successful release on a kingfoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.tableauview[2].getX()+10,ak.tableauview[2].getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.acefoundationview[1].getX()+10, ak.acefoundationview[1].getY()+100);//test tableau dragging and a successful release on an acefoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.tableauview[1].getX()+10,ak.tableauview[1].getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.acefoundationview[1].getX()+10, ak.acefoundationview[1].getY()+100);//test tableau dragging and a successful release on an acefoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.wasteview.getX()+10,ak.wasteview.getY()+100);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.acefoundationview[1].getX()+10, ak.acefoundationview[1].getY()+100);//test tableau dragging and a successful release on an acefoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		bot.mouseMove(ak.reserveview[2].getX()+10,ak.reserveview[2].getY()*5+50);
		bot.delay(500);
		bot.mousePress(mask);
		bot.mouseMove(ak.acefoundationview[1].getX()+10, ak.acefoundationview[1].getY()+100);//test reserve dragging and a successful release on an acefoundation
		bot.delay(500);
		bot.mouseRelease(mask);
		
		gw.dispose();
	}

}
