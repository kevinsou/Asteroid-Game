package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddStationCommand extends Command {
	private GameWorld gw;
	
	public AddStationCommand(GameWorld gw) {
		super("Add Space Staion");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.addStation();
		System.out.println("Add Space Station has been clicked");
	}
}
