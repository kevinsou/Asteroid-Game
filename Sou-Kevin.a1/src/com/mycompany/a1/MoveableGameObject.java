package com.mycompany.a1;

import java.util.Random;

public abstract class MoveableGameObject extends GameObject implements IMoveable{
	private int speed;
	protected int direction;
	
	public MoveableGameObject(int s, int d, double x, double y, int myColor) {
		super(x,y,myColor);
		speed=s;
		direction=d;
	}
	public MoveableGameObject(int myColor) {
		super(myColor);
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
	public void move() {
		setX(getX()+(Math.cos((90-Math.toRadians(getDirection())))*getSpeed()));
		setY(getY()+(Math.cos((90-Math.toRadians(getDirection())))*getSpeed()));
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}
	
}
