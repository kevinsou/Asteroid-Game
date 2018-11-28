package com.mycompany.a3.GameObjects;

public class FixGameObject extends GameObject {
	private static int idNum;
	
	public FixGameObject (int id, int red,int green, int blue) {
		super(red,green,blue);
		idNum = id;
	}
	public void setId(int id) {
		idNum = id;
	}
	public int getId() {
		return idNum;
	}
}
