package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class LaunchMissileCommand extends Command {
	private GameWorld gw;
	
	public LaunchMissileCommand(GameWorld gw) {
		super("NPS Launch Missile");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.launchMissileNPS();
		System.out.println("NPS Launch Missile has been clicked");
	}
}
