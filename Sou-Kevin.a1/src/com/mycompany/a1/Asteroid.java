package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class Asteroid extends MoveableGameObject {
	private int size;
	
	public Asteroid() {
		super(145400);
		Random rand= new Random();
		size = rand.nextInt(24)+6; //size could be from 6-30
	}
	public String toString() {
		return ("ASTEROID: Location = "+getX()+","+getY()+
				", color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", size = "+getSize());
	}
	public void setSize(int siz) {
		size=siz;
	}
	public int getSize() {
		return size;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		setX(getX()+(Math.cos((90-Math.toRadians(getDirection()))*getSpeed())));
		setY(getY()+(Math.cos((90-Math.toRadians(getDirection()))*getSpeed())));
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}

}
