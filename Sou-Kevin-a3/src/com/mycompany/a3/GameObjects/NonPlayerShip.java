package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

public class NonPlayerShip extends Ships implements IDrawable, ICollider{
	private int size;
	private MissileLauncher npsMissleLauncher;
	
	public NonPlayerShip() {
		super(2,255,255,255); //passed a 2 for missile count of nps
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
				", color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", size = "+getSize());
	}
	public MissileLauncher getMissileLauncher() {
		return npsMissleLauncher;
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
		int px = (int) (pCmpRelPrnt.getX() + this.getX()); 
		int py = (int) (pCmpRelPrnt.getY() + this.getY()); 
//		double x = this.getX();
//		double y = this.getY();
		int xpoints[] = {px+size, px-size, px};
	    int ypoints[] = {py+size, py+size, py-size};
	    int npoints = 3;
		g.fillPolygon(xpoints, ypoints, npoints);
		int length;
		if(size==20) {
			length = 30;
		} else {
			length = 20;
		}
		int dx = (int) ((size+length)*(Math.sin(Math.toRadians(getDirection()))));
		int dy = (int) ((size+length)*(Math.cos(Math.toRadians(getDirection()))));
		g.drawLine(px, py, px+dx, py+dy);
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
		if(otherObject instanceof Asteroid) {
			this.setCollision("asteroidColliededNPS");
			//asteroidColliededNPS();
		}else if(otherObject instanceof PlayerShip) {
			this.setCollision("psHitNPS");
			psHitNPS();
		}
	}

}
