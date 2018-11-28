package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FireMissileCommand extends Command {
	private GameWorld gw;
	
	public FireMissileCommand(GameWorld gw) {
		super("Fire Missile");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.fireMissilePS();
		System.out.println("Fire Missile has been clicked");
	}
}
