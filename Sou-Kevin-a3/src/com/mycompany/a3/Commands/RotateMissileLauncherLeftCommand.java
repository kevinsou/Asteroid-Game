package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RotateMissileLauncherLeftCommand extends Command {
	private GameWorld gw;
	
	public RotateMissileLauncherLeftCommand(GameWorld gw) {
		super("Rotate Missile Launcher Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.rotLauncherLeft();
		System.out.println("Rotate Missile Launcher has been clicked");
	}
}
