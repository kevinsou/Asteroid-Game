package com.mycompany.a3;

import java.util.Iterator;
import java.util.Vector;

import com.mycompany.a3.GameObjects.GameObject;

public class GameCollection implements ICollection{

	private Vector<GameObject> theCollection;
	
	public GameCollection() {
		theCollection = new Vector<GameObject>();
	}
	
	@Override
	public void add(Object o) {
		// TODO Auto-generated method stub
		theCollection.addElement((GameObject) o);
	}

	@Override
	public Iterator<Object> getIterator() {
		// TODO Auto-generated method stub
		return new GameVectorIterator();
	}
	
	private class GameVectorIterator implements Iterator<Object>{
		private int currElementIndex;
		
		public GameVectorIterator() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(theCollection.size()<= 0) {
				return false;
			}
			if(currElementIndex == theCollection.size()-1) {
				return false;
			}
			//currElementIndex++;
			return true;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			currElementIndex++;
			return (theCollection.elementAt(currElementIndex));
		}
		

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			theCollection.remove(currElementIndex);
		}
	}

}
