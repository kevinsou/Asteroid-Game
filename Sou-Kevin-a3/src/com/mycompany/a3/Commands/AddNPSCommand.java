package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddNPSCommand extends Command {
	private GameWorld gw;
	
	public AddNPSCommand(GameWorld gw) {
		super("Add Enemy Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.addNPS();
		System.out.println("Add Enemy Ship has been clicked");
	}
}
