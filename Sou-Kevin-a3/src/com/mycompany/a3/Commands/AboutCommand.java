package com.mycompany.a3.Commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.a3.GameWorld;

public class AboutCommand extends Command {
	private GameWorld gw;
	private final Button show = new Button("Show Popup");
	
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("About has been clicked");
		Dialog about = new Dialog();
		TextArea popUp = new TextArea("This is an Asteroid Game");
		popUp.setUIID("popUpBody");
		popUp.setEditable(false);
		
		about.setLayout(new BorderLayout());
		about.add(BorderLayout.CENTER,popUp);
		about.showPopupDialog(show);
	}
}
