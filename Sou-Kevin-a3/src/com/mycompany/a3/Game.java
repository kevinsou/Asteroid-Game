package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Commands.AboutCommand;
import com.mycompany.a3.Commands.AddAsteroidCommand;
import com.mycompany.a3.Commands.AddNPSCommand;
import com.mycompany.a3.Commands.AddPSCommand;
import com.mycompany.a3.Commands.AddStationCommand;
import com.mycompany.a3.Commands.AsteroidCollideCommand;
import com.mycompany.a3.Commands.AsteroidImpactNPSCommand;
import com.mycompany.a3.Commands.DecreaseSpeedCommand;
import com.mycompany.a3.Commands.FireMissileCommand;
import com.mycompany.a3.Commands.IncreaseSpeedCommand;
import com.mycompany.a3.Commands.JumpCommand;
import com.mycompany.a3.Commands.LaunchMissileCommand;
import com.mycompany.a3.Commands.MissileEliminatesNPSCommand;
import com.mycompany.a3.Commands.MissileExplodesPSCommand;
import com.mycompany.a3.Commands.MissileKillsAsteroidCommand;
import com.mycompany.a3.Commands.PSCrashAsteroidCommand;
import com.mycompany.a3.Commands.PSHitNPSCommand;
import com.mycompany.a3.Commands.PauseCommand;
import com.mycompany.a3.Commands.QuitCommand;
import com.mycompany.a3.Commands.RefuelCommand;
import com.mycompany.a3.Commands.ReloadCommand;
import com.mycompany.a3.Commands.RotateMissileLauncherLeftCommand;
import com.mycompany.a3.Commands.RotateMissileLauncherRightCommand;
import com.mycompany.a3.Commands.SoundCommand;
import com.mycompany.a3.Commands.TickCommand;
import com.mycompany.a3.Commands.TurnLeftCommand;
import com.mycompany.a3.Commands.TurnRightCommand;
import com.mycompany.a3.PointerPressListener;
import com.mycompany.a3.BGSound;


public class Game extends Form implements Runnable{

	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private UITimer timer;
	private boolean repeatTime=true;
	private Button pause;
	private Button addAsteroid;
	private Button addStation;
	private Button addPS;
	private Button increaseSpeed;
	private Button decreaseSpeed;
	private Button turnLeft;
	private Button turnRight;
	private Button fireMissile;
	private Button jump;
	private Button refuel;
	private Button rotateMissileLauncherLeft;
	private Button rotateMissileLauncherRight;
	private Button exit;
	private AddAsteroidCommand myAddAsteroid;
	private AddStationCommand myAddStation;
	private AddPSCommand myAddPS;
	private IncreaseSpeedCommand myIncreaseSpeed;
	private DecreaseSpeedCommand myDecreaseSpeed;
	private TurnLeftCommand myTurnLeft;
	private TurnRightCommand myTurnRight;
	private FireMissileCommand myFireMissile;
	private JumpCommand myJump;
	private RotateMissileLauncherLeftCommand myRotateMissileLauncherLeft;
	private RotateMissileLauncherRightCommand myRotateMissileLauncherRight;
	private boolean isPause;
//	private Label checkStatusVal = new Label("OFF");
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw);
		pv = new PointsView();
		gw.addObserver(mv);
		gw.addObserver(pv);
		UITimer timer = new UITimer(this);

		
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
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setFgColor(ColorUtil.rgb(16, 132, 209));
		Label commands = new Label("Commands :");
		leftContainer.add(commands);
		
		//Add Asteroid Command
		addAsteroid = new Button("Add Asteroid");
		addAsteroid.getAllStyles().setBgTransparency(255);
		addAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addAsteroid.getAllStyles().setPadding(TOP, 2);
		addAsteroid.getAllStyles().setPadding(BOTTOM, 2);
		addAsteroid.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addAsteroid);
		myAddAsteroid = new AddAsteroidCommand(gw,isPause);
		addAsteroid.setCommand(myAddAsteroid);
		addKeyListener('a', myAddAsteroid);
		
		//Add NPS
