package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class PlayerShip extends Ships implements ISteerable {
	private SteerableMissileLauncher psMissileLauncher;
	
	public PlayerShip() {
		super(0,0,512.0,384.0,10,202220); //starts with 0 speed facing north(0) in the center (512,384)
		psMissileLauncher = new SteerableMissileLauncher(0,0,512.0,384.0);
	}
	public void turnMissileLauncher() {
		psMissileLauncher.steerLeft();
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
				", color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
				", speed = "+getSpeed()+
				", direction = "+getDirection()+
				", missiles = "+getMissileCount()+
				", missile launcher direction = " + psMissileLauncher.getDirection());
				
	}

}
