package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.ISelectable;

public class Asteroid extends MoveableGameObject implements IDrawable,ICollider,ISelectable {
	
	public Asteroid() {
		super(255,255,255);
		Random rand= new Random();
		//size = rand.nextInt(24)+6; //size could be from 6-30
	}
	public String toString() {
		return ("ASTEROID: Location = "+getX()+","+getY()+
				", color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", size = "+getSize());
	}

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
		g.drawRect(x,y,this.getSize(),this.getSize());
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
			result = true;
		}
		return result;
	}
	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Asteroid) {
			this.setCollision("asteroidCollided");
			//System.out.println("test");
			//asteroidsCollided();
		}else if(otherObject instanceof NonPlayerShip) {
			this.setCollision("asteroidColliededNPS");
			//this.asteroidColliededNPS();
		}else if(otherObject instanceof Missile) {
			this.setCollision("playerMissileKillAsteroid");
			//playerMissileKillAsteroid();
		}else if(otherObject instanceof PlayerShip) {
			this.setCollision("psCrashAsteroid");
			//psCrashAsteroid();
		}
	}
	@Override
	public void selected(int x, int y,boolean s) {
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