//		Button addNPS = new Button("Add Enemy Ship");
//		addNPS.getAllStyles().setBgTransparency(255);
//		addNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
//		addNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
//		addNPS.getAllStyles().setPadding(TOP, 1);
//		addNPS.getAllStyles().setPadding(BOTTOM, 1);
//		addNPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
//		leftContainer.add(addNPS);
//		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
//		addNPS.setCommand(myAddNPS);
//		addKeyListener('y', myAddNPS);
		
		//Add Blink Station
		addStation = new Button("Add Space Station");
		addStation.getAllStyles().setBgTransparency(255);
		addStation.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addStation.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addStation.getAllStyles().setPadding(TOP, 2);
		addStation.getAllStyles().setPadding(BOTTOM, 2);
		addStation.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addStation);
		myAddStation = new AddStationCommand(gw);
		addStation.setCommand(myAddStation);
		addKeyListener('b', myAddStation);
		
		//Add PlayerShip
		addPS = new Button("Add Player Ship");
		addPS.getAllStyles().setBgTransparency(255);
		addPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		addPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		addPS.getAllStyles().setPadding(TOP, 2);
		addPS.getAllStyles().setPadding(BOTTOM, 2);
		addPS.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(addPS);
		myAddPS = new AddPSCommand(gw);
		addPS.setCommand(myAddPS);
		addKeyListener('s', myAddPS);
		
		//Increase Playership speed
		increaseSpeed = new Button("Increase Speed");
		increaseSpeed.getAllStyles().setBgTransparency(255);
		increaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		increaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		increaseSpeed.getAllStyles().setPadding(TOP, 2);
		increaseSpeed.getAllStyles().setPadding(BOTTOM, 2);
		increaseSpeed.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(increaseSpeed);
		myIncreaseSpeed = new IncreaseSpeedCommand(gw);
		increaseSpeed.setCommand(myIncreaseSpeed);
		addKeyListener(-91, myIncreaseSpeed);
		
		//Decrease Playership speed
		decreaseSpeed = new Button("Decrease Speed");
		decreaseSpeed.getAllStyles().setBgTransparency(255);
		decreaseSpeed.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		decreaseSpeed.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		decreaseSpeed.getAllStyles().setPadding(TOP, 2);
		decreaseSpeed.getAllStyles().setPadding(BOTTOM, 2);
		decreaseSpeed.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(decreaseSpeed);
		myDecreaseSpeed = new DecreaseSpeedCommand(gw);
		decreaseSpeed.setCommand(myDecreaseSpeed);
		addKeyListener(-92, myDecreaseSpeed);
		
		//turn left playership
		turnLeft = new Button("Turn Left");
		turnLeft.getAllStyles().setBgTransparency(255);
		turnLeft.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		turnLeft.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turnLeft.getAllStyles().setPadding(TOP, 2);
		turnLeft.getAllStyles().setPadding(BOTTOM, 2);
		turnLeft.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(turnLeft);
		myTurnLeft = new TurnLeftCommand(gw);
		turnLeft.setCommand(myTurnLeft);
		addKeyListener(-93, myTurnLeft);
		
		//turn right playership
		turnRight = new Button("Turn Right");
		turnRight.getAllStyles().setBgTransparency(255);
		turnRight.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		turnRight.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		turnRight.getAllStyles().setPadding(TOP, 2);
		turnRight.getAllStyles().setPadding(BOTTOM, 2);
		turnRight.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(turnRight);
		myTurnRight = new TurnRightCommand(gw);
		turnRight.setCommand(myTurnRight);
		addKeyListener(-94, myTurnRight);
		
		//rotate missile launcher left
		rotateMissileLauncherLeft = new Button("Rotate Missile Launcher Left");
		rotateMissileLauncherLeft.getAllStyles().setBgTransparency(255);
		rotateMissileLauncherLeft.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		rotateMissileLauncherLeft.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		rotateMissileLauncherLeft.getAllStyles().setPadding(TOP, 2);
		rotateMissileLauncherLeft.getAllStyles().setPadding(BOTTOM, 2);
		rotateMissileLauncherLeft.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(rotateMissileLauncherLeft);
		myRotateMissileLauncherLeft = new RotateMissileLauncherLeftCommand(gw);
		rotateMissileLauncherLeft.setCommand(myRotateMissileLauncherLeft);
		addKeyListener(44 , myRotateMissileLauncherLeft);
		
		//rotate missile launcher right
		rotateMissileLauncherRight = new Button("Rotate Missile Launcher Right");
		rotateMissileLauncherRight.getAllStyles().setBgTransparency(255);
		rotateMissileLauncherRight.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		rotateMissileLauncherRight.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		rotateMissileLauncherRight.getAllStyles().setPadding(TOP, 2);
		rotateMissileLauncherRight.getAllStyles().setPadding(BOTTOM, 2);
		rotateMissileLauncherRight.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(rotateMissileLauncherRight);
		myRotateMissileLauncherRight = new RotateMissileLauncherRightCommand(gw);
		rotateMissileLauncherRight.setCommand(myRotateMissileLauncherRight);
		addKeyListener(46 , myRotateMissileLauncherRight);
		
		//fire missile ps
		fireMissile = new Button("Fire Missile");
		fireMissile.getAllStyles().setBgTransparency(255);
		fireMissile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		fireMissile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		fireMissile.getAllStyles().setPadding(TOP, 2);
		fireMissile.getAllStyles().setPadding(BOTTOM, 2);
		fireMissile.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(fireMissile);
		myFireMissile = new FireMissileCommand(gw);
		fireMissile.setCommand(myFireMissile);
		addKeyListener(-90, myFireMissile);
		
		//launch missile nps
