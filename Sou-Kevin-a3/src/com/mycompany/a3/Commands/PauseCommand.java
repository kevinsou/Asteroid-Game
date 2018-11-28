package com.mycompany.a3.Commands;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class PauseCommand extends Command{

	private Game game;
	private GameWorld gw;
	private String name;
	
	public PauseCommand(Game game,GameWorld gw, String name) {
		super(name);
		this.game = game;
		this.gw=gw;
		this.name=name;
	}
	
	public void actionPerformed(ActionEvent e) {
		game.pauseNPlay();
		if(name.equals("Pause") && gw.getSound()==true) {
			gw.setBgSoundOn();
		}
	}
}
