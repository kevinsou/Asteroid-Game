package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.ISelectable;

public class Missile extends MoveableGameObject implements IDrawable,ICollider,ISelectable{
	private int fuelLevel;
	private int identifier;
	
	public Missile(int s, int d, double x, double y, int i) {
		super(7,s,d,x,y,255,255,255);
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
					", color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
					", speed = "+getSpeed()+
					", direction = "+getDirection()+
					", fuel = "+getFuel());
		}else {
			return ("NPS MISSILE: Location = "+getX()+","+getY()+
					", color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
					", speed = "+getSpeed()+
					", direction = "+getDirection()+
					", fuel = "+getFuel());
		}
	}
	
	@Override
//	public void move() {
//		// TODO Auto-generated method stub
//		setX(getX()+(Math.cos((90-Math.toRadians(getDirection()))+getSpeed())));
//		setY(getY()+(Math.cos((90-Math.toRadians(getDirection()))+getSpeed())));
//		setX((Math.round(getX()*10))/10);
//		setY((Math.round(getY()*10))/10);
//	}
//	
	public void move() {
		double delta= Math.toRadians(90-getDirection());
		double deltaX = (Math.cos(delta)*(getSpeed()+20));
		double deltaY = (Math.sin(delta)*(getSpeed()+20));
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
		g.drawArc(x,y, this.getSize(), this.getSize(), 0, 360);
	}
	@Override
	public boolean collidesWith(ICollider otherObject) {
		// TODO Auto-generated method stub
		Boolean result = false;
		int thisCenterX = (int)(this.getX() + (this.getSize()/2));
		int thisCenterY = (int)(this.getY() + (this.getSize()/2));
		int otherCenterX = (int)(((GameObject) otherObject).getX() +((GameObject) otherObject).getSize()/2);
		int otherCenterY = (int)(((GameObject) otherObject).getY() +((GameObject) otherObject).getSize()/2);
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distance = (dx * dx) + (dy * dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = ((GameObject)otherObject).getSize()/2;
		
		int rad = (thisRadius * thisRadius) + (2 * thisRadius) + (otherRadius * otherRadius);
		if(distance <= rad) {
			System.out.println("test");
			result = true;
		}
		return result;
	}
	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Asteroid && this.getIdentifier()==1) {
			this.setCollision("playerMissileKillAsteroid");
			
		}else if(otherObject instanceof NonPlayerShip && this.getIdentifier()==1) {
			this.setCollision("playerMissileEliminateNPS");
			
		}else if(otherObject instanceof PlayerShip && this.getIdentifier()==0) {
			this.setCollision("npsStruckPS");
		}
	}
	@Override
	public void selected(int x, int y, boolean s) {
		// TODO Auto-generated method stub
		if((x>=this.getX()-this.getSize()) && 
				(x<=this.getX()+this.getSize()) && 
				(y>=this.getY()-this.getSize()) && 
				(y<=this.getY()+this.getSize())) {
			setColorRed(255);
			setColorGreen(0);
			setColorBlue(0);
		}
	}
	@Override
	public void unselect() {
		// TODO Auto-generated method stub
		setColorRed(255);
		setColorGreen(255);
		setColorBlue(255);
	}

}
