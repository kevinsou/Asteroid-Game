package com.mycompany.a2;

import java.util.Iterator;
import java.util.Observable;

import com.mycompany.a2.GameObjects.SpaceStation;
import com.mycompany.a2.GameObjects.PlayerShip;
import com.mycompany.a2.GameObjects.Ships;
import com.mycompany.a2.GameObjects.Missile;
import com.mycompany.a2.GameObjects.IMoveable;
import com.mycompany.a2.GameObjects.Asteroid;
import com.mycompany.a2.GameObjects.GameObject;
import com.mycompany.a2.GameObjects.MoveableGameObject;
import com.mycompany.a2.GameObjects.NonPlayerShip;

public class GameWorld extends Observable implements IGameWorld {
	private int id;
	private int score=0;
	private int lives=3;
	private int counter=0;
	private int gameclock=0;
	private GameCollection go;
	private boolean soundOn;
	public GameWorld() {
		go = new GameCollection();
		this.init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
	}

	public GameCollection getGameObjects() {
		return go;
	}
	
	@Override
	public int getPlayerScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	@Override
	public int getMissileCount() {
		// TODO Auto-generated method stub
		int count = 0;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if((obj) instanceof PlayerShip) {
				count = obj.getMissileCount(); 
			}
		}
		if(count>0) {
			return count;
		}
		else {
			return 0;
		}
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return gameclock;
	}
	
	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		if(lives>0) {
			return lives;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public boolean getSound() {
		// TODO Auto-generated method stub
		return soundOn;
	}
	public void setSound(boolean sound) {
		soundOn= sound;
		if(sound == true) {
			System.out.println("Sound on. \n");
		}
		else {
			System.out.println("Sound off. \n");
		}
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	public void addAsteroid() {
		// TODO Auto-generated method stub
		Asteroid asteroid = new Asteroid(); //create an asteroid object
		go.add(asteroid); //add asteroid to storage vector
		System.out.println("A new ASTEROID has been created.\n"); //tell the user that you created an asteroid
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void addNPS() {
		// TODO Auto-generated method stub
		NonPlayerShip nonPlayerShip = new NonPlayerShip(); //create a nps object
		go.add(nonPlayerShip); //add nps to storage vector
		System.out.println("A new NON-PLAYERSHIP has been created\n");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		
	}

	public void addStation() {
		// TODO Auto-generated method stub
		SpaceStation spaceStation = new SpaceStation(id); //create a spacestation
		id++;
		go.add(spaceStation); //add spacestation to storage vector
		System.out.println("A new SPACESTATION has been created\n");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void addPS() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) { // test if there exist a player ship
			if((theElements.next()) instanceof PlayerShip) {
				System.out.println("ERROR ALREADY EXIST A PLAYERSHIP\n");
				return;
			}
		}
		PlayerShip playerShip = new PlayerShip(); //creates a playership
		go.add(playerShip); //adds playership to store
		System.out.println("A new PLAYERSHIP has been created\n");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}

	public void incSpeed() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) { //checks for playership
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				if(((MoveableGameObject) obj).getSpeed()==10) {
					System.out.println("MAX SPEED\n");
					return;
				}
				((MoveableGameObject) obj).incSpeed();
				((PlayerShip) obj).speedMissileLauncher(((PlayerShip) obj).getSpeed());
				System.out.println("PLAYERSHIP: speed = "+ ((PlayerShip) obj).getSpeed()+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void decSpeed() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext())  {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				if(((PlayerShip) obj).getSpeed()==0) {
					System.out.println("ERROR PLAYERSHIP SPEED CANNOT BE LESS THAN 0\n");
					return;
				}
				((PlayerShip)obj).decSpeed();
				((PlayerShip) obj).speedMissileLauncher(((PlayerShip) obj).getSpeed());
				System.out.println("PLAYERSHIP: speed = "+ ((PlayerShip) obj).getSpeed()+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void turnLeft() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext())  {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip)obj).steerLeft();
				System.out.println("PLAYERSHIP: direction = "+ ((MoveableGameObject)obj).getDirection()+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void turnRight() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext())  {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip)obj).steerRight();
				System.out.println("PLAYERSHIP: direction = "+ ((MoveableGameObject) obj).getDirection()+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void rotLauncherLeft() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip)obj).turnMissileLauncherLeft();
				System.out.println("PLAYERSHIP MISSILELAUNCHER: direction = "+ (((PlayerShip) obj).getMissileLauncherDir())+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
	
	public void rotLauncherRight() {
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip)obj).turnMissileLauncherRight();
				System.out.println("PLAYERSHIP MISSILELAUNCHER: direction = "+ (((PlayerShip) obj).getMissileLauncherDir())+"\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void fireMissilePS() {
		// TODO Auto-generated method stub
		boolean check=true;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()){
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				if(((PlayerShip)obj).getMissileCount()>=0) {
					Missile psMissile = new Missile(10,((PlayerShip) obj).getMissileLauncherDir(),((PlayerShip) obj).getX(),((PlayerShip)obj).getY(),1);
					go.add(psMissile);
					System.out.println("A new MISSILE has been created\n");
					((PlayerShip) obj).launchMissile();
					System.out.println("PLAYERSHIP HAS FIRED A MISSILE!\n");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("ERROR NO MORE MISSILES\n");
					return;
				}
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void launchMissileNPS() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof NonPlayerShip) {
				if(((Ships) obj).getMissileCount()>=0) {
					Missile npcMissile = new Missile(10,((NonPlayerShip) obj).getDirection(),((NonPlayerShip) obj).getX(),( (NonPlayerShip) obj).getY(),0);
					go.add(npcMissile);
					System.out.println("A new MISSILE has been created");
					((Ships)obj).launchMissile();
					System.out.println("NONPLAYERSHIP HAS FIRED A MISSILE!\n");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
					return;
				}
				else {
					System.out.println("ERROR NO MORE MISSILES\n");
					return;
				}
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void jump() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).setX(512.0);
				((PlayerShip) obj).setY(384.0);
				System.out.println("PLAYERSHIP HAS JUMP THROUGH HYPERSPACE\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void reload() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).setMissileCount(10);
				System.out.println("MISSILES HAS BEEN RELOADED\n");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

	public void playerMissileKillAsteroid() {
		// TODO Auto-generated method stub
		boolean asteroidPresent=false;
		boolean psMissilePresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1) {
				psMissilePresent=true;
			}
		}
		if(asteroidPresent==true && psMissilePresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof Asteroid) {
					tracker1.remove();
					//System.out.println("ASTEROID HAS BEEN DESTROYED");
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof Missile && ((Missile)obj).getIdentifier()==1){
					tracker2.remove();
					System.out.println("PLAYERSHIP MISSILE HAS DESTROYED ASTEROID\n");
					break;
				}
			}
			score=score+100;
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			System.out.println("Score : +"+100+"\n");
		}else {
			System.out.println("NO MISSILES OR ASTEROIDS\n");
		}
	}

	public void playerMissileEliminateNPS() {
		// TODO Auto-generated method stub
		boolean npsPresent=false;
		boolean psMissilePresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1) {
				psMissilePresent=true;
			}
		}
		if(npsPresent==true && psMissilePresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof NonPlayerShip) {
					tracker1.remove();
					//System.out.println("NonPlayerShip HAS BEEN DESTROYED");
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1){
					tracker2.remove();
					System.out.println("PLAYERSHIP MISSILE HAS DESTROYED NONPLAYERSHIP\n");
					break;
				}
			}
			score=score+500;
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			System.out.println("Score : +"+500+"\n");
		}else {
			System.out.println("NO MISSILES OR NON-PLAYERSHIPS\n");
		}
	}

	public void npsStruckPS() {
		// TODO Auto-generated method stub
		boolean psPresent=false;
		boolean npsMissilePresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==0) {
				npsMissilePresent=true;
			}
		}
		if(psPresent==true && npsMissilePresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof PlayerShip) {
					tracker1.remove();
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof Missile && ((Missile) obj).getIdentifier()==0){
					tracker2.remove();
					System.out.println("NON-PLAYERSHIP MISSILE HAS DESTROYED A PLAYERSHIP\n");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO MISSILES OR PLAYERSHIP\n");
		}
	}

	public void psCrashAsteroid() {
		// TODO Auto-generated method stub
		boolean psPresent=false;
		boolean asteroidPresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
		}
		if(psPresent==true && asteroidPresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof PlayerShip) {
					tracker1.remove();
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof Asteroid){
					tracker2.remove();
					System.out.println("ASTEROID HAS COLLIDED A PLAYERSHIP\n");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO ASTEROID OR PLAYERSHIP\n");
		}
	}

	public void psHitNPS() {
		// TODO Auto-generated method stub
		boolean psPresent=false;
		boolean npsPresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
		}
		if(psPresent==true && npsPresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof PlayerShip) {
					tracker1.remove();
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof NonPlayerShip){
					tracker2.remove();
					System.out.println("NON-PLAYERSHIP AND PLAYERSHIP HAS BEEN DESTROYED\n");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO NON-PLAYERSHIP OR PLAYERSHIP\n");
		}
	}

	public void asteroidsCollided() {
		// TODO Auto-generated method stub
		boolean asteroidPresent1=false;
		boolean asteroidPresent2=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof Asteroid && asteroidPresent1) {
				asteroidPresent2=true;
			}
			if(obj instanceof Asteroid && !asteroidPresent1) {
				asteroidPresent1=true;
			}
		}
		if(asteroidPresent1==true && asteroidPresent2==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof Asteroid && asteroidPresent1) {
					tracker1.remove();
					//System.out.println("ASTEROID HAS BEEN DESTROYED");
					asteroidPresent1=false;
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof Asteroid && !asteroidPresent1){
					tracker2.remove();
					System.out.println("ASTEROID HAS COLLIDED\n");
					asteroidPresent2=false;
					break;
				}
			}
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			//System.out.println("Score :"+score);
		}else {
			System.out.println("DOES NOT EXIST 2 OR MORE ASTEROIDS\n");
		}
	}

	public void asteroidColliededNPS() {
		// TODO Auto-generated method stub
		boolean asteroidPresent=false;
		boolean npsPresent=false;
		Iterator<Object> theElements = go.getIterator();
		while(theElements.hasNext()){
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
		}
		if(asteroidPresent==true && npsPresent==true) {
			Iterator<Object> tracker1 = go.getIterator();
			while(tracker1.hasNext()) {
				GameObject obj = (GameObject) tracker1.next();
				if(obj instanceof Asteroid) {
					tracker1.remove();
					//System.out.println("asteroid HAS BEEN DESTROYED");
					break;
				}
			}
			Iterator<Object> tracker2 = go.getIterator();
			while(tracker2.hasNext()) {
				GameObject obj = (GameObject) tracker2.next();
				if(obj instanceof NonPlayerShip){
					tracker2.remove();
					System.out.println("ASTEROID HAS COLLIDED A NON-PLAYERSHIP\n");
					break;
				}
			}
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO NON-PLAYERSHIP OR ASTEROID\n");
		}
	}

	public void tick() {
		// TODO Auto-generated method stub
		Iterator<Object> theElements= go.getIterator();
		while(theElements.hasNext()) {
			GameObject obj = (GameObject) theElements.next();
			if(obj instanceof IMoveable) {
				((IMoveable) obj).move();
				if(obj instanceof Missile) {
					((Missile) obj).decFuel();
					if(((Missile) obj).getFuel()==0) {
						theElements.remove();
						break;
					}
				}
			}else {
				if(((SpaceStation) obj).getBlink()==4) {
					((SpaceStation) obj).setBlink(0);
				}else {
					((SpaceStation) obj).incBlink();
				}
			}
		}
		gameclock++;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		System.out.println("CURRENT ELAPSED TIME: "+gameclock+"\n");
	}
//	public void map() {
//		Iterator theElements = go.getIterator();
//		System.out.println("MAP:");
//		while(theElements.hasNext()) {
//			GameObject obj = (GameObject) theElements.next();
//			System.out.println(obj.toString());
//		}
//		System.out.println();
//	}





}
