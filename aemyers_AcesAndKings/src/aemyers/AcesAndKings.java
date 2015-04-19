//Adam Myers cs3733

package aemyers;

import moves.FoundationToKingFoundationMove;
import moves.WasteToTableauMove;
import controllers.*;
import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.Card;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.CardImages;
import ks.common.view.ColumnView;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

public class AcesAndKings extends Solitaire{

	MultiDeck stock;
	Pile waste;
	Pile tableau[] = new Pile [5];
	Pile acefoundation[] = new Pile [5];
	Pile kingfoundation[] = new Pile [5];
	Column reserve[] = new Column [3];
	//Views
	IntegerView scoreview;
	DeckView stockview;
	PileView wasteview;
	PileView acefoundationview[] = new PileView [5];
	PileView kingfoundationview[] = new PileView [5];
	ColumnView reserveview[] = new ColumnView [3];
	PileView tableauview[] = new PileView [5];
	
	@Override
	public String getName() {
		return "AcesAndKings";
	}

	@Override
	public boolean hasWon() {
		return getScore().getValue() == 104;
	}

	@Override
	public void initialize() {
		//initialize model
		initializeModel(getSeed());
		//initialize the views
		initializeView();
		//initialize the controllers
		initializeController();
		
		//prepare game by ...
		
	}

	private void initializeController() {
		stockview.setMouseAdapter(new DeckController(this, stock, waste));
		stockview.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		stockview.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		wasteview.setMouseAdapter(new WasteController(this, wasteview));
		wasteview.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wasteview.setUndoAdapter(new SolitaireUndoAdapter(this));
		
		for (int i = 1; i <=4; i++){
			acefoundationview[i].setMouseAdapter(new AceFoundationController(this, acefoundationview[i], stock, waste));
			acefoundationview[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			acefoundationview[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}
		for (int i = 1; i <=4; i++){
			kingfoundationview[i].setMouseAdapter(new KingFoundationController(this, kingfoundationview[i], stock, waste));
			kingfoundationview[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			kingfoundationview[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}
		for (int i = 1; i <=4; i++){
			tableauview[i].setMouseAdapter(new TableauController(this, tableauview[i]));
			tableauview[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			tableauview[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}
		for (int i = 1; i <=2; i++){
			reserveview[i].setMouseAdapter(new ReserveController(this, reserveview[i]));
			reserveview[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			reserveview[i].setUndoAdapter(new SolitaireUndoAdapter(this));
		}

	}

	private void initializeView() {
		CardImages c = getCardImages();
		
		scoreview = new IntegerView (getScore());
		scoreview.setFontSize(14);
		scoreview.setBounds (20, 20, 150, 50);
		scoreview.setName("ScoreView");
		container.addWidget (scoreview);
		
		stockview = new DeckView(stock);
		stockview.setBounds(10*(1+5) + c.getWidth()*(1+3),20,c.getWidth(),c.getHeight());
		stockview.setName("StockView");
		container.addWidget(stockview);

		wasteview = new PileView (waste);
		wasteview.setBounds (10*(2+5) + c.getWidth()*(2+3),20, c.getWidth(), c.getHeight());
		wasteview.setName("WasteView");
		container.addWidget (wasteview);
		
		// create acefoundation PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			acefoundationview[pileNum] = new PileView (acefoundation[pileNum]);
			acefoundationview[pileNum].setBounds (10*(pileNum+5) + c.getWidth()*(pileNum+3), 140+scoreview.getHeight(), c.getWidth(), c.getHeight());
			acefoundationview[pileNum].setName("AceFoundationView" + pileNum);
			container.addWidget (acefoundationview[pileNum]);
		}
		
		// create kingfoundation PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			kingfoundationview[pileNum] = new PileView (kingfoundation[pileNum]);
			kingfoundationview[pileNum].setBounds (10*(pileNum+5) + c.getWidth()*(pileNum+3), 180+scoreview.getHeight()+c.getHeight(), c.getWidth(), c.getHeight());
			kingfoundationview[pileNum].setName("KingFoundationView" + pileNum);
			container.addWidget (kingfoundationview[pileNum]);
		}
				
		// create tableau PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			tableauview[pileNum] = new PileView (tableau[pileNum]);
			tableauview[pileNum].setBounds (10*(pileNum+5) + c.getWidth()*(pileNum+3), 200+scoreview.getHeight()+2*c.getHeight(), c.getWidth(), c.getHeight());
			tableauview[pileNum].setName("TableauView" + pileNum);
			container.addWidget (tableauview[pileNum]);
		}
		
		// create reserve ColumnViews, one after the other.
		for (int pileNum = 1; pileNum <=2; pileNum++) {
			reserveview[pileNum] = new ColumnView (reserve[pileNum]);
			if (pileNum ==1)
				reserveview[pileNum].setBounds(20,40+scoreview.getHeight(),c.getWidth(),c.getHeight()*4+50);
			if (pileNum ==2)
				reserveview[pileNum].setBounds(80+reserveview[pileNum-1].getWidth(),40+scoreview.getHeight(),c.getWidth(),c.getHeight()*4+50);
			reserveview[pileNum].setName("ReserveView" + pileNum);
			container.addWidget (reserveview[pileNum]);
		}

	}

	private void initializeModel(int seed) {
		stock = new MultiDeck(2);
		stock.create(seed);
		model.addElement(stock); //add deck to the model
		
		waste = new Pile("waste");
		model.addElement(waste);
		//stock = new Pile("stock");
		//model.addElement(stock);
		
		// each of the tableaus appear here
		for (int i = 1; i<=4; i++) {
			tableau[i] = new Pile ("tableau" + i);
			model.addElement (tableau[i]);
		} 

		// develop acefoundations
		for (int i = 1; i<=4; i++) {
			acefoundation[i] = new Pile ("acefoundation" + i);
			model.addElement (acefoundation[i]);
		}
		
		// develop kingfoundations
		for (int i = 1; i<=4; i++) {
			kingfoundation[i] = new Pile ("kingfoundation" + i);
			model.addElement (kingfoundation[i]);
		}
		
		// develop reserve columns
		for (int i = 1; i<=2; i++) {
			reserve[i] = new Column ("reserve" + i);
			model.addElement (reserve[i]);
		}
		
		updateScore(0);
		updateNumberCardsLeft(104);
		
		for(int i = 1; i<=4;i++){ //give each tableau pile a card
			Card c = stock.get();
			tableau[i].add(c);
		}
		
		for(int i = 1; i<=2;i++){ //give each reserve pile a card
			for(int f=1; f<=16;f++){
				Card c = stock.get();
				reserve[i].add(c);
			}	
		}
		
		
	}
	
	public static void main(String []args){
		Main.generateWindow(new AcesAndKings(), Deck.OrderBySuit);
	}
}
