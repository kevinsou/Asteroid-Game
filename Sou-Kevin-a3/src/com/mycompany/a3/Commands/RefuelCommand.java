package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class RefuelCommand extends Command {
	private GameWorld gw;
	private Game g;
	
	public RefuelCommand(GameWorld gw, Game game) {
		super("Refuel");
		this.gw = gw;
		this.g = game;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(g.getIsPause()) {
			//System.out.println("hi");
			gw.refuel();
		}else {
			System.out.println("play");
		}
	}
}
