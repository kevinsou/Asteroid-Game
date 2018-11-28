package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {

	private GameWorld realgw;
	
	public GameWorldProxy(GameWorld gw) {
		realgw = gw;
	}
	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return realgw.getPlayerScore();
	}
	
	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		return realgw.getMissileCount();
	}
	
	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return realgw.getTime();
	}
	
	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return realgw.getLives();
	}
	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return realgw.getSound();
	}


}