//		Button launchMissile = new Button("NPS launch Missile");
//		launchMissile.getAllStyles().setBgTransparency(255);
//		launchMissile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
//		launchMissile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
//		launchMissile.getAllStyles().setPadding(TOP, 1);
//		launchMissile.getAllStyles().setPadding(BOTTOM, 1);
//		launchMissile.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
//		leftContainer.add(launchMissile);
//		LaunchMissileCommand myLaunchMissile = new LaunchMissileCommand(gw);
//		launchMissile.setCommand(myLaunchMissile);
//		addKeyListener('L', myLaunchMissile);
		
		//jump through hyperspace
		jump = new Button("Hyperspace");
		jump.getAllStyles().setBgTransparency(255);
		jump.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		jump.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		jump.getAllStyles().setPadding(TOP, 2);
		jump.getAllStyles().setPadding(BOTTOM, 2);
		jump.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(jump);
		JumpCommand myJump = new JumpCommand(gw);
		jump.setCommand(myJump);
		addKeyListener('j', myJump);
		
		//reload missiles
//		Button reload = new Button("Reload Missiles");
//		reload.getAllStyles().setBgTransparency(255);
//		reload.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
//		reload.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
//		reload.getAllStyles().setPadding(TOP, 1);
//		reload.getAllStyles().setPadding(BOTTOM, 1);
//		reload.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
//		leftContainer.add(reload);
//		ReloadCommand myReload = new ReloadCommand(gw);
//		reload.setCommand(myReload);
//		addKeyListener('n', myReload);
		
		//refuel
		refuel = new Button("Refuel");
		refuel.getAllStyles().setBgTransparency(255);
		refuel.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		refuel.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		refuel.getAllStyles().setPadding(TOP, 2);
		refuel.getAllStyles().setPadding(BOTTOM, 2);
		refuel.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(refuel);
		RefuelCommand myRefuel = new RefuelCommand(gw,this);
		refuel.setCommand(myRefuel);
		
		String name;
		if(repeatTime) {
			System.out.println("test");
			name="Pause";
		}else {
			name="Play";
		}
		
		pause = new Button(name);
		pause.getAllStyles().setBgTransparency(255);
		pause.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		pause.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		pause.getAllStyles().setPadding(TOP, 2);
		pause.getAllStyles().setPadding(BOTTOM, 2);
		pause.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(pause);
		PauseCommand myPause = new PauseCommand(this,gw, name);
		pause.setCommand(myPause);
//			Button play = new Button("Play");
//			play.getAllStyles().setBgTransparency(255);
//			play.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
//			play.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
//			play.getAllStyles().setPadding(TOP, 1);
//			play.getAllStyles().setPadding(BOTTOM, 1);
//			play.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
//			leftContainer.add(play);
//		
		exit = new Button("Exit");
		exit.getAllStyles().setBgTransparency(255);
		exit.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		exit.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		exit.getAllStyles().setPadding(TOP, 2);
		exit.getAllStyles().setPadding(BOTTOM, 2);
		exit.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		leftContainer.add(exit);
		QuitCommand myQuitButton = new QuitCommand(gw);
		exit.setCommand(myQuitButton);
		
		//tick
