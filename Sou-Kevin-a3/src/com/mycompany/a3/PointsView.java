package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class PointsView extends Container implements Observer {

	private Label pointsValueLabel;
	private Label missileValueLabel;
	private Label timeValueLabel;
	private Label soundValueLabel;
	private Label livesValueLabel;
	
	public PointsView() {
		//setLayout(new BorderLayout());
		Label pointsTextLabel = new Label("Points: ");
		pointsValueLabel = new Label("XXX");
		Label missileTextLabel = new Label("Missile count: ");
		missileValueLabel = new Label("X");
		Label timeTextLabel = new Label("Elapsed time: ");
		timeValueLabel = new Label("X");
		Label soundTextLabel = new Label("SOUND: ");
		soundValueLabel = new Label("OFF");
		Label livesTextLabel = new Label("Lives: ");
		livesValueLabel = new Label("X");
		pointsValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		missileValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		timeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		livesValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0,0,255));
		//pointsValueLabel.getAllStyles().setPadding(1, 2);
		//container for the points
		Container pointViewContainer = new Container();
		pointViewContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		//adding the layers
		pointViewContainer.add(pointsTextLabel);
		pointViewContainer.add(pointsValueLabel);
		pointViewContainer.add(missileTextLabel);
		pointViewContainer.add(missileValueLabel);
		pointViewContainer.add(timeTextLabel);
		pointViewContainer.add(timeValueLabel);
		pointViewContainer.add(soundTextLabel);
		pointViewContainer.add(soundValueLabel);
		pointViewContainer.add(livesTextLabel);
		pointViewContainer.add(livesValueLabel);
		this.add(pointViewContainer);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		IGameWorld gw = (IGameWorld) arg;
		
		int score = gw.getPlayerScore();
		int missileCount = gw.getMissileCount();
		int time = gw.getTime();
		boolean sound = gw.getSound();
		int lives = gw.getLives();
		pointsValueLabel.setText(""+(score > 99 ? "" : "0")+(score > 9 ?"" : "0")+score);
		missileValueLabel.setText(""+(missileCount > 99 ? "" : "0")+(missileCount > 9?"" : "0")+missileCount);
		timeValueLabel.setText(""+(time > 99 ? "" : "0")+(time > 9?"" : "0")+time);
		if(sound) {
			soundValueLabel.setText("ON");
		}
		else {
			soundValueLabel.setText("OFF");
		}
		livesValueLabel.setText(""+(lives > 99 ? "" : "0")+(lives > 9?"" : "0")+lives);
		this.repaint();
	}

}
