package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Game extends Form {
	private GameWorld gw;
	private boolean quit;
	
	public Game() {
		gw= new GameWorld();
		gw.init();
		play();
	}
	
	@SuppressWarnings("rawtypes")
	private void play() {
		Label myLabel = new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				switch (sCommand.charAt(0)) {
					case 'a': //creates an asteroid
						gw.addNewAsteroid();
						break;
					case 'y': //creates a nps
						gw.addNewNPS();
						break;
					case 'b': //creates a space station
						gw.addSpaceStation();
						break;
					case 's': // creates a ps
						gw.addPlayerShip();
						break;
					case 'i': // inc speed of ps
						gw.incPlayerShipSpeed();
						break;
					case 'd': // dec speed of ps
						gw.decPlayerShipSpeed();
						break;
					case 'l': //turn left on ps
						gw.steerLeftPS();
						break;
					case 'r': //turn right on ps
						gw.steerRightPS();
						break;
					case '<': //turn the ps missilelauncher
						gw.turnMissileLauncherPS();
						break;
					case 'f': //fire a missile from ps
						gw.fireMissilePS();
						break;
					case 'L': //launch a missile from nps
						gw.launchMissile();
						break;
					case 'j': //jump ps to the center
						gw.jumpCenter();
						break;
					case 'n': //reload ps missiles
						gw.reload();
						break;
					case 'k': //missile kills asteroid
						gw.killAsteroid();
						break;
					case 'e': //missile kills nps
						gw.eliminateNPS();
						break;
					case 'E': //missile kills ps
						gw.explodePS();
						break;
					case 'c': //ps crashes into asteroid
						gw.crash();
						break;
					case 'h': //ps hits the npc
						gw.hitNPC();
						break;
					case 'x': //asteroids collided
						gw.exterminate();
						break;
					case 'I': //asteroid collided with nps
						gw.impact();
						break;
					case 't': //tick
						gw.tick();
						break;
					case 'p': //print
						gw.print();
						break;
					case 'm': //map
						gw.map();
						break;
					case 'q': //quit
						System.out.println("ARE YOU SURE YOU WANT TO QUIT?");
						System.out.println("'1' to quit '0' to continue\n");
						quit=true;
						break;
					case '1':
						if(quit) {
							System.out.println("End Game");
							gw.quit();
						}
						break;
					case '0':
						if(quit) {
							System.out.println("Continue\n");
							quit=false;
							break;
						}
						break;
						
				//add code to handle rest of the commands
				}//switch
			}
		});
	}
}
