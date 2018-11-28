package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

public class SpaceStation extends FixGameObject implements IDrawable,ICollider {
	private int blinkRate;
	private boolean light;
	//private String switchLight;
	
	public SpaceStation(int id) {
		super(id, 229,245,163);
		Random rand = new Random();
		blinkRate = rand.nextInt(5);
		
		if (blinkRate == 4) {
			light = true;
		}else {
			light = false;
		}
	}
	public void setBlink(int blink) {
		blinkRate=blink;
	}
	public int getBlink() {
		return blinkRate;
	}
	public void incBlink() {
		blinkRate++;
	}
	public String getSwitchLight() {
		if(blinkRate==4) {
			return "On";
		}else {
			return "Off";
		}
	}
	public String toString() {
		return("SPACESTATION: Location = "+getX()+","+getY()+
				", ID = "+getId()+
				", Color = ["+getColorRed()+","+getColorGreen()+","+getColorBlue()+"]"+
				", Blink = "+getBlink())+
				", Status = "+ getSwitchLight();
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		g.setColor(ColorUtil.rgb(getColorRed(), getColorGreen(),getColorBlue()));	
		int px = (int) (pCmpRelPrnt.getX() + this.getX()); 
		int py = (int) (pCmpRelPrnt.getY() + this.getY()); 
	    if(blinkRate == 4) {
	    	g.fillArc(px, py, 30, 30, 0, 360);
	    } else {
	    	g.drawArc(px, py, 30, 30, 0, 360);
	    }
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
		if(otherObject instanceof SpaceStation && this.getBlink() == 4){
			this.setCollision("reload");
		}
	}
}
