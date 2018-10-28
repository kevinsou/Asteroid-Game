package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

public class GameObject extends GameWorld {
	private double xLocation;
	private double yLocation;
	private Point2D location = new Point2D(xLocation,yLocation);
	private int myColor;
	
	public GameObject(int col) {
		Random rand=new Random();
		location.setX(rand.nextInt(1025));
		location.setY(rand.nextInt(769));
		myColor=col;
	}
	public GameObject(double x, double y, int col) {
		location.setX(x);
		location.setY(y);
		myColor=col;
	}
	public int getColor() {
		return myColor;
	}
	public void setX(double x) {
		if(x>1024) {
			location.setX(x-1025);
		}else if(x<0) {
			location.setX(1025+x);
		}else {
			location.setX(x);
		}
	}
	public void setY(double y) {
		if(y>769) {
			location.setY(y-769);
		}else if(y<0) {
			location.setY(769+y);
		}else {
			location.setY(y);
		}
	}
	public double getX() {
		return location.getX();
	}
	public double getY() {
		return location.getY();
	}
}
