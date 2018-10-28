package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.Commands.AboutCommand;
import com.mycompany.a2.Commands.AddAsteroidCommand;
import com.mycompany.a2.Commands.AddNPSCommand;
import com.mycompany.a2.Commands.AddPSCommand;
import com.mycompany.a2.Commands.AddStationCommand;
import com.mycompany.a2.Commands.AsteroidCollideCommand;
import com.mycompany.a2.Commands.AsteroidImpactNPSCommand;
import com.mycompany.a2.Commands.DecreaseSpeedCommand;
import com.mycompany.a2.Commands.FireMissileCommand;
import com.mycompany.a2.Commands.IncreaseSpeedCommand;
import com.mycompany.a2.Commands.JumpCommand;
import com.mycompany.a2.Commands.LaunchMissileCommand;
import com.mycompany.a2.Commands.MissileEliminatesNPSCommand;
import com.mycompany.a2.Commands.MissileExplodesPSCommand;
import com.mycompany.a2.Commands.MissileKillsAsteroidCommand;
import com.mycompany.a2.Commands.PSCrashAsteroidCommand;
import com.mycompany.a2.Commands.PSHitNPSCommand;
import com.mycompany.a2.Commands.QuitCommand;
import com.mycompany.a2.Commands.ReloadCommand;
import com.mycompany.a2.Commands.RotateMissileLauncherLeftCommand;
import com.mycompany.a2.Commands.RotateMissileLauncherRightCommand;
import com.mycompany.a2.Commands.SoundCommand;
import com.mycompany.a2.Commands.TickCommand;
import com.mycompany.a2.Commands.TurnLeftCommand;
import com.mycompany.a2.Commands.TurnRightCommand;


public class Game extends Form {

	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
//	private Label checkStatusVal = new Label("OFF");
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		Toolbar myToolbar = new Toolbar();
		this.setToolbar(myToolbar);
		Button newGame = new Button("New");
		newGame.getAllStyles().setAlignment(LEFT);
		Button saveGame = new Button("Save");
		saveGame.getAllStyles().setAlignment(LEFT);
		Button undo = new Button("Undo");
		undo.getAllStyles().setAlignment(LEFT);
		CheckBox sound = new CheckBox("Sound");
		sound.getAllStyles().setBgTransparency(255);
		//sound.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		Command soundCommand = new SoundCommand(gw);
		sound.setCommand(soundCommand);
		Button about = new Button("About");
		about.getAllStyles().setAlignment(LEFT);
		Command aboutCommand = new AboutCommand(gw);
		about.setCommand(aboutCommand);
		Button quit = new Button("Quit");
		quit.getAllStyles().setAlignment(LEFT);
		Command quitCommand = new QuitCommand(gw);
		quit.setCommand(quitCommand);
		myToolbar.addComponentToSideMenu(newGame);
		myToolbar.addComponentToSideMenu(saveGame);
		myToolbar.addComponentToSideMenu(undo);
		myToolbar.addComponentToSideMenu(sound);
		myToolbar.addComponentToSideMenu(about);
		myToolbar.addComponentToSideMenu(quit);
//		checkStatusVal.getAllStyles().setPadding(LEFT, 5);	
//		checkStatusVal.getAllStyles().setPadding(RIGHT,5);	
//		this.add(checkStatusVal);
		myToolbar.setTitle("Asteroid Game");
		
		setLayout(new BorderLayout());
		Container leftContainer = new Container();
//		leftContainer.getAllStyles().setPadding(RIGHT, 1200);
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setFgColor(ColorUtil.rgb(16, 132, 209));
		Label commands = new Label("Commands :");
		leftContainer.add(commands);
		
