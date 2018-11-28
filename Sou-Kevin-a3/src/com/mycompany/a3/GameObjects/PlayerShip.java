package com.mycompany.a3.GameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

public class PlayerShip extends Ships implements ISteerable,IDrawable,ICollider {
	private SteerableMissileLauncher psMissileLauncher;
	
	public PlayerShip() {
		super(20,0,0,512.0,384.0,10,255,255,255); //starts with 0 speed facing north(0) in the center (512,384)
		psMissileLauncher = new SteerableMissileLauncher(0,0,512.0,384.0);
	}
	public void turnMissileLauncherLeft() {
		psMissileLauncher.steerLeft();
	}
	public void turnMissileLauncherRight() {
		psMissileLauncher.steerRight();
	}
	public int getMissileLauncherDir() {
		return psMissileLauncher.getDirection();
	}
	public void speedMissileLauncher(int s){ //keeps the speed the same as the ps
		psMissileLauncher.setSpeed(s);
	}
	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub
		this.direction=direction+15;
		if(this.direction>359) {
			this.direction=direction-359;
		}
	}
	@Override
	public void steerRight() {
		// TODO Auto-generated method stub
		this.direction=direction-15;
		if(this.direction<0) {
			this.direction=direction+359;
		}
	}
	public String toString() {
		return ("PLAYERSHIP: Location = "+getX()+","+getY()+
				", color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", missiles = "+getMissileCount()+
				", missile launcher direction = " + psMissileLauncher.getDirection());
				
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(ColorUtil.rgb(getColorRed(), getColorGreen(),getColorBlue()));	
		int px = (int) (pCmpRelPrnt.getX() + this.getX()); 
		int py = (int) (pCmpRelPrnt.getY() + this.getY()); 
		double x = this.getX();
		double y = this.getY();
		int xpoints[] = {px+this.getSize(), px-this.getSize(), px};
	    int ypoints[] = {py-this.getSize(), py-this.getSize(), py+this.getSize()};
	    int npoints = 3;
		g.drawPolygon(xpoints, ypoints, npoints);
		int dx = (int) (30*(Math.sin(Math.toRadians(getMissileLauncherDir()))));
		int dy = (int) (30*(Math.cos(Math.toRadians(getMissileLauncherDir()))));
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
			result = true;
		}
		return result;
	}
	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Asteroid) {
			this.setCollision("psCrashAsteroid");
			//psCrashAsteroid();
		}else if(otherObject instanceof NonPlayerShip) {
			this.setCollision("psHitNPS");
			//psHitNPS();
		}else if(otherObject instanceof SpaceStation && (((SpaceStation) otherObject).getBlink() == 4)){
			this.setCollision("reload");
		}
	}

}
