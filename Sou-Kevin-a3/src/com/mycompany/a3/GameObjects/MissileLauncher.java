package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.IDrawable;

public class MissileLauncher extends MoveableGameObject implements IDrawable{
	
	public MissileLauncher(int siz, int s, int d, double x, double y) {
		super(siz,s,d,x,y,255,255,255);
	}
	public MissileLauncher() {
		super(128,21,21);
	}

	@Override
//	public void move() {
//		// TODO Auto-generated method stub
//		setX(getX()+(Math.cos(Math.toRadians(getDirection())+getSpeed())));
//		setY(getY()+(Math.cos(Math.toRadians(getDirection())+getSpeed())));
//		setX(Math.round(getX()*10)/10);
//		setY(Math.round(getY()*10)/10);
//	}

	public void move() {
		double delta= Math.toRadians(90-getDirection());
		double deltaX = (Math.cos(delta)*getSpeed());
		double deltaY = (Math.sin(delta)*getSpeed());
		setX(getX() + deltaX);
		setY(getY() + deltaY);
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(ColorUtil.rgb(getColorRed(), getColorGreen(),getColorBlue()));
		int x = (int) (pCmpRelPrnt.getX()+ this.getX());
		int y = (int) (pCmpRelPrnt.getY()+ this.getY());
		int dx = (int) Math.cos(Math.toRadians(getDirection()));
		int dy = (int) Math.sin(Math.toRadians(getDirection()));	
		g.drawLine(x, y, x+(dx*this.getSize()), y+(dy*this.getSize()));
	}
}
