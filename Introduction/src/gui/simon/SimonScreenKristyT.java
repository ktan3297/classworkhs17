package gui.simon;

import java.awt.Color;
import java.util.ArrayList;
import gui.components.Action;

import gui.components.TextLabel;
import gui.components.Visible;
import gui.sampleGames.ClickableScreen;

public class SimonScreenKristyT extends ClickableScreen implements Runnable {

	private ArrayList<MoveInterfaceKristyT> moveSequence1;
	private TextLabel label1;
	private ButtonInterfaceKristyT[] button1;
	private ProgressInterfaceKristyT progress1;
	
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	
	public SimonScreenKristyT(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		label1.setText("");
	    nextRound();
	}

	private void nextRound() {
		// TODO Auto-generated method stub
		acceptingInput = false;
		roundNumber++;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress1 = getProgress();
		label1 = new TextLabel(130, 230, 300, 40,"Let's play Simon!");
		moveSequence1 = new ArrayList<MoveInterfaceKristyT>();
		lastSelectedButton = -1;
		moveSequence1.add(randomMove());
		moveSequence1.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress1);
		viewObjects.add(label1);
	}

	private MoveInterfaceKristyT randomMove() {
	//	ButtonInterfaceKristyT b;
		int randomNum = (int) (Math.random() * button1.length);
		//code that randomly selects a ButtonInterfaceX
		while(randomNum == lastSelectedButton){
			randomNum = (int) (Math.random() * button1.length);
		}
		return getMove(button1[randomNum]);
	}

	private ProgressInterfaceKristyT getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	private void addButtons() {
		int numOfButtons = 6;
		Color[] colors1 = {Color.blue, Color.green, Color.yellow, Color.red, Color.orange, Color.black};
		button1 = new ButtonInterfaceKristyT[numOfButtons];
		for(int i = 0; i < numOfButtons; i++){
			//doesn't have 'final'
			button1[i] = getAButton();
			button1[i].setColor(colors1[i]);
		    button1[i].setX(150);
		    button1[i].setY(250);
		    button1[i].setAction(new Action(){
		    	public void act(){
		    		if(acceptingInput){
		    			Thread blink = new Thread(new Runnable(){
		    				public void run(){
		    					button1[i].highlight();
		    					try{
		    						Thread.sleep(800);
		    					}
		    					catch(InterruptedException e) {
		    						e.printStackTrace();
		    					}
		    					button1[i].dim();
		    				}
		    			});
		    			blink.start();
		    			if(button1[i] == moveSequence1.get(sequenceIndex).getButton()){
		    				sequenceIndex++;
		    			}
		    			else{
		    				ProgressInterfaceKristyT.gameOver();
		    			}
		    			if(sequenceIndex == moveSequence1.size()){
		    				Thread nextRound = new Thread(SimonScreenKristyT.this);
							nextRound.start(); 
		    			}
		    		}
		    	}
		    });
		    //add view objects
		}
	}

	private ButtonInterfaceKristyT getAButton() {
		// TODO Auto-generated method stub
		return null;
	}
}