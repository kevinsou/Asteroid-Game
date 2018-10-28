package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class JumpCommand extends Command {
	private GameWorld gw;
	
	public JumpCommand(GameWorld gw) {
		super("Jump Through Hyperspace");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.jump();
		System.out.println("Jump Through Hyperspace has been clicked");
	}
}
