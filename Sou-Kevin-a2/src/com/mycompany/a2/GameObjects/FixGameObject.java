package com.mycompany.a2.GameObjects;

public class FixGameObject extends GameObject {
	private static int idNum;
	
	public FixGameObject (int id, int color) {
		super(color);
		idNum = id;
	}
	public void setId(int id) {
		idNum = id;
	}
	public int getId() {
		return idNum;
	}
}
