package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RotateMissileLauncherRightCommand extends Command {
	private GameWorld gw;
	
	public RotateMissileLauncherRightCommand(GameWorld gw) {
		super("Rotate Missile Launcher Right");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.rotLauncherRight();
		System.out.println("Rotate Missile Launcher has been clicked");
	}
}
