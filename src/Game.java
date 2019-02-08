import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends GameDriverV2 implements KeyListener, MouseListener, MouseMotionListener{
	SoundDriver s1;
	SoundDriver s2;
	SoundDriver s3; 
	SoundDriver s4;
	
	BufferedImage mainMenu;
	BufferedImage back;
	BufferedImage backUp;
	BufferedImage backUpp;
	BufferedImage backP;
	BufferedImage overlay;
	BufferedImage instrucA;
	BufferedImage instrucB;
	BufferedImage instrucC;
	BufferedImage instrucD;
	BufferedImage winner;
	BufferedImage lose;
	BufferedImage paused;
	BufferedImage credits;
	BufferedImage sheet;
	BufferedImage weapon;
	BufferedImage weapon1;
	BufferedImage weapon2;
	BufferedImage cover;
	BufferedImage sheet2;
	BufferedImage monster;
	BufferedImage projectile;
	BufferedImage bomb;
	BufferedImage sheet3;
	BufferedImage playerProjectile1;
	BufferedImage playerProjectile2;
	BufferedImage lightning;
	BufferedImage fire;
	BufferedImage ice;
	BufferedImage transparent;
	BufferedImage light; 
	BufferedImage sheet4;
	
	BufferedImage trans1;
	
	Rectangle bg = new Rectangle(0, 0, 1200, 780);
	
	Rectangle t1 = new Rectangle(20, 250, 275, 60);
	Rectangle t2 = new Rectangle(20, 335, 275, 60);
	Rectangle t3 = new Rectangle(20, 420, 275, 60);
	
	Rectangle playerScreen = new Rectangle(193, 47, 868, 600);
	Rectangle pl = new Rectangle(20, 95, 275, 60);
	
	Rectangle begin = new Rectangle(110, 128, 314, 83);
	Rectangle credit = new Rectangle(110, 228, 314, 83);
	
	Rectangle closeUp = new Rectangle(1000, 55, 57, 55);
	Rectangle closePUp = new Rectangle(1000, 55, 57, 55);
	
	Rectangle white = new Rectangle(1100, 0, 100, 780);
	
	Rectangle costBack = new Rectangle(0, 0, 119, 35);
	
	Rectangle next1 = new Rectangle(335, 540, 175, 65);
	Rectangle next2 = new Rectangle(419, 289, 185, 60);
	Rectangle next3 = new Rectangle(125, 525, 210, 75);
	Rectangle next4 = new Rectangle(575, 559, 185, 60);
	
	Rectangle goBack = new Rectangle(350, 525, 550, 75);
	Rectangle pause = new Rectangle(1000, 10, 150, 60);
	Rectangle proceed = new Rectangle(445, 235, 315, 75);
	Rectangle pauseBack = new Rectangle(445, 380, 315, 75);
	
	Rectangle costBox = new Rectangle(218, 560, 235, 65);
	
	ArrayList<Projectile> projs2 = new ArrayList<Projectile>();
	
	Player p;
	
	int position = 1;
	int move = 0;
	int time = 0;
	int lasTime = 0;
	
	int theta = 0;
	
	int moveBack = 717;
	int moveCredits = 700;
	
	int currentWave = 0;
	int resources = 500;
	int addR = 500;
	
	int gameState = 0;
	// gameState 0 menu
	// gameState 1 game
	// gameState 2 upgrade screen
	
	boolean wAppear = false;
	boolean hoverB = false;
	
	boolean hoverSell = false;
	
	boolean sSelected = false;
	boolean pSelected = false;
	boolean start = false;
	int boxY = 0;
	
	boolean instructions1 = false;
	boolean instructions2 = false;
	
	boolean instructions3 = false;
	boolean instructions4 = false;
	
	boolean clickPause = false;
	
	Font f1 = new Font("Arial", Font.BOLD, 24);
	Font f2=  new Font("Arial", Font.BOLD, 56);
	Font f3 = new Font("Arial", Font.BOLD, 36);
	int cost;
	int cX;
	int cY;
	boolean drawCost;
	
	String day = "DAY";
	Color c1 = new Color(255, 255, 255, 255);
	int c1A = 255;
	
	Timer music1 = new Timer(51);
	Timer music2 = new Timer(51);
	
	Turret[] turrets = new Turret[3];
	
	Wave theWave;
	
	Animation a;
	
	HealthBar baseH = new HealthBar(250, 10, 650, 250, 30);
	
	int[][] set = {{10, 0, 0, 0, 0}, {0, 15, 0, 0, 0}, {10, 20, 0, 0, 0}, {0, 0, 5, 0, 0}, {15, 0, 5, 0 , 0}, {20, 30, 8, 0, 0}, {0, 0, 0, 10, 0}, {20, 0, 5, 15, 0}, {25, 30, 10, 20, 0}, {0, 0, 0, 0, 1}};
	//                   1                   2                  3                 4                 5                  6                 7                 8                   9                  10
	public Game(){
		mainMenu = this.addImage("Menu.png");
		back = this.addImage("Forest.png");
		backUp = this.addImage("Upgrade.png");
		backUpp = this.addImage("UpgradePop.png");
		backP = this.addImage("PlayerPop.png");
		overlay = this.addImage("Overlay.png");
		instrucA = this.addImage("InstructionsA.png");
		instrucB = this.addImage("InstructionsB.png");
		instrucC = this.addImage("InstructionsC.png");
		instrucD = this.addImage("InstructionsD.png");
		winner = this.addImage("Win.png");
		lose = this.addImage("Lose.png");
		paused = this.addImage("Pause.png");
		credits = this.addImage("Credits.png");
		sheet = this.addImage("Weapons.png");
		sheet2 = this.addImage("Monsters.png");
		sheet3 = this.addImage("Player.png");
		transparent = this.addImage("Transparent.png");
		light = this.addImage("Flash.png");
		sheet4 = this.addImage("Highlight.png");
		monster = sheet2.getSubimage(0, 0, 120, 45);
		projectile = sheet.getSubimage(48, 0, 14, 12);
		bomb = sheet.getSubimage(62, 0, 15, 30);
		playerProjectile1 = sheet3.getSubimage(70, 0, 15, 15);
		playerProjectile2 = sheet3.getSubimage(163, 0, 25, 20);
		lightning = sheet3.getSubimage(76, 440, 22, 20);
		fire = sheet3.getSubimage(76, 150, 18, 20); 
		ice = sheet3.getSubimage(76, 297, 18, 20);
		weapon = sheet.getSubimage(77, 0, 48, 10);
		weapon1 = sheet.getSubimage(77, 10, 50, 9);
		weapon2 = sheet.getSubimage(77, 37, 48, 15);
		cover = sheet.getSubimage(128, 0, 20, 55);
		trans1 = transparent.getSubimage(0, 0, 100, 75);
		
		a = new Animation(this.addImage("Explosion.png"), 9, 9, 3);
		
		for(int i = 0; i < 3; i++){
			turrets[i] = new Turret(weapon1, cover, 0);
			for(int j = 0; j < 6; j++){
				turrets[i].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
				turrets[i].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
				turrets[i].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
			}
		}
	
		theWave = new Wave(0, 0, 0, 0, 0, sheet2);
	
		String[] str1 = {"hit.wav", "grand.wav", "projectile.wav", "click.wav"};
		String[] str2 = {"victory.wav"};
		String[] str3 = {"freeze.wav", "bomb.wav", "lightning.wav", "fireball.wav"};
		
		s1 = new SoundDriver(str1);
		s2 = new SoundDriver(str2);
		s3 = new SoundDriver(str3);
		s4 = new SoundDriver(str2);
		
		p = new Player(10, playerProjectile1, 2, 40, sheet3, s3);
		p.br.currentState = sheet4.getSubimage(0, 140, 280, 68);
		for(int i = 0; i < p.buttons2.length; i++){
			p.buttons2[i].currentState = sheet4.getSubimage(0, 487, 220, 74);
		}
		
		music1.setTime(0);
		music2.setTime(26);
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame j1 = new JFrame();
			j1.setSize(1200, 740); 
			j1.setTitle("Final Stand" );
			j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			j1.add(new Game());
			
			j1.setVisible(true);
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		music1.startTimer();
		if(music1.getTime() <= 0){
			s2.play(0);
			music1.resetTimer();
		}
		music2.startTimer();
		if(music2.getTime() <= 0){
			s4.play(0);
			music2.resetTimer();
		}
		if(gameState == -1){
			win.drawImage(back, null, 0, 0);
			Color tempC = new Color(0, 0, 0, 170);
			win.setColor(tempC);
			win.fill(bg);
			win.drawImage(credits, null, 0, moveCredits);
			moveCredits--;
			if(moveCredits <= -700){
				gameState = 0;
				moveCredits = 700;
			}
			
		}
		if(gameState == 0){
			win.drawImage(mainMenu, null, 0, 0);
			Color c1 = new Color(255, 255, 255, 120);
			win.setColor(c1);
			if(hoverB){
				win.fill(begin);
			}
		}
		if(gameState == 1 && moveBack > 0){
			win.drawImage(backUp, null, -717 + moveBack, 0);
			win.drawImage(back, null, 0 + moveBack, 0);
			if(!instructions1){
				win.drawImage(instrucA, null, 0, 0);
				win.setColor(Color.CYAN);
				//win.fill(next1);
			}
			if(instructions1){
				p.moveAndDraw(win, moveBack);
			}
			
			for(int i = 0; i < 3; i++){
				if(turrets[i] != null){
					turrets[i].moveAndDraw(win, moveBack);
				}
			}
			
			if(moveBack > 0){
				moveBack -= 10;
			}
			c1A = 255;
			
		}
		if(gameState == 1 && moveBack <= 0 && instructions1 && instructions3 || instructions4){
			win.drawImage(backUp, null, -717 + moveBack, 0);
			win.drawImage(back, null, 0 + moveBack, 0);
			p.moveAndDraw(win, moveBack);
			p.rechargeBar.moveAndDraw(win);
			if(instructions3){
				win.drawImage(instrucC, null, 0, 0);
				win.setColor(Color.WHITE);
				win.setFont(f3);
				win.drawString("PRESS F TO CYCLE", 600, 300);
				win.drawString("THROUGH ABILITIES", 600, 340);
				//win.fill(next3);
			}
			if(instructions4 && !instructions3){
				win.drawImage(instrucD, null, 0, 0);
				//win.fill(next4);
			}

		}
		if(gameState == 1 && moveBack <= 0 && instructions1 && !instructions3 && !instructions4){
			win.setColor(Color.WHITE);
			win.fill(bg);
			win.setColor(Color.CYAN);
			//win.fill(t1);
			//win.fill(t2);
			//win.fill(t3);
			if(time % 30 == 0){
				theta++;
			}
			
			win.drawImage(backUp, null, -717 + moveBack, 0);
			win.drawImage(back, null, 0 + moveBack, 0);
			
			baseH.moveAndDraw(win);
		
			if(pause.getX() != 1030){
				pause.move(1030, 20);
			}
			
			win.setFont(f2);
			if(c1A > 0){
				c1A-= 4;
			} 
			if(c1A < 0){
				c1A = 0;
			}
			c1 = new Color(255, 255, 255, c1A);
			
			
			win.setColor(c1);
			win.drawString(day + " " + (currentWave + 1), 500, 300);
			
			win.setColor(Color.WHITE);
			
			if(theWave.mons.size() > 0){
				position = theWave.mons.get(theWave.indexOfFirst()).returnPos();
			}
			
			if(!clickPause && !baseH.noHp()){
				for(int i = 0; i < 3; i++){
					if(turrets[i] != null && turrets[i].type == 1){
						turrets[i].moveAndDraw(win, moveBack);
						if(theWave.mons.size() > 0){
							turrets[i].shoot(win, projectile, position, s1);
						}
						for(int j = 0; j < turrets[i].projs.size(); j++){
							//System.out.println(turrets[0].projs.size());
							turrets[i].projs.get(j).shootP(win);
							if(j >= 0 && turrets[i].type == 1 && theWave.mons.size() > 0 && turrets[i].returnA().get(j).returnHB().intersects(theWave.mons.get(theWave.indexOfFirst()).returnHB())){
								turrets[i].returnA().remove(j);
								j--;
								s1.play(0);
								theWave.mons.get(theWave.indexOfFirst()).h.loseH(turrets[i].damage);
							}
							//System.out.println(turrets[i].projs.get(j).hB.getY());
							if(j >= 0 && turrets[i].projs.get(j).hB.getY() > 730){
								turrets[i].projs.remove(j);
								j--;
							}
						}
					}
					if(turrets[i] != null && turrets[i].type == 2){
						turrets[i].moveAndDraw(win, moveBack);
						if(theWave.mons.size() > 0){
							//s1.loop(1);
							((LaserTurret)turrets[i]).shoot(win, projectile, position, theWave.mons.get(theWave.indexOfSecond()).returnHB().getX(),
									theWave.mons.get(theWave.indexOfThird()).returnHB().getX(), theWave.mons.get(theWave.indexOfFourth()).returnHB().getX(), theWave.mons.get(theWave.indexOfFifth()).returnHB().getX());
							if(((LaserTurret)turrets[i]).projs.get(0).l1.intersects(theWave.mons.get(theWave.indexOfFirst()).returnHB())){
								lasTime++;
								if(lasTime % turrets[i].rate == 0){
									theWave.mons.get(theWave.indexOfFirst()).h.loseH(1);
									if(turrets[i].speedUps[0] && theWave.mons.size() >= 2){
										theWave.mons.get(theWave.indexOfSecond()).h.loseH(1);
									}
									if(turrets[i].speedUps[1] && theWave.mons.size() >= 3){
										theWave.mons.get(theWave.indexOfThird()).h.loseH(1);
									}
									if(turrets[i].speedUps[2] && theWave.mons.size() >= 4){
										theWave.mons.get(theWave.indexOfFourth()).h.loseH(1);
									}
									if(turrets[i].speedUps[2] && theWave.mons.size() >= 5){
										theWave.mons.get(theWave.indexOfFifth()).h.loseH(1);
									}
								}
							}
	
						}
					}
					if(turrets[i] != null && turrets[i].type == 3){
						turrets[i].moveAndDraw(win, moveBack);
						if(theWave.mons.size() > 0){
							turrets[i].shoot(win, bomb, position, s1);
						}
						if(((BombTurret)turrets[i]).bProjs.size() > 0){	
							for(int k = 0; k < ((BombTurret)turrets[i]).bProjs.size(); k++){
								((BombTurret)turrets[i]).bProjs.get(k).shootP(win);
								if(((BombTurret)turrets[i]).bProjs.get(k).explode){
									s3.play(1);
									a.changeA(true, (int)((BombTurret)turrets[i]).bProjs.get(k).returnHB().getX() - 35, (int)((BombTurret)turrets[i]).bProjs.get(k).returnHB().getY() - 35);
									for(int j = 0; j < theWave.mons.size(); j++){
										if(theWave.mons.get(j).returnHB().getX() >= ((BombTurret)turrets[i]).bProjs.get(k).b.getBounds().getX() - turrets[i].rate && theWave.mons.get(j).returnHB().getX() <= ((BombTurret)turrets[i]).bProjs.get(k).b.getBounds().getX() + turrets[i].rate){
											theWave.mons.get(j).h.loseH(turrets[i].damage);
										}
									}
									if(turrets[i].speedUps[2] && ((BombTurret)turrets[i]).bProjs.get(k).cluster == 0){
										((BombTurret)turrets[i]).bProjs.add(new BombProjectile(bomb, ((BombTurret)turrets[i]).bProjs.get(k).returnHB().getX(), 595.0, position, 595.0, 1));
										((BombTurret)turrets[i]).bProjs.add(new BombProjectile(bomb, ((BombTurret)turrets[i]).bProjs.get(k).returnHB().getX(), 595.0, position, 595.0, 2));
									}
									((BombTurret)turrets[i]).bProjs.remove(k);
								}
							}
						}
				
					}
				}
				for(int i = 0; i < p.projs.size(); i++){
					p.projs.get(i).shootP(win);
					for(int j = 0; j < theWave.mons.size(); j++){
						if(i >= 0 && theWave.mons.size() > 0 && p.projs.get(i).returnHB().intersects(theWave.mons.get(j).returnHB())){
							if(p.projs.get(i).type == 1){
								theWave.mons.get(j).burn(p.fireTime, p.fireDamage);
							}
							if(p.projs.get(i).type == 2){
								theWave.mons.get(j).slow(p.iceTime, p.iceSpeed);
								s3.play(0);
							}
							if(p.projs.get(i).type != 2){
								p.projs.remove(i);
								theWave.mons.get(j).h.loseH(p.damage);
							}
							i--;
						}
					}
					if(i >= 0 && p.projs.get(i).hB.getY() > 750){
						p.projs.remove(i);
					}
				}
			}
			for(int i = 0; i < theWave.mons.size(); i++){
				if(theWave.mons.get(i).returnHB().getX() <= 230 && theWave.mons.get(i).hitTime > 30){
					baseH.loseH(theWave.mons.get(i).damage);
					theWave.mons.get(i).hitTime = 0;
				}
				if(theWave.mons.get(i).returnHB().intersects(p.lightHB)){
					theWave.mons.get(i).h.loseH(p.lightningDamage);
					p.lightHB.move(0, 0);
				}
				if(theWave.mons.get(i).h.noHp()){
					if(theWave.mons.get(i).canSplit){
						theWave.addSplits((int)theWave.mons.get(i).returnHB().getX());
					}
					//theWave.effects.add(new Effect((int)theWave.mons.get(i).returnHB().getX(), (int)theWave.mons.get(i).returnHB().getY()));
					theWave.mons.remove(i);
					if(theWave.bossDay && i == 0){
						theWave.bossDay = false;
					}
				}
			}
			if(!clickPause){
				theWave.startWave(win);
			}
			
			a.moveAndDraw(win);
			
			time++;
			if(theWave.mons.size() > 0){
				p.shoot(position, s1);
			}
			p.moveAndDraw(win, moveBack);
			if(p.lightningUp || p.fireUp || p.iceUp){
				p.rechargeBar.moveAndDraw(win);
			}
			
			if(p.stormTime > 0 && theWave.bossDay){
				theWave.mons.get(0).h.move(350, 55);
			}
			if(p.stormTime <= 0 && theWave.bossDay){
				theWave.mons.get(0).h.move(350, 15);
			}
			
			if(baseH.noHp()){
				goBack.move(350, 525);
				//win.setColor(Color.CYAN);
				//win.fill(goBack);
				win.drawImage(lose, null, 0, 0);
				win.setFont(f2);
				win.setColor(Color.WHITE);
				win.drawString("" + (currentWave), 595, 250);
			}
			
			if(theWave.waveDone() && currentWave <= 8){
				gameState = 2;
				resources += addR;
				addR += 250;
				currentWave++;
				theWave = new Wave(set[currentWave][0], set[currentWave][1], set[currentWave][2], set[currentWave][3], set[currentWave][4], sheet2);
				for(int i = 0; i < 3; i++){
					if(turrets[i] != null){
						while(turrets[i].projs.size() > 0){
							turrets[i].projs.remove(0);
						}
						if(turrets[i].type == 3){
							while(((BombTurret)turrets[i]).bProjs.size() > 0){
								((BombTurret)turrets[i]).bProjs.remove(0);
							}
						}
					}
				}
				while(p.projs.size() > 0){
					p.projs.remove(0);
				}
			}
			if(theWave.waveDone() && currentWave == 9){
				goBack.move(330, 370);
				//win.fill(goBack);
				win.drawImage(winner, null, 0, 0);
			}
			
			if(clickPause){
				Color temp = new Color(0, 0, 0, 170);
				win.setColor(temp);
				win.fill(bg);
				win.drawImage(paused, null, 0, 0);
				
				win.setFont(f2);
				win.setColor(Color.BLACK);
				win.drawString("CONTINUE", 455, 294);
				win.drawString("MENU", 520, 440);
				
				win.setColor(Color.RED);
				//win.fill(proceed);
				//win.fill(pauseBack);
			}
		}
		if(gameState == 2 && moveBack < 717){
			win.setColor(Color.DARK_GRAY);
			win.fill(bg);
			win.drawImage(backUp, null, -717 + moveBack, 0);
			win.drawImage(back, null, moveBack, 0);
			
			if(!instructions2 && moveBack >= 707){
				win.drawImage(instrucB, null, 0, 0);
				win.setColor(Color.CYAN);
				//win.fill(next2);
			}
			if(pause.getX() != 946){
				pause.move(946, 15);
			}
			
			
			for(int i = 0; i < 3; i++){
				if(turrets[i] != null){
					turrets[i].moveAndDraw(win, moveBack);
				}
			}
	
			p.moveAndDraw(win, moveBack);
			moveBack+= 10;
		}
		if(gameState == 2 && moveBack >= 717 && instructions2){
			win.setColor(Color.DARK_GRAY);
			win.fill(bg);
			win.drawImage(backUp, null, -717 + moveBack, 0);
			win.drawImage(back, null, moveBack, 0);
			p.moveAndDraw(win, moveBack);
			
			for(int i = 0; i < 3; i++){
				if(turrets[i] != null){
					turrets[i].moveAndDraw(win, moveBack);
				}
			}
			
			win.setFont(f3);
			win.setColor(Color.WHITE);
			win.drawString("" + resources, 515, 110);
			
			win.drawImage(overlay, null, -15, 0);
	
			
			if(pSelected){
				win.drawImage(backP, null, 0, 0);
				for(int i = 0; i < p.buttons1.length; i++){
					p.buttons1[i].moveAndDraw(win);
				}
				for(int i = 0; i < p.buttons2.length; i++){
					p.buttons2[i].moveAndDraw(win);
				}
				win.setFont(f1);
				if(drawCost){
					costBack.move(cX, cY - 25);
					win.setColor(Color.BLACK);
					win.fill(costBack);
					win.setColor(Color.GREEN);
					win.drawString("COST: " + cost, cX, cY);
				}
				win.setFont(f3);
				win.setColor(Color.WHITE);
				win.drawString("" + resources, 305, 230);
			}
			
			if(sSelected){
				win.drawImage(backUpp, null, 0, 0);
				turrets[boxY].ba.moveAndDraw(win);
				turrets[boxY].la.moveAndDraw(win);
				turrets[boxY].bo.moveAndDraw(win);
				
				for(int i = 0; i < 6; i++){
					turrets[boxY].Bups[i].moveAndDraw(win);
					turrets[boxY].Lups[i].moveAndDraw(win);
					turrets[boxY].Bomups[i].moveAndDraw(win);
				}
				win.setFont(f2);
				win.setColor(Color.BLACK);
				int temp = boxY + 1;
				win.drawString("" + temp, 700, 108);
				win.setFont(f1);
				if(drawCost){
					costBack.move(cX, cY - 25);
					win.setColor(Color.BLACK);
					win.fill(costBack);
					win.setColor(Color.GREEN);
					win.drawString("COST: " + cost, cX, cY);
				}
				if(turrets[boxY].yPos != 0){
					win.setFont(f3);
					win.setColor(Color.CYAN);
					//win.fill(costBox);
					if(hoverSell){
						win.setColor(Color.RED);
					}else{
						win.setColor(Color.RED.darker());
					}
				
					win.drawString("SELL FOR " + turrets[boxY].turretValue, 220, 610);
				
				}
				win.setFont(f3);
				win.setColor(Color.WHITE);
				win.drawString("" + resources, 594, 585);
					
			}
			
			win.setColor(Color.GREEN);
			//win.fill(closePUp);
			
			if(moveBack < 700){
				moveBack += 5;
			}
			
			Color c = new Color(255,255,255, 127);
			win.setColor(c);
			if(wAppear){
				win.fill(white);
			}
			
			if(clickPause){
				Color temp = new Color(0, 0, 0, 170);
				win.setColor(temp);
				win.fill(bg);
				win.drawImage(paused, null, 0, 0);
				
				win.setFont(f2);
				win.setColor(Color.BLACK);
				win.drawString("CONTINUE", 455, 294);
				win.drawString("MENU", 520, 440);
				
				win.setColor(Color.RED);
				//win.fill(proceed);
				//win.fill(pauseBack);
			}
		
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE && !clickPause){
			if(p.lightningUp3 && gameState == 1 && !theWave.usedMaster && !instructions3 && !instructions4 && p.cycle == 1){
				p.masterLight();
				theWave.usedMaster = true;
			}
			if(p.iceUp3 && gameState == 1 && !theWave.usedMaster && !instructions3 && !instructions4 && p.cycle == 3){
				p.masterIce();
				theWave.usedMaster = true;
			}
			if(p.fireUp3 && gameState == 1 && !theWave.usedMaster && !instructions3 && !instructions4 && p.cycle == 2){
				p.masterFire();
				theWave.usedMaster = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_F){
			p.cycle();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(gameState == 2 && instructions2){
			if(sSelected == false && !clickPause && instructions2){
				if(t1.contains(e.getPoint())){
					sSelected = true;
					boxY = 0;
					s1.play(3);
				}
				if(t2.contains(e.getPoint())){
					sSelected = true;
					boxY = 1;
					s1.play(3);
				}
				if(t3.contains(e.getPoint())){
					sSelected = true;
					boxY = 2;
					s1.play(3);
				}
			}
			if(sSelected){
				if(costBox.contains(e.getPoint())){
					resources += turrets[boxY].turretValue; 
					turrets[boxY] = new Turret(weapon1, cover, 0);
					for(int j = 0; j < 6; j++){
						turrets[boxY].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
				}
				if(turrets[boxY].ba.contains(e.getPoint()) && resources >= cost){
					turrets[boxY] = new Turret(weapon, cover, boxY + 1);
					turrets[boxY].ba.currentState = sheet4.getSubimage(0, 0, 275, 64);
					turrets[boxY].la.currentState = sheet4.getSubimage(0, 275, 275, 64);
					turrets[boxY].bo.currentState = sheet4.getSubimage(0, 275, 275, 64);
					for(int j = 0; j < 6; j++){
						turrets[boxY].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
					turrets[boxY].bup11.currentState = null;
					turrets[boxY].bup21.currentState = null;
					resources -= cost;
					turrets[boxY].turretValue += (int)(cost * .75);
					s1.play(3);
				}
				if(turrets[boxY].la.contains(e.getPoint()) && resources >= cost){
					turrets[boxY] = new LaserTurret(weapon1, cover, boxY + 1);
					turrets[boxY].ba.currentState = sheet4.getSubimage(0, 275, 275, 64);
					turrets[boxY].la.currentState = sheet4.getSubimage(0, 0, 275, 64);
					turrets[boxY].bo.currentState = sheet4.getSubimage(0, 275, 275, 64);
					for(int j = 0; j < 6; j++){
						turrets[boxY].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
					turrets[boxY].lup11.currentState = null;
					turrets[boxY].lup21.currentState = null;
					resources -= cost;
					turrets[boxY].turretValue += (int)(cost * .75);
					s1.play(3);
				}
				if(turrets[boxY].bo.contains(e.getPoint()) && resources >= cost){
					turrets[boxY] = new BombTurret(weapon2, cover, boxY + 1);
					turrets[boxY].ba.currentState = sheet4.getSubimage(0, 275, 275, 64);
					turrets[boxY].la.currentState = sheet4.getSubimage(0, 275, 275, 64);
					turrets[boxY].bo.currentState = sheet4.getSubimage(0, 0, 275, 64);
					for(int j = 0; j < 6; j++){
						turrets[boxY].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[boxY].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
					turrets[boxY].bomup11.currentState = null;
					turrets[boxY].bomup21.currentState = null;
					resources -= cost;
					turrets[boxY].turretValue += (int)(cost * .75);
					s1.play(3);
				}
				if(turrets[boxY].type == 1){
					if(turrets[boxY].speedUps[0] == false){
						if(turrets[boxY].Bups[0].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[0].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bups[1].currentState = null;
							turrets[boxY].speedUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[1] == false){
						if(turrets[boxY].Bups[1].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[1].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bups[2].currentState = null;
							turrets[boxY].speedUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[2] == false){
						if(turrets[boxY].Bups[2].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[2].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].speedUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
					if(turrets[boxY].damageUps[0] == false){
						if(turrets[boxY].Bups[3].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[3].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bups[4].currentState = null;
							turrets[boxY].damageUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[1] == false){
						if(turrets[boxY].Bups[4].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[4].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bups[5].currentState = null;
							turrets[boxY].damageUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[2] == false){
						if(turrets[boxY].Bups[5].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bups[5].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].damageUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
				}
				if(turrets[boxY].type == 2){
					if(turrets[boxY].speedUps[0] == false){
						if(turrets[boxY].Lups[0].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[0].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Lups[1].currentState = null;
							turrets[boxY].speedUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[1] == false){
						if(turrets[boxY].Lups[1].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[1].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Lups[2].currentState = null;
							turrets[boxY].speedUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[2] == false){
						if(turrets[boxY].Lups[2].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[2].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].speedUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
					if(turrets[boxY].damageUps[0] == false){
						if(turrets[boxY].Lups[3].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[3].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Lups[4].currentState = null;
							turrets[boxY].damageUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[1] == false){
						if(turrets[boxY].Lups[4].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[4].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Lups[5].currentState = null;
							turrets[boxY].damageUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[2] == false){
						if(turrets[boxY].Lups[5].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Lups[5].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].damageUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
				}
				if(turrets[boxY].type == 3){
					if(turrets[boxY].speedUps[0] == false){
						if(turrets[boxY].Bomups[0].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bomups[0].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bomups[1].currentState = null;
							turrets[boxY].speedUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[1] == false){
						if(turrets[boxY].Bomups[1].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bomups[1].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bomups[2].currentState = null;
							turrets[boxY].speedUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].speedUps[2] == false){
						if(turrets[boxY].Bomups[2].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bomups[2].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].speedUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
					if(turrets[boxY].damageUps[0] == false){
						if(turrets[boxY].Bomups[3].contains(e.getPoint()) && resources >= cost){
							turrets[boxY].Bomups[3].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bomups[4].currentState = null;
							turrets[boxY].damageUps[0] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[1] == false && resources >= cost){
						if(turrets[boxY].Bomups[4].contains(e.getPoint())){
							turrets[boxY].Bomups[4].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].Bomups[5].currentState = null;
							turrets[boxY].damageUps[1] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					} else if(turrets[boxY].damageUps[2] == false && resources >= cost){
						if(turrets[boxY].Bomups[5].contains(e.getPoint())){
							turrets[boxY].Bomups[5].currentState = sheet4.getSubimage(0, 65, 140, 74);
							turrets[boxY].damageUps[2] = true;
							resources -= cost;
							turrets[boxY].turretValue += (int)(cost * .75);
							s1.play(3);
						}
					}
				}
				if(closeUp.contains(e.getPoint())){
					sSelected = false;
				}
			}
		}
		if(pl.contains(e.getPoint()) && gameState == 2 && instructions2 && !clickPause){
			pSelected = true;
			s1.play(3);
		}
		if(pSelected){
			if(p.fx.contains(e.getPoint()) && !p.iceUp && !p.fireUp && !p.lightningUp && resources >= cost){
				p.proj = playerProjectile2;
				p.damage = 3;
				p.rate = 35;
				p.pla = 11;
				
				p.fixedUp = true;
				p.fx.currentState = sheet4.getSubimage(0, 140, 280, 68);
				p.blt1.currentState = null;
				p.bal1.currentState = null;
				p.ici1.currentState = null;
				resources -= cost;
				s1.play(3);
			}
			if(p.blt1.contains(e.getPoint()) && p.fixedUp && !p.lightningUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 20;
				p.proj = lightning;
				
				p.lightningUp = true;
				p.blt1.currentState = sheet4.getSubimage(0, 210, 220, 68);
				//p.bal1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				//p.ici1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				
				p.blt2.currentState = null;
				p.cycle = 1;
				resources -= cost;
				if(!p.showA){
					instructions3 = true;
				}
				s1.play(3);
			}
			if(p.bal1.contains(e.getPoint()) && p.fixedUp && !p.fireUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 30;
				p.proj = fire;
				
				p.fireUp = true;
				p.bal1.currentState = sheet4.getSubimage(0, 210, 220, 68);
				//p.blt1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				//p.ici1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				
				p.bal2.currentState = null;
				p.cycle = 2;
				resources -= cost;
				if(!p.showA){
					instructions3 = true;
				}
				s1.play(3);
			}
			if(p.ici1.contains(e.getPoint()) && p.fixedUp && !p.iceUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 40;
				p.proj = ice;
				
				p.iceUp = true;
				p.ici1.currentState = sheet4.getSubimage(0, 210, 220, 68);
				//p.blt1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				//p.bal1.currentState = sheet4.getSubimage(0, 487, 220, 74);
				
				p.ici2.currentState = null;
				p.cycle = 3;
				resources -= cost;
				if(!p.showA){
					instructions3 = true;
				}
				s1.play(3);
			}
			if(p.blt2.contains(e.getPoint()) && p.lightningUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 20;
				
				p.lightningUp2 = true;
				p.blt2.currentState = sheet4.getSubimage(0, 210, 220, 68);
				
				p.blt3.currentState = null;
				resources -= cost;
				s1.play(3);
			}
			if(p.bal2.contains(e.getPoint()) && p.fireUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 30;
				
				p.fireUp2 = true;
				p.bal2.currentState = sheet4.getSubimage(0, 210, 220, 68);
				
				p.bal3.currentState = null;
				resources -= cost;
				s1.play(3);
			}
			if(p.ici2.contains(e.getPoint()) && p.iceUp && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 40;
				
				p.iceUp2 = true;
				p.ici2.currentState = sheet4.getSubimage(0, 210, 220, 68);
			
				p.ici3.currentState = null;
				resources -= cost;
				s1.play(3);
			}
			if(p.blt3.contains(e.getPoint()) && p.lightningUp2 && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 20;
				
				p.lightningUp3 = true;
				p.blt3.currentState = sheet4.getSubimage(0, 210, 220, 68);
				resources -= cost;
				
				if(!p.showB){
					instructions4 = true;
				}
				s1.play(3);
			}
			if(p.bal3.contains(e.getPoint()) && p.fireUp2 && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 30;
				
				p.fireUp3 = true;
				p.bal3.currentState = sheet4.getSubimage(0, 210, 220, 68);
				resources -= cost;
				
				if(!p.showB){
					instructions4 = true;
				}
				s1.play(3);
			}
			if(p.ici3.contains(e.getPoint()) && p.iceUp2 && resources >= cost){
				p.damage = 2;
				p.rate = 40;
				p.pla = 40;
				
				p.iceUp3 = true;
				p.ici3.currentState = sheet4.getSubimage(0, 210, 220, 68);
				resources -= cost;
				
				if(!p.showB){
					instructions4 = true;
				}
				s1.play(3);
			}
			if(closePUp.contains(e.getPoint())){
				pSelected = false;
			}
		}
		if(begin.contains(e.getPoint()) && gameState == 0){
			gameState = 1;
			theWave = new Wave(set[currentWave][0], set[currentWave][1], set[currentWave][2], set[currentWave][3], set[currentWave][4], sheet2);
			s1.play(3);
		}
		if(credit.contains(e.getPoint()) && gameState == 0){
			gameState = -1;
			s1.play(3);
		}
		if(gameState == 1 && !clickPause){
			if(p.lightningUp && !p.lightningUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 1){
				p.activateLight(e.getX());
			}
			if(p.fireUp && !p.fireUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 2){
				p.activateFire(e.getX());
			}
			if(p.iceUp && !p.iceUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 3){
				p.activateIce(e.getX());
			}
			if(p.fireUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 2){
				p.createFire(e.getX());
			}
			if(p.iceUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 3){
				p.createIce(e.getX());
			}
			if(p.lightningUp2 && p.rechargeBar.fullHp() && !instructions3 && !instructions4 && p.cycle == 1){
				p.createLight(e.getX());
			}
		}
		if(gameState == 2 && moveBack >= 717 && !clickPause && !sSelected & !pSelected){
			if(white.contains(e.getPoint())){
				gameState = 1;
			}
		}
		if(gameState == 1 && !instructions1){
			if(next1.contains(e.getPoint())){
				gameState = 2;
				instructions1 = true;
				s1.play(3);
			}
		}
		if(gameState == 2 && !instructions2){
			if(next2.contains(e.getPoint())){
				instructions2 = true;
				s1.play(3);
			}
		}
		if(gameState == 1 && instructions3){
			if(next3.contains(e.getPoint())){
				instructions3 = false;
				p.showA = true;
				s1.play(3);
			}
		}
		if(gameState == 1 && !instructions3 && instructions4){
			if(next4.contains(e.getPoint())){
				instructions4 = false;
				p.showB = true;
				s1.play(3);
			}
		}
		if(baseH.noHp() || theWave.waveDone() && currentWave == 9 && gameState == 1 && !instructions4 && !instructions3){
			if(goBack.contains(e.getPoint())){
				gameState = 0;
				for(int i = 0; i < 3; i++){
					turrets[i] = new Turret(weapon1, cover, 0);
					for(int j = 0; j < 6; j++){
						turrets[i].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[i].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[i].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
				}
				p = new Player(10, playerProjectile1, 2, 40, sheet3, s3);
				p.br.currentState = sheet4.getSubimage(0, 140, 280, 68);
				for(int i = 0; i < p.buttons2.length; i++){
					p.buttons2[i].currentState = sheet4.getSubimage(0, 487, 220, 74);
				}
				resources = 500;
				currentWave = 0;
				baseH.resetHP();
				instructions1 = false;
				instructions2 = false;
				instructions3 = false;
				instructions4 = false;
				moveBack = 717;
				s1.play(3);
			}
		}
		if(gameState == 1 || gameState == 2){
			if(pause.contains(e.getPoint()) && !sSelected && !pSelected){
				clickPause = true;
			}
		}
		if(clickPause){
			if(proceed.contains(e.getPoint())){
				clickPause = false;
			}
			if(pauseBack.contains(e.getPoint())){
				gameState = 0;
				for(int i = 0; i < 3; i++){
					turrets[i] = new Turret(weapon1, cover, 0);
					for(int j = 0; j < 6; j++){
						turrets[i].Bups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[i].Lups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
						turrets[i].Bomups[j].currentState = sheet4.getSubimage(0, 345, 140, 74);
					}
				}
				p = new Player(10, playerProjectile1, 2, 40, sheet3, s3);
				p.br.currentState = sheet4.getSubimage(0, 140, 280, 68);
				for(int i = 0; i < p.buttons2.length; i++){
					p.buttons2[i].currentState = sheet4.getSubimage(0, 487, 220, 74);
				}
				resources = 500;
				currentWave = 0;
				baseH.resetHP();
				instructions1 = false;
				instructions2 = false;
				instructions3 = false;
				instructions4 = false;
				clickPause = false;
				moveBack = 717;
				s1.play(3);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(white.contains(e.getPoint()) && !sSelected && !pSelected && !clickPause){
			wAppear = true;
		}
		if(!white.contains(e.getPoint())){
			wAppear = false;
		}
		if(sSelected){
			drawCost = false;
			if(costBox.contains(e.getPoint())){
				hoverSell = true;
			}
			if(!costBox.contains(e.getPoint())){
				hoverSell = false;
			}
			if(turrets[boxY].ba.contains(e.getPoint()) && turrets[boxY].ba.currentState == null){
				cX = e.getX();
				cY = e.getY();
				cost = 100;
				drawCost = true;
			}
			if(turrets[boxY].la.contains(e.getPoint()) && turrets[boxY].la.currentState == null){
				cX = e.getX();
				cY = e.getY();
				cost = 200;
				drawCost = true;
			}
			if(turrets[boxY].bo.contains(e.getPoint()) && turrets[boxY].bo.currentState == null){
				cX = e.getX();
				cY = e.getY();
				cost = 250;
				drawCost = true;
			}
			for(int i = 0; i < turrets[boxY].Bups.length; i++){
				if(turrets[boxY].Bups[i].contains(e.getPoint()) && turrets[boxY].Bups[i].currentState == null){
					cX = e.getX();
					cY = e.getY();
					drawCost = true;
					if(i == 0){
						cost = 100;
					}
					if(i == 1){
						cost = 450;
					}
					if(i == 2){
						cost = 900;
					}
					if(i == 3){
						cost = 150;
					}
					if(i == 4){
						cost = 300;
					}
					if(i == 5){
						cost = 750;
					}
				}
			}
			for(int i = 0; i < turrets[boxY].Lups.length; i++){
				if(turrets[boxY].Lups[i].contains(e.getPoint()) && turrets[boxY].Lups[i].currentState == null){
					cX = e.getX();
					cY = e.getY();
					drawCost = true;
					if(i == 0){
						cost = 300;
					}
					if(i == 1){
						cost = 750;
					}
					if(i == 2){
						cost = 1750;
					}
					if(i == 3){
						cost = 200;
					}
					if(i == 4){
						cost = 500;
					}
					if(i == 5){
						cost = 1000;
					}
				}
			}
			for(int i = 0; i < turrets[boxY].Bomups.length; i++){
				if(turrets[boxY].Bomups[i].contains(e.getPoint()) && turrets[boxY].Bomups[i].currentState == null){
					cX = e.getX();
					cY = e.getY();
					drawCost = true;
					if(i == 0){
						cost = 400;
					}
					if(i == 1){
						cost = 750;
					}
					if(i == 2){
						cost = 1500;
					}
					if(i == 3){
						cost = 200;
					}
					if(i == 4){
						cost = 600;
					}
					if(i == 5){
						cost = 1000;
					}
				}
			}
		}
		if(pSelected){
			drawCost = false;
			if(p.buttons1[1].contains(e.getPoint())){
				cX = e.getX();
				cY = e.getY();
				drawCost = true;
				cost = 100;
			}
			for(int i = 0; i < p.buttons2.length; i++){
				if(p.buttons2[i].contains(e.getPoint()) && p.buttons2[i].currentState == null){
					cX = e.getX();
					cY = e.getY();
					drawCost = true;
					if(i == 0){
						cost = 250;
					}
					if(i == 1){
						cost = 500;
					}
					if(i == 2){
						cost = 1000;
					}
					if(i == 3){
						cost = 300;
					}
					if(i == 4){
						cost = 600;
					}
					if(i == 5){
						cost = 1200;
					}
					if(i == 6){
						cost = 400;
					}
					if(i == 7){
						cost = 800;
					}
					if(i == 8){
						cost = 1200;
					}
				}
			}
		}
	}

}
