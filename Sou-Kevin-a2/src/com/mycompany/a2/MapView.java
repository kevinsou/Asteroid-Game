package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

	public MapView(){
		//Label map = new Label("Map: ");
		Container mapContainer = new Container();
		mapContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		mapContainer.getAllStyles().setPadding(RIGHT, 1024);
		mapContainer.getAllStyles().setPadding(BOTTOM, 768);
		mapContainer.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		//myContainer.getAllStyles().setPadding(LEFT, 600);
		//mapContainer.add(map);
		this.add(mapContainer);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Map Width: "+ Game.getMapWidth()+"  Map Height: "+ Game.getMapHeight());
		GameWorld gw = (GameWorld)o;
		GameCollection go = gw.getGameObjects();
		Iterator gameIterator = go.getIterator();
		while(gameIterator.hasNext()) {
			System.out.println(gameIterator.next());
		}
		System.out.println();
	}

}
