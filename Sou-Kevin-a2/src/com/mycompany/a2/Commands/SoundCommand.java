package com.mycompany.a2.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.Game;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.CheckBox;

public class SoundCommand extends Command {
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent evt){
		System.out.println("Sound has been clicked");
		if (((CheckBox)evt.getComponent()).isSelected()) {//getComponent() returns the component
			//that generated the event
			gw.setSound(true);
			gw.getSound();
		}
		else {
			gw.setSound(false);
			gw.getSound();
		}
	}
}
