package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class MissileExplodesPSCommand extends Command {
	private GameWorld gw;
	
	public MissileExplodesPSCommand(GameWorld gw) {
		super("Enemy Missile Explodes Player Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.npsStruckPS();
		System.out.println("Enemy Missile Explodes Player Ship has been clicked");
	}
}
