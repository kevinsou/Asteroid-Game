package com.mycompany.a2.GameObjects;

public class MissileLauncher extends MoveableGameObject{
	
	public MissileLauncher(int s, int d, double x, double y) {
		super(s,d,x,y,5);
	}
	public MissileLauncher() {
		super(5);
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
		setX(getX()+(Math.cos((90-(getDirection())))*getSpeed()));
		setY(getY()+(Math.cos((90-(getDirection())))*getSpeed()));
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}
}
