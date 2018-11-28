package com.mycompany.a3.GameObjects;

public abstract class Ships extends MoveableGameObject {
	private int missileCount;
	
	public Ships (int siz,int s, int d, double x, double y, int m, int red,int green, int blue) {
		super(siz, s,d, x, y, red,blue,green);
		missileCount=m;
	}
	public Ships (int m, int red,int green, int blue) {
		super(red, green, blue);
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
//	public void move() {
//		// TODO Auto-generated method stub
//		setX(getX()+((90-Math.cos(Math.toRadians(getDirection())))*getSpeed()));
//		setY(getY()+((90-Math.cos(Math.toRadians(getDirection())))*getSpeed()));
//		setX((Math.round(getX()*10))/10);
//		setY((Math.round(getY()*10))/10);
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

}
