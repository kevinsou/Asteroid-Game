package com.mycompany.a1;

import java.util.Vector;

public class GameWorld {
	public Vector<GameObject> store = new Vector<GameObject>();
	private int id=0;
	private int score=0;
	private int lives=3;
	private int counter=0;
	private int gameclock=0;
	
//--------------------------------------ASTEROID-------------------------------------------------	
	public void addNewAsteroid() { //add a new asteroid
		Asteroid asteroid = new Asteroid(); //create an asteroid object
		store.add(asteroid); //add asteroid to storage vector
		System.out.println("A new ASTEROID has been created.\n"); //tell the user that you created an asteroid
	}
	
//-----------------------------------------NONPLAYERSHIP-------------------------------------------	
	public void addNewNPS() { //add a new NPS
		NonPlayerShip nonPlayerShip = new NonPlayerShip(); //create a nps object
		store.add(nonPlayerShip); //add nps to storage vector
		System.out.println("A new NON-PLAYERSHIP has been created\n");
	}
//______________________________________LAUNCH_______________________________________________
	public void launchMissile() {
		for(Object obj: store) {
			if(obj instanceof NonPlayerShip) {
				if(((Ships) obj).getMissileCount()>=0) {
					Missile npcMissile = new Missile(10,((NonPlayerShip) obj).getDirection(),((NonPlayerShip) obj).getX(),( (NonPlayerShip) obj).getY(),0);
					store.add(npcMissile);
					System.out.println("A new MISSILE has been created");
					((Ships) obj).launchMissile();
					System.out.println("NONPLAYERSHIP HAS FIRED A MISSILE!\n");
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
//----------------------------------------SPACESTATION--------------------------------------------	
	public void addSpaceStation() { //add new spacestation
		SpaceStation spaceStation = new SpaceStation(id); //create a spacestation
		id++;
		store.add(spaceStation); //add spacestation to storage vector
		System.out.println("A new SPACESTATION has been created\n");
	}
//-----------------------------------------PLAYERSHIP-------------------------------------------
	public void addPlayerShip() { //add a new playership 
		for(Object obj:store) { // test if there exist a player ship
			if(obj instanceof PlayerShip) {
				System.out.println("ERROR ALREADY EXIST A PLAYERSHIP\n");
				return;
			}
		}
		PlayerShip playerShip = new PlayerShip(); //creates a playership
		store.add(playerShip); //adds playership to store
		System.out.println("A new PLAYERSHIP has been created\n");
	}
//_____________________________________Speed_________________________________
	public void incPlayerShipSpeed() { //increase the ps speed
		for(Object obj: store) { //checks for playership
			if(obj instanceof PlayerShip) {
				if(((Ships) obj).getSpeed()==10) {
					System.out.println("MAX SPEED\n");
					return;
				}
				((PlayerShip) obj).incSpeed();
				((PlayerShip) obj).speedMissileLauncher(((PlayerShip) obj).getSpeed());
				System.out.println("PLAYERSHIP: speed = "+ ((PlayerShip) obj).getSpeed()+"\n");
				return;
			}

		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
	public void decPlayerShipSpeed() { // decrease playership
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				if(((PlayerShip) obj).getSpeed()==0) {
					System.out.println("ERROR PLAYERSHIP SPEED CANNOT BE LESS THAN 0\n");
					return;
				}
				((PlayerShip) obj).decSpeed();
				((PlayerShip) obj).speedMissileLauncher(((PlayerShip) obj).getSpeed());
				System.out.println("PLAYERSHIP: speed = "+ ((PlayerShip) obj).getSpeed()+"\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
//_____________________________________direction_________________________________
	public void steerLeftPS() {
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).steerLeft();
				System.out.println("PLAYERSHIP: direction = "+ ((MoveableGameObject) obj).getDirection()+"\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
	public void steerRightPS() {
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).steerRight();
				System.out.println("PLAYERSHIP: direction = "+ ((MoveableGameObject) obj).getDirection()+"\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
//________________________________________MISSILE LAUNCHER____________________________
	public void turnMissileLauncherPS() {
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).turnMissileLauncher();
				System.out.println("PLAYERSHIP MISSILELAUNCHER: direction = "+ (((PlayerShip) obj).getMissileLauncherDir())+"\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
//________________________________________FIREMISSILE____________________________________
	public void fireMissilePS() {
		boolean check=true;
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				if(((PlayerShip) obj).getMissileCount()>=0) {
					Missile psMissile = new Missile(10,((PlayerShip) obj).getMissileLauncherDir(),((PlayerShip) obj).getX(),((PlayerShip) obj).getY(),1);
					store.add(psMissile);
					System.out.println("A new MISSILE has been created\n");
					((PlayerShip) obj).launchMissile();
					System.out.println("PLAYERSHIP HAS FIRED A MISSILE!\n");
					return;
				}
				else {
					System.out.println("ERROR NO MORE MISSILES\n");
				}
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
//___________________________________Reload missiles_____________________________
	public void reload() {
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).setMissileCount(10);
				System.out.println("MISSILES HAS BEEN RELOADED\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}
//_______________________________________JUMP____________________________________
	public void jumpCenter() {
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				((PlayerShip) obj).setX(512.0);
				((PlayerShip) obj).setY(384.0);
				System.out.println("PLAYERSHIP HAS JUMP THROUGH HYPERSPACE\n");
				return;
			}
		}
		System.out.println("ERROR NO PLAYERSHIP\n");
	}

//------------------------------------------KILL-------------------------------------
	public void killAsteroid() {
		boolean asteroidPresent=false;
		boolean psMissilePresent=false;
		for(Object obj: store) {
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1) {
				psMissilePresent=true;
			}
		}
		if(asteroidPresent==true && psMissilePresent==true) {
			for(Object obj: store) {
				if(obj instanceof Asteroid) {
					store.remove(store.indexOf(obj));
					//System.out.println("ASTEROID HAS BEEN DESTROYED");
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1){
					store.remove(store.indexOf(obj));
					System.out.println("PLAYERSHIP MISSILE HAS DESTROYED ASTEROID");
					break;
				}
			}
			score=score+100;
			System.out.println("Score : +"+100+"\n");
		}else {
			System.out.println("NO MISSILES OR ASTEROIDS\n");
		}
	}
//_________________________________________ELIMINATE____________________________________
	public void eliminateNPS() {
		boolean npsPresent=false;
		boolean psMissilePresent=false;
		for(Object obj: store) {
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1) {
				psMissilePresent=true;
			}
		}
		if(npsPresent==true && psMissilePresent==true) {
			for(Object obj: store) {
				if(obj instanceof NonPlayerShip) {
					store.remove(store.indexOf(obj));
					//System.out.println("NonPlayerShip HAS BEEN DESTROYED");
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof Missile && ((Missile) obj).getIdentifier()==1){
					store.remove(store.indexOf(obj));
					System.out.println("PLAYERSHIP MISSILE HAS DESTROYED NONPLAYERSHIP");
					break;
				}
			}
			score=score+500;
			System.out.println("Score : +"+500+"\n");
		}else {
			System.out.println("NO MISSILES OR NON-PLAYERSHIPS\n");
		}
	}
//______________________________________EXPLODED___________________________________________
	public void explodePS() {
		boolean psPresent=false;
		boolean npsMissilePresent=false;
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof Missile && ((Missile) obj).getIdentifier()==0) {
				npsMissilePresent=true;
			}
		}
		if(psPresent==true && npsMissilePresent==true) {
			for(Object obj: store) {
				if(obj instanceof PlayerShip) {
					store.remove(store.indexOf(obj));
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof Missile && ((Missile) obj).getIdentifier()==0){
					store.remove(store.indexOf(obj));
					System.out.println("NON-PLAYERSHIP MISSILE HAS DESTROYED A PLAYERSHIP");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO MISSILES OR PLAYERSHIP\n");
		}
	}
//______________________________________CRASH_______________________________
	public void crash() {
		boolean psPresent=false;
		boolean asteroidPresent=false;
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
		}
		if(psPresent==true && asteroidPresent==true) {
			for(Object obj: store) {
				if(obj instanceof PlayerShip) {
					store.remove(store.indexOf(obj));
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof Asteroid){
					store.remove(store.indexOf(obj));
					System.out.println("ASTEROID HAS COLLIDED A PLAYERSHIP");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO ASTEROID OR PLAYERSHIP\n");
		}
	}
//_____________________________________HIT_______________________________
	public void hitNPC() {
		boolean psPresent=false;
		boolean npsPresent=false;
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				psPresent=true;
			}
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
		}
		if(psPresent==true && npsPresent==true) {
			for(Object obj: store) {
				if(obj instanceof PlayerShip) {
					store.remove(store.indexOf(obj));
					//System.out.println("PlayerShip HAS BEEN DESTROYED");
					lives--;
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof NonPlayerShip){
					store.remove(store.indexOf(obj));
					System.out.println("NON-PLAYERSHIP AND PLAYERSHIP HAS BEEN DESTROYED");
					break;
				}
			}
			System.out.println("Lives: -1\n");
			if(lives==0) {
				System.out.println("GAME OVER\n");
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO NON-PLAYERSHIP OR PLAYERSHIP\n");
		}
	}
//__________________________________EXTERMINATE_____________________________________
	public void exterminate() {
		boolean asteroidPresent1=false;
		boolean asteroidPresent2=false;
		for(Object obj: store) {
			if(obj instanceof Asteroid && asteroidPresent1) {
				asteroidPresent2=true;
			}
			if(obj instanceof Asteroid && !asteroidPresent1) {
				asteroidPresent1=true;
			}
		}
		if(asteroidPresent1==true && asteroidPresent2==true) {
			for(Object obj: store) {
				if(obj instanceof Asteroid && asteroidPresent1) {
					store.remove(store.indexOf(obj));
					//System.out.println("ASTEROID HAS BEEN DESTROYED");
					asteroidPresent1=false;
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof Asteroid && !asteroidPresent1){
					store.remove(store.indexOf(obj));
					System.out.println("ASTEROID HAS COLLIDED\n");
					asteroidPresent2=false;
					break;
				}
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("DOES NOT EXIST 2 OR MORE ASTEROIDS\n");
		}
	}
//__________________________________IMPACTED__________________________________________
	public void impact() {
		boolean asteroidPresent=false;
		boolean npsPresent=false;
		for(Object obj: store) {
			if(obj instanceof Asteroid) {
				asteroidPresent=true;
			}
			if(obj instanceof NonPlayerShip) {
				npsPresent=true;
			}
		}
		if(asteroidPresent==true && npsPresent==true) {
			for(Object obj: store) {
				if(obj instanceof Asteroid) {
					store.remove(store.indexOf(obj));
					//System.out.println("asteroid HAS BEEN DESTROYED");
					break;
				}
			}
			for(Object obj: store) {
				if(obj instanceof NonPlayerShip){
					store.remove(store.indexOf(obj));
					System.out.println("ASTEROID HAS COLLIDED A NON-PLAYERSHIP\n");
					break;
				}
			}
			//System.out.println("Score :"+score);
		}else {
			System.out.println("NO NON-PLAYERSHIP OR ASTEROID\n");
		}
	}
//-------------------------------------------GAMECLOCK---------------------------------------
	public void tick() {
		for(Object obj: store) {
			if(obj instanceof IMoveable) {
				((IMoveable) obj).move();
//				((GameObject) obj).setX(((GameObject) obj).getX()+(Math.cos(Math.toRadians(90-((MoveableGameObject) obj).getDirection()))*((MoveableGameObject) obj).getSpeed()));
//				((GameObject) obj).setY(((GameObject) obj).getY()+(Math.cos(Math.toRadians(90-((MoveableGameObject) obj).getDirection()))*((MoveableGameObject) obj).getSpeed()));
//				((MoveableGameObject) obj).setX(Math.round(((MoveableGameObject) obj).getX()*10)/10);
//				((MoveableGameObject) obj).setY(Math.round(((MoveableGameObject) obj).getY()*10)/10);
				if(obj instanceof Missile) {
					((Missile) obj).decFuel();
					if(((Missile) obj).getFuel()==0) {
						store.remove(store.indexOf(obj));
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
		System.out.println("CURRENT ELAPSED TIME: "+gameclock+"\n");
	}
//-------------------------------------------PRINT------------------------------------
	public void print() {
		System.out.println("SCORE :"+score);
		System.out.println("LIVES :"+ lives);
		for(Object obj: store) {
			if(obj instanceof PlayerShip) {
				System.out.println("MISSILES :"+((Ships) obj).getMissileCount());
			}
		}
		System.out.println("CURRENT ELASPED TIME: "+gameclock+"\n");
	}
	
//----------------------------------------------QUIT------------------------------------------
	public void quit() {
		System.exit(0);
	}
//---------------------------------------------MAP----------------------------------------------
	public void map() {
		System.out.println("MAP:");
		for(Object obj: store) {
			System.out.println(obj);
		}
		System.out.println();
	}
//-----------------------------------------QUIT-------------------------------------------------
	
	public void init() {
		
	}
}
