package com.mycompany.a1;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable {
	
	public SteerableMissileLauncher (int s, int d, double x, double y) {
		super(s,d,x,y);
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
		
	}
	

}