		//Add Asteroid Command
		Button addAsteroid = new Button("Add Asteroid");
		addAsteroid.getAllStyles().setBgTransparency(255);
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addAsteroid.getAllStyles().setPadding(TOP, 1);
		addAsteroid.getAllStyles().setPadding(BOTTOM, 1);
		addAsteroid.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addAsteroid);
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		addAsteroid.setCommand(myAddAsteroid);
		addKeyListener('a', myAddAsteroid);
		
		//Add NPS
		Button addNPS = new Button("Add Enemy Ship");
		addNPS.getAllStyles().setBgTransparency(255);
		addNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addNPS.getAllStyles().setPadding(TOP, 1);
		addNPS.getAllStyles().setPadding(BOTTOM, 1);
		addNPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addNPS);
		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
		addNPS.setCommand(myAddNPS);
		addKeyListener('y', myAddNPS);
		
		//Add Blink Station
		Button addStation = new Button("Add Space Station");
		addStation.getAllStyles().setBgTransparency(255);
		addStation.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addStation.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addStation.getAllStyles().setPadding(TOP, 1);
		addStation.getAllStyles().setPadding(BOTTOM, 1);
		addStation.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addStation);
		AddStationCommand myAddStation = new AddStationCommand(gw);
		addStation.setCommand(myAddStation);
		addKeyListener('b', myAddStation);
		
		//Add PlayerShip
		Button addPS = new Button("Add Player Ship");
		addPS.getAllStyles().setBgTransparency(255);
		addPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addPS.getAllStyles().setPadding(TOP, 1);
		addPS.getAllStyles().setPadding(BOTTOM, 1);
		addPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addPS);
		AddPSCommand myAddPS = new AddPSCommand(gw);
		addPS.setCommand(myAddPS);
		addKeyListener('s', myAddPS);
		
		//Increase Playership speed
		Button increaseSpeed = new Button("Increase Speed");
		increaseSpeed.getAllStyles().setBgTransparency(255);
		increaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		increaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		increaseSpeed.getAllStyles().setPadding(TOP, 1);
		increaseSpeed.getAllStyles().setPadding(BOTTOM, 1);
		increaseSpeed.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(increaseSpeed);
		IncreaseSpeedCommand myIncreaseSpeed = new IncreaseSpeedCommand(gw);
		increaseSpeed.setCommand(myIncreaseSpeed);
		addKeyListener(-91, myIncreaseSpeed);
		
		//Decrease Playership speed
		Button decreaseSpeed = new Button("Decrease Speed");
		decreaseSpeed.getAllStyles().setBgTransparency(255);
		decreaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		decreaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		decreaseSpeed.getAllStyles().setPadding(TOP, 1);
		decreaseSpeed.getAllStyles().setPadding(BOTTOM, 1);
		decreaseSpeed.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(decreaseSpeed);
		DecreaseSpeedCommand myDecreaseSpeed = new DecreaseSpeedCommand(gw);
		decreaseSpeed.setCommand(myDecreaseSpeed);
		addKeyListener(-92, myDecreaseSpeed);
		
		//turn left playership
		Button turnLeft = new Button("Turn Left");
		turnLeft.getAllStyles().setBgTransparency(255);
		turnLeft.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		turnLeft.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turnLeft.getAllStyles().setPadding(TOP, 1);
		turnLeft.getAllStyles().setPadding(BOTTOM, 1);
		turnLeft.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(turnLeft);
		TurnLeftCommand myTurnLeft = new TurnLeftCommand(gw);
		turnLeft.setCommand(myTurnLeft);
		addKeyListener(-93, myTurnLeft);
		
		//turn right playership
		Button turnRight = new Button("Turn Right");
		turnRight.getAllStyles().setBgTransparency(255);
		turnRight.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		turnRight.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turnRight.getAllStyles().setPadding(TOP, 1);
		turnRight.getAllStyles().setPadding(BOTTOM, 1);
		turnRight.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(turnRight);
		TurnRightCommand myTurnRight = new TurnRightCommand(gw);
		turnRight.setCommand(myTurnRight);
		addKeyListener(-94, myTurnRight);
		
		//rotate missile launcher left
		Button rotateMissileLauncherLeft = new Button("Rotate Missile Launcher Left");
		rotateMissileLauncherLeft.getAllStyles().setBgTransparency(255);
		rotateMissileLauncherLeft.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		rotateMissileLauncherLeft.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		rotateMissileLauncherLeft.getAllStyles().setPadding(TOP, 1);
		rotateMissileLauncherLeft.getAllStyles().setPadding(BOTTOM, 1);
		rotateMissileLauncherLeft.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(rotateMissileLauncherLeft);
		RotateMissileLauncherLeftCommand myRotateMissileLauncherLeft = new RotateMissileLauncherLeftCommand(gw);
		rotateMissileLauncherLeft.setCommand(myRotateMissileLauncherLeft);
		addKeyListener(44 , myRotateMissileLauncherLeft);
		
		//rotate missile launcher right
		Button rotateMissileLauncherRight = new Button("Rotate Missile Launcher Right");
		rotateMissileLauncherRight.getAllStyles().setBgTransparency(255);
		rotateMissileLauncherRight.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		rotateMissileLauncherRight.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		rotateMissileLauncherRight.getAllStyles().setPadding(TOP, 1);
		rotateMissileLauncherRight.getAllStyles().setPadding(BOTTOM, 1);
		rotateMissileLauncherRight.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(rotateMissileLauncherRight);
		RotateMissileLauncherRightCommand myRotateMissileLauncherRight = new RotateMissileLauncherRightCommand(gw);
		rotateMissileLauncherRight.setCommand(myRotateMissileLauncherRight);
		addKeyListener(46 , myRotateMissileLauncherRight);
		
		//fire missile ps
		Button fireMissile = new Button("Fire Missile");
		fireMissile.getAllStyles().setBgTransparency(255);
		fireMissile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		fireMissile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		fireMissile.getAllStyles().setPadding(TOP, 1);
		fireMissile.getAllStyles().setPadding(BOTTOM, 1);
		fireMissile.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(fireMissile);
		FireMissileCommand myFireMissile = new FireMissileCommand(gw);
		fireMissile.setCommand(myFireMissile);
		addKeyListener(-90, myFireMissile);
		
		//launch missile nps
		Button launchMissile = new Button("NPS launch Missile");
		launchMissile.getAllStyles().setBgTransparency(255);
		launchMissile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		launchMissile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		launchMissile.getAllStyles().setPadding(TOP, 1);
		launchMissile.getAllStyles().setPadding(BOTTOM, 1);
		launchMissile.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(launchMissile);
		LaunchMissileCommand myLaunchMissile = new LaunchMissileCommand(gw);
		launchMissile.setCommand(myLaunchMissile);
		addKeyListener('L', myLaunchMissile);
		
		//jump through hyperspace
		Button jump = new Button("Jump Through Hyperspace");
		jump.getAllStyles().setBgTransparency(255);
		jump.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		jump.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		jump.getAllStyles().setPadding(TOP, 1);
		jump.getAllStyles().setPadding(BOTTOM, 1);
		jump.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(jump);
		JumpCommand myJump = new JumpCommand(gw);
		jump.setCommand(myJump);
		addKeyListener('j', myJump);
		
		//reload missiles
		Button reload = new Button("Reload Missiles");
		reload.getAllStyles().setBgTransparency(255);
		reload.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		reload.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		reload.getAllStyles().setPadding(TOP, 1);
		reload.getAllStyles().setPadding(BOTTOM, 1);
		reload.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(reload);
		ReloadCommand myReload = new ReloadCommand(gw);
		reload.setCommand(myReload);
		addKeyListener('n', myReload);
		
		//Missile kills Asteroid
		Button missileKillsAsteroid = new Button("Missile Kills Asteroid");
		missileKillsAsteroid.getAllStyles().setBgTransparency(255);
		missileKillsAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		missileKillsAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		missileKillsAsteroid.getAllStyles().setPadding(TOP, 1);
		missileKillsAsteroid.getAllStyles().setPadding(BOTTOM, 1);
		missileKillsAsteroid.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(missileKillsAsteroid);
		MissileKillsAsteroidCommand myMissileKillsAsteroid = new MissileKillsAsteroidCommand(gw);
		missileKillsAsteroid.setCommand(myMissileKillsAsteroid);
		addKeyListener('k', myMissileKillsAsteroid);
		
		//Missile eliminates NPS
		Button missileEliminatesNPS = new Button("Missile Eliminates Enemy Ship");
		missileEliminatesNPS.getAllStyles().setBgTransparency(255);
		missileEliminatesNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		missileEliminatesNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		missileEliminatesNPS.getAllStyles().setPadding(TOP, 1);
		missileEliminatesNPS.getAllStyles().setPadding(BOTTOM, 1);
		missileEliminatesNPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(missileEliminatesNPS);
		MissileEliminatesNPSCommand myMissileEliminatesNPS = new MissileEliminatesNPSCommand(gw);
		missileEliminatesNPS.setCommand(myMissileEliminatesNPS);
		addKeyListener('e', myMissileEliminatesNPS);
		
		//MPS missile explodeds ps
		Button missileExplodesPS = new Button("Enemy Missile Explodes Player Ship");
		missileExplodesPS.getAllStyles().setBgTransparency(255);
		missileExplodesPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		missileExplodesPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		missileExplodesPS.getAllStyles().setPadding(TOP, 1);
		missileExplodesPS.getAllStyles().setPadding(BOTTOM, 1);
		missileExplodesPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(missileExplodesPS);
		MissileExplodesPSCommand myMissileExplodesPS = new MissileExplodesPSCommand(gw);
		missileExplodesPS.setCommand(myMissileExplodesPS);
		addKeyListener('E', myMissileExplodesPS);
		
		//PS crashed into an asteroid
		Button psCrashAsteroid = new Button("Player Ship Crashed into an Asteroid");
		psCrashAsteroid.getAllStyles().setBgTransparency(255);
		psCrashAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		psCrashAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		psCrashAsteroid.getAllStyles().setPadding(TOP, 1);
		psCrashAsteroid.getAllStyles().setPadding(BOTTOM, 1);
		psCrashAsteroid.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(psCrashAsteroid);
		PSCrashAsteroidCommand myPSCrashAsteroid = new PSCrashAsteroidCommand(gw);
		psCrashAsteroid.setCommand(myPSCrashAsteroid);
		addKeyListener('c', myPSCrashAsteroid);
		
		//PS hit NPS
		Button psHitNPS = new Button("Player Ship has Hit a Enemy Ship");
		psHitNPS.getAllStyles().setBgTransparency(255);
		psHitNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		psHitNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		psHitNPS.getAllStyles().setPadding(TOP, 1);
		psHitNPS.getAllStyles().setPadding(BOTTOM, 1);
		psHitNPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(psHitNPS);
		PSHitNPSCommand myPSHitNPS = new PSHitNPSCommand(gw);
		psHitNPS.setCommand(myPSHitNPS);
		addKeyListener('h', myPSHitNPS);
		
		//two asteroids collide
		Button asteroidCollide = new Button("Two Asteroids have Collided");
		asteroidCollide.getAllStyles().setBgTransparency(255);
		asteroidCollide.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		asteroidCollide.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		asteroidCollide.getAllStyles().setPadding(TOP, 1);
		asteroidCollide.getAllStyles().setPadding(BOTTOM, 1);
		asteroidCollide.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(asteroidCollide);
		AsteroidCollideCommand myAsteroidCollide = new AsteroidCollideCommand(gw);
		asteroidCollide.setCommand(myAsteroidCollide);
		addKeyListener('x', myAsteroidCollide);
		
		//asteroid has impacted a nps
		Button asteroidImpactNPS = new Button("Asteroid has Impacted an Enemy Ship");
		asteroidImpactNPS.getAllStyles().setBgTransparency(255);
		asteroidImpactNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		asteroidImpactNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		asteroidImpactNPS.getAllStyles().setPadding(TOP, 1);
		asteroidImpactNPS.getAllStyles().setPadding(BOTTOM, 1);
		asteroidImpactNPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(asteroidImpactNPS);
		AsteroidImpactNPSCommand myAsteroidImpactNPS = new AsteroidImpactNPSCommand(gw);
		asteroidImpactNPS.setCommand(myAsteroidImpactNPS);
		addKeyListener('I', myAsteroidImpactNPS);
		
		//tick
		Button tick = new Button("Game Clock Tick");
		tick.getAllStyles().setBgTransparency(255);
		tick.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		tick.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		tick.getAllStyles().setPadding(TOP, 1);
		tick.getAllStyles().setPadding(BOTTOM, 1);
		tick.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(tick);
		TickCommand myTick = new TickCommand(gw);
		tick.setCommand(myTick);
		addKeyListener('t', myTick);
		
		add(BorderLayout.NORTH, pv);
		add(BorderLayout.CENTER,mv);
		add(BorderLayout.WEST,leftContainer);
		this.show();
	}
//    public void setCheckStatusVal(boolean bVal){
//	   if (bVal)
//		    checkStatusVal.setText("ON");
//	   else
//		   checkStatusVal.setText("OFF");
//    }
	public static int getMapHeight() {
		// TODO Auto-generated method stub
		return 748;
	}
	public static int getMapWidth() {
		return 1024;
	}
}