//		Button tick = new Button("Game Clock Tick");
//		tick.getAllStyles().setBgTransparency(255);
//		tick.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
//		tick.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
//		tick.getAllStyles().setPadding(TOP, 1);
//		tick.getAllStyles().setPadding(BOTTOM, 1);
//		tick.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
//		leftContainer.add(tick);
//		TickCommand myTick = new TickCommand(gw);
//		tick.setCommand(myTick);
//		addKeyListener('t', myTick);
		
		add(BorderLayout.NORTH, pv);
		add(BorderLayout.CENTER,mv);
		add(BorderLayout.WEST,leftContainer);
		timer.schedule(150, true, this);
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!repeatTime) {
			pause.setText("Play");
			isPause = true;
			addAsteroid.setEnabled(true);
			this.addKeyListener('a', myAddAsteroid);
			addStation.setEnabled(true);
			this.addKeyListener('b', myAddStation);
			addPS.setEnabled(true);
			this.addKeyListener('s', myAddPS);
			increaseSpeed.setEnabled(false);
			this.removeKeyListener(-91, myIncreaseSpeed);
			increaseSpeed.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			decreaseSpeed.setEnabled(false);
			this.removeKeyListener(-92, myDecreaseSpeed);
			decreaseSpeed.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			turnLeft.setEnabled(false);
			this.removeKeyListener(-93, myTurnLeft);
			turnLeft.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			turnRight.setEnabled(false);
			this.removeKeyListener(-94, myTurnRight);
			turnRight.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			fireMissile.setEnabled(false);
			this.removeKeyListener(-90, myFireMissile);
			fireMissile.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			jump.setEnabled(false);
			this.removeKeyListener('j', myJump);
			jump.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			refuel.setEnabled(true);
			refuel.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			rotateMissileLauncherLeft.setEnabled(false);
			this.removeKeyListener(44, myRotateMissileLauncherLeft);
			rotateMissileLauncherLeft.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			rotateMissileLauncherRight.setEnabled(false);
			this.removeKeyListener(46, myRotateMissileLauncherRight);
			rotateMissileLauncherRight.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			exit.setEnabled(true);
			gw.handleSound();
			mv.selector();
			this.repaint();
		}else {
			pause.setText("Pause");
			isPause=false;
			addAsteroid.setEnabled(false);
			this.removeKeyListener('a', myAddAsteroid);
			addAsteroid.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			addStation.setEnabled(false);
			this.removeKeyListener('b', myAddStation);
			addStation.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			addPS.setEnabled(false);
			this.removeKeyListener('s', myAddPS);
			addPS.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			increaseSpeed.setEnabled(true);
			this.addKeyListener(-91, myIncreaseSpeed);
			decreaseSpeed.setEnabled(true);
			this.addKeyListener(-92, myDecreaseSpeed);
			turnLeft.setEnabled(true);
			this.addKeyListener(-93, myTurnLeft);
			turnRight.setEnabled(true);
			this.addKeyListener(-94, myTurnRight);
			fireMissile.setEnabled(true);
			this.addKeyListener(-90, myFireMissile);
			jump.setEnabled(true);
			this.addKeyListener('j', myJump);
			refuel.setEnabled(false);
			refuel.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			rotateMissileLauncherLeft.setEnabled(true);
			this.addKeyListener(44 , myRotateMissileLauncherLeft);
			rotateMissileLauncherRight.setEnabled(true);
			this.addKeyListener(46 , myRotateMissileLauncherRight);
			exit.setEnabled(false);
			exit.getDisabledStyle().setBgColor(ColorUtil.LTGRAY);
			mv.unSelectAll();
			mv.deselector();
			gw.tick();
			int roll = Game.genRandInt(1,100);
			if(roll>= 1 && roll <= 2) {
				gw.addNPS();
				gw.launchMissileNPS();
				gw.launchMissileNPS();
			}
			
		}
	}
	public static int genRandInt(int min, int max) {
		Random r = new Random();
		int x = r.nextInt((max-min)+1)+min;
		return x;
	}
	public boolean getRepeatTime() {
		return repeatTime;
	}
	public void pauseNPlay() {
		// TODO Auto-generated method stub
		repeatTime=!repeatTime;
	}
	public boolean getIsPause() {
		return isPause;
	}
}
