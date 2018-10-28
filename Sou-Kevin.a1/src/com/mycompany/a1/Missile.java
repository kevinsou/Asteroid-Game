package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Missile extends MoveableGameObject {
	private int fuelLevel;
	private int identifier;
	
	public Missile(int s, int d, double x, double y, int i) {
		super(s,d,x,y,232250);
		fuelLevel = 10;
		identifier = i;
	}
	public void setFuel(int f) {
		fuelLevel = f;
	}
	public int getFuel() {
		return fuelLevel;
	}
	public void decFuel() {
		fuelLevel--;
	}
	public int getIdentifier(){
		return identifier;
	}
	public String toString() {
		if(this.identifier==1) {
			return ("PS MISSILE: Location = "+getX()+","+getY()+
					", color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
					", speed = "+getSpeed()+
					", direction = "+getDirection()+
					", fuel = "+getFuel());
		}else {
			return ("NPS MISSILE: Location = "+getX()+","+getY()+
					", color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
					", speed = "+getSpeed()+
					", direction = "+getDirection()+
					", fuel = "+getFuel());
		}
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		setX(getX()+(Math.cos((90-Math.toRadians(getDirection()))+getSpeed())));
		setY(getY()+(Math.cos((90-Math.toRadians(getDirection()))+getSpeed())));
		setX((Math.round(getX()*10))/10);
		setY((Math.round(getY()*10))/10);
	}

}
