package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MissileEliminatesNPSCommand extends Command {
	private GameWorld gw;
	
	public MissileEliminatesNPSCommand(GameWorld gw) {
		super("Missile Eliminates Enemy Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.playerMissileEliminateNPS();
		System.out.println("Missile Eliminates Enemy Ship has been clicked");
	}
}
