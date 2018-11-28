package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.mycompany.a3.IDrawable;

public abstract class MoveableGameObject extends GameObject implements IMoveable{
	private int speed;
	protected int direction;
	
	public MoveableGameObject(int siz, int s, int d, double x, double y, int red, int green, int blue) {
		super(siz,x,y,red,green,blue);
		speed=s;
		direction=d;
	}
	public MoveableGameObject(int red, int green, int blue) {
		super(red,green,blue);
		Random rand = new Random();
		speed = rand.nextInt(11);
		direction = rand.nextInt(360);
	}
	public void setSpeed(int s) {
		speed=s;
	}
	public void setDirection(int d) {
		direction=d;
	}
	public int getSpeed() {
		return speed;
	}
	public int getDirection() {
		return direction;
	}
	public void incSpeed() { //increases the speed of the object by 1
		speed++;
	}
	public void decSpeed() { //decreases the speed of the object by 1
		speed--;
	}
//	public void move() {
//		setX(getX()+(Math.cos((90-Math.toRadians(getDirection())))*getSpeed()));
//		setY(getY()+(Math.cos((90-Math.toRadians(getDirection())))*getSpeed()));
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