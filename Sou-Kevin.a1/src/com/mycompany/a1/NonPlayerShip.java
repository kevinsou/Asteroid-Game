package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerShip extends Ships {
	private int size;
	private MissileLauncher npsMissleLauncher;
	
	public NonPlayerShip() {
		super(2,103330); //passed a 2 for missile count of nps
		MissileLauncher mpsMissleLauncher = new MissileLauncher();
		Random rand=new Random();
		if(rand.nextInt(2)==1) {
			size = 10; //small
		}else {
			size = 20; //large
		}
	}
	public void setSize(int siz) {
		
	}
	public int getSize() {
		return size;
	}
	public String toString() {
		return ("NON-PLAYERSHIP: Location = "+getX()+","+getY()+
				", color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", size = "+getSize());
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		setX(getX()+(Math.cos(Math.toRadians(getDirection())+getSpeed())));
		setY(getY()+(Math.cos(Math.toRadians(getDirection())+getSpeed())));
		setX(Math.round(getX()*10)/10);
		setY(Math.round(getY()*10)/10);
	}

}
