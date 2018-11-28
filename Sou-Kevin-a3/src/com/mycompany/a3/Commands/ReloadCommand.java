package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ReloadCommand extends Command {
	private GameWorld gw;
	
	public ReloadCommand(GameWorld gw) {
		super("Reload Missiles");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.reload();
		System.out.println("Reload Missiles has been clicked");
	}
}
