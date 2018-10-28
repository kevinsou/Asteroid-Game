package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AsteroidImpactNPSCommand extends Command {
	private GameWorld gw;
	
	public AsteroidImpactNPSCommand(GameWorld gw) {
		super("Asteroid has Impacted an Enemy Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.asteroidColliededNPS();
		System.out.println("Asteroid has Impacted an Enemy Ship has been clicked");
	}
}
