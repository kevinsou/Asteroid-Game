package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PSCrashAsteroidCommand extends Command {
	private GameWorld gw;
	
	public PSCrashAsteroidCommand(GameWorld gw) {
		super("Player Ship Crashed into an Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.psCrashAsteroid();
		System.out.println("Player Ship Crashed into an Asteroid has been clicked");
	}
}
