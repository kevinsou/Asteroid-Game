package com.mycompany.a1;

public abstract class Ships extends MoveableGameObject {
	private int missileCount;
	
	public Ships (int s, int d, double x, double y, int m, int color) {
		super(s,d, x, y, color);
		missileCount=m;
	}
	public Ships (int m, int color) {
		super(color);
		missileCount=m;
	}
	public void setMissileCount(int m) {
		missileCount = m;
	}
	public int getMissileCount() {
		return missileCount;
	}
	public void launchMissile() {
		missileCount--;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		setX(getX()+((90-Math.cos(Math.toRadians(getDirection())))*getSpeed()));
		setY(getY()+((90-Math.cos(Math.toRadians(getDirection())))*getSpeed()));
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}

}
