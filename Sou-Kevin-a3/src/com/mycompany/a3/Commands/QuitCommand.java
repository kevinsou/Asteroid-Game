package com.mycompany.a3.Commands;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.a3.GameWorld;

public class QuitCommand extends Command {
	private GameWorld gw;
	private final Button show = new Button("Show Popup");
	
	public QuitCommand(GameWorld gw) {
		super("Quit");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Quit has been clicked");
		Dialog quit = new Dialog();
		Button yes = new Button("Yes");
		Button no = new Button("No");
		
		Container choose = new Container(new FlowLayout());
		choose.add(yes);
		choose.add(no);
		
		TextArea popUp = new TextArea("Do you want to quit?");
		popUp.setUIID("popUpBody");
		popUp.setEditable(false);
		
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				quit.dispose();
			}
			
		});
		quit.setLayout(new BorderLayout());
		quit.add(BorderLayout.CENTER, popUp);
		quit.add(BorderLayout.SOUTH, choose);
		quit.showPopupDialog(show);
	}
}
