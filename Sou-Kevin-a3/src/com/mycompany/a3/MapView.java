package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.PointerPressListener;
import com.mycompany.a3.GameObjects.Asteroid;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.Missile;
import com.mycompany.a3.GameObjects.Ships;

public class MapView extends Container implements Observer {

	private boolean select=false;
	private GameWorld gwProxy;
	private int cordX=0;
	private int cordY=0;
	GameCollection go;
	
	public MapView(GameWorld gw){
		Label map = new Label("Map: ");
		Container mapContainer = new Container();
		mapContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		mapContainer.getAllStyles().setPadding(RIGHT, 1024);
		mapContainer.getAllStyles().setPadding(BOTTOM, 768);
		mapContainer.getAllStyles().setBgTransparency(200);
		mapContainer.getAllStyles().setBgColor(ColorUtil.rgb(38, 38, 38));
		mapContainer.getAllStyles().setBorder(Border.createLineBorder(5,ColorUtil.BLACK));
		PointerPressListener pressListener = new PointerPressListener(this);
		mapContainer.addPointerPressedListener(pressListener);
		this.add(mapContainer);
		gwProxy=gw;
	}
	@Override
	public void pointerPressed(int px, int py) {
		cordX=px-this.getAbsoluteX();
		cordY=py-this.getAbsoluteY();
		
	}
	public int getCordX() {
		return cordX;
	}
	public int getCordY() {
		return cordY;
	}
	public void selector() {
		select=true;
	}
	public void deselector() {
		select=false;
	}
	public void paint(Graphics g) {
		super.paint(g);
		go = gwProxy.getCollection();
		Iterator gameIterator = go.getIterator();
		//System.out.println("test");
		GameObject holder = new GameObject(1,2,3);
		while (gameIterator.hasNext()) {
			holder = (GameObject) gameIterator.next();
			Point pCmpRelPrnt = new Point(this.getX(), this.getY());
			((IDrawable) holder).draw(g, pCmpRelPrnt);
			//System.out.println(holder);
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Map Width: "+ Game.getMapWidth()+"  Map Height: "+ Game.getMapHeight());
		if(o instanceof IGameWorld) {
			gwProxy = (GameWorld)o;
	//		GameCollection go = gw.getGameObjects();
	//		Iterator gameIterator = go.getIterator();
	//		while(gameIterator.hasNext()) {
	//			System.out.println(gameIterator.next());
	//		}
	//		System.out.println();
			repaint();
		}
	}
	public void checkIfSelected() {
		if(select) {
			go = gwProxy.getCollection();
			Iterator gameIterator = go.getIterator();
			//System.out.println("test");
			GameObject holder = new GameObject(1,2,3);
			while (gameIterator.hasNext()) {
				holder = (GameObject) gameIterator.next();
				if(holder instanceof Asteroid || holder instanceof Missile) {
					((ISelectable) holder).selected(cordX,cordY,select);
				}
				
			}
		}
	}
	public void unSelectAll() {
		go = gwProxy.getCollection();
		Iterator gameIterator = go.getIterator();
		GameObject holder = new GameObject(1,2,3);
		while (gameIterator.hasNext()) {
			holder = (GameObject) gameIterator.next();
			if(holder instanceof Asteroid || holder instanceof Missile) {
				((ISelectable)holder).unselect();
			}
		}
	}

}
