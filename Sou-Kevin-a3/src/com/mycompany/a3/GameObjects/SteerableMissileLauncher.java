package com.mycompany.a3.GameObjects;

import com.mycompany.a3.IDrawable;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable,IDrawable {
	
	public SteerableMissileLauncher (int s, int d, double x, double y) {
		super(20,s,d,x,y);
	}
	@Override
	public void steerLeft() {
		// TODO Auto-generated method stub
		this.direction=direction+10;
		if(this.direction>359) {
			this.direction=direction-359;
		}
	}

	@Override
	public void steerRight() {
		// TODO Auto-generated method stub
		this.direction = direction-10;
		if(this.direction<0) {
			this.direction = direction+ 359;
		}
	}
	

}
