package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public class SpaceStation extends FixGameObject {
	private int blinkRate;
	private boolean light;
	
	public SpaceStation(int id) {
		super(id, 125500);
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
	public String toString() {
		return("SPACESTATION: Location = "+getX()+","+getY()+
				", ID = "+getId()+
				", Color = ["+ColorUtil.red((getColor()))+","+ColorUtil.green((getColor()))+","+ColorUtil.blue((getColor()))+"]"+
				", Blink = "+getBlink());
	}
}
