//Adam Myers cs3733

package aemyers;

import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.BuildablePile;
import ks.common.model.Column;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.BuildablePileView;
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
	Column reserve1, reserve2;
	//Views
	IntegerView scoreview;
	DeckView stockview;
	PileView wasteview;
	PileView acefoundationview[] = new PileView [5];
	PileView kingfoundationview[] = new PileView [5];
	ColumnView reserveview1, reserveview2;
	PileView tableauview[] = new PileView [5];
	
	@Override
	public String getName() {
		return "AcesAndKings";
	}

	@Override
	public boolean hasWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initialize() {
		//initialize model
		initializeModel(getSeed());
		//initialize the views
		initializeView();
		initializeController();
		
		//prepare game by ...
		
	}

	private void initializeController() {
		stockview.setMouseAdapter(new AcesAndKingsController(this, stock, waste));
		stockview.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		stockview.setUndoAdapter(new SolitaireUndoAdapter(this));
	}

	private void initializeView() {
		CardImages c = getCardImages();
		
		scoreview = new IntegerView (getScore());
		scoreview.setFontSize(14);
		scoreview.setBounds (20, 20, 150, 50);
		container.addWidget (scoreview);
		
		stockview = new DeckView(stock);
		stockview.setBounds(20,100+scoreview.getHeight()+2*c.getHeight(),c.getWidth(),c.getHeight());
		container.addWidget(stockview);

		wasteview = new PileView (waste);
		wasteview.setBounds (30+c.getWidth(),100+scoreview.getHeight()+2*c.getHeight(), c.getWidth(), c.getHeight());
		container.addWidget (wasteview);
		
		// create acefoundation PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			acefoundationview[pileNum] = new PileView (acefoundation[pileNum]);
			acefoundationview[pileNum].setBounds (20*pileNum + c.getWidth()*(pileNum-1), 80+scoreview.getHeight()+c.getHeight(), c.getWidth(), c.getHeight());
			container.addWidget (acefoundationview[pileNum]);
		}
		
		// create kingfoundation PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			kingfoundationview[pileNum] = new PileView (kingfoundation[pileNum]);
			kingfoundationview[pileNum].setBounds (20*(pileNum+5) + c.getWidth()*(pileNum+3), 80+scoreview.getHeight()+c.getHeight(), c.getWidth(), c.getHeight());
			container.addWidget (kingfoundationview[pileNum]);
		}
				
		// create Tableau PileViews, one after the other.
		for (int pileNum = 1; pileNum <=4; pileNum++) {
			tableauview[pileNum] = new PileView (tableau[pileNum]);
			tableauview[pileNum].setBounds (20*(pileNum+5) + c.getWidth()*(pileNum+3), 100+scoreview.getHeight()+2*c.getHeight(), c.getWidth(), c.getHeight());
			container.addWidget (tableauview[pileNum]);
		}

		reserveview1 = new ColumnView(reserve1);
		reserveview1.setBounds(20,40+scoreview.getHeight(),c.getWidth(),c.getHeight());
		container.addWidget(reserveview1);
		reserveview2 = new ColumnView(reserve2);
		reserveview2.setBounds(80+reserveview1.getWidth(),40+scoreview.getHeight(),c.getWidth(),c.getHeight());
		container.addWidget(reserveview2);

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
		
		reserve1 = new Column("reserve1");
		model.addElement(reserve1);
		reserve2 = new Column("reserve2");
		model.addElement(reserve2);
		
		updateScore(0);
		updateNumberCardsLeft(104);
	}
	
	public static void main(String []args){
		Main.generateWindow(new AcesAndKings(), Deck.OrderBySuit);
	}
}
