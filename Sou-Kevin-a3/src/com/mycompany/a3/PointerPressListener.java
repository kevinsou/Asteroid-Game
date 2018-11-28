package com.mycompany.a3;

import java.util.Iterator;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.GameObjects.Asteroid;
import com.mycompany.a3.GameObjects.GameObject;

public class PointerPressListener implements com.codename1.ui.events.ActionListener {
	private MapView map;

	public PointerPressListener(MapView m) {
		// TODO Auto-generated constructor stub
		map=m;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		//System.out.println(evt.getX()+","+evt.getY());
		map.pointerPressed(evt.getX(),evt.getY());
		//map.selector();
		map.unSelectAll();
		map.checkIfSelected();
//		System.out.println(map.getCordX()+","+map.getCordY());
	}

}
