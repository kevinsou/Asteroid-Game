package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AsteroidCollideCommand extends Command {
	private GameWorld gw;
	
	public AsteroidCollideCommand(GameWorld gw) {
		super("Two Asteroids have Collided");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.asteroidsCollided();
		System.out.println("Two Asteroids have Collided has been clicked");
	}
}
