package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MissileKillsAsteroidCommand extends Command {
	private GameWorld gw;
	
	public MissileKillsAsteroidCommand(GameWorld gw) {
		super("Missile Kills Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.playerMissileKillAsteroid();
		System.out.println("Missile Kills Asteroid has been clicked");
	}
}
