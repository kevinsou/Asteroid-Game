package com.mycompany.a3.GameObjects;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IDrawable;

public class GameObject extends GameWorld {
	private double xLocation;
	private double yLocation;
	private Point2D location = new Point2D(xLocation,yLocation);
	private int colRed;
	private int colGreen;
	private int colBlue;
	private int size;
	private String collision;
	
	public GameObject(int red,int green,int blue) {
		Random rand=new Random();
		location.setX(rand.nextInt(1025));
		location.setY(rand.nextInt(769));
		colRed=red;
		colGreen=green;
		colBlue=blue;
		size = rand.nextInt(24)+6;
		collision = "";
	}
	public GameObject(int siz,double x, double y, int red,int green, int blue) {
		location.setX(x);
		location.setY(y);
		colRed = red;
		colGreen = green;
		colBlue = blue;
		size = siz;
		collision = "";
	}
	public void setCollision(String c) {
		collision = c;
	}
	public String getCollision() {
		return collision;
	}
	public int getColorRed() {
		return colRed;
	}
	public int getColorGreen() {
		return colGreen;
	}
	public int getColorBlue() {
		return colBlue;
	}
	public void setColorRed(int r) {
		colRed=r;
	}
	public void setColorGreen(int g) {
		colGreen=g;
	}
	public void setColorBlue(int b) {
		colBlue=b;
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
	
	public int getSize() {
		return size;
	}

}
