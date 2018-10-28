package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class IncreaseSpeedCommand extends Command {
	private GameWorld gw;
	
	public IncreaseSpeedCommand(GameWorld gw) {
		super("Increase Speed");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.incSpeed();
		System.out.println("Increase Speed has been clicked");
	}
}
