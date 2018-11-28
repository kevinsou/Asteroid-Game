package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddAsteroidCommand extends Command {
	private GameWorld gw;
	private boolean p;
	
	public AddAsteroidCommand(GameWorld gw,boolean p) {
		super("Add Asteroid");
		this.gw = gw;
		this.p=p;
	}
	
	public void actionPerformed(ActionEvent e) {

		gw.addAsteroid();
		System.out.println("Add Asteroid has been clicked");

	}
}
