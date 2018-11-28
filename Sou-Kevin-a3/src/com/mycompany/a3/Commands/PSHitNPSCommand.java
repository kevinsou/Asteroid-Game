package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PSHitNPSCommand extends Command {
	private GameWorld gw;
	
	public PSHitNPSCommand(GameWorld gw) {
		super("Player Ship has Hit a Enemy Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.psHitNPS();
		System.out.println("Player Ship has Hit a Enemy Ship has been clicked");
	}
}
