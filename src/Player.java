import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player{
	SoundDriver s1;
	
	BufferedImage activePlayer;
	
	BufferedImage playerB;
	BufferedImage playerF;
	
	BufferedImage proj;
	
	int time = 0;
	int o1 = 255;
	int o2 = 255;
	
	int damage;
	int rate;
	int pla = 10;
	
	int lxPos;
	
	boolean activateL = false;
	boolean activateL2 = false;
	boolean activateL3 = false;
	
	boolean activateI = false;
	boolean activateF = false;
	
	boolean lightningUp = false;
	boolean lightningUp2 = false;
	boolean lightningUp3 = false;
	
	boolean fireUp = false;
	boolean fireUp2 = false;
	boolean fireUp3 = false;
	
	boolean iceUp = false;
	boolean iceUp2 = false;
	boolean iceUp3 = false;
	
	int stormTime = 0;
	int moveCloud = 100;
	
	int iceTime = 45;
	int iceSpeed = -1;
	int fireTime = 30;
	int fireDamage = 1;
	int lightningDamage = 15;
	
	boolean fixedUp = false;
	boolean showA = false;
	boolean showB = false;
	
	int cycle = 1; 
	
	Rectangle flash = new Rectangle(0, 0, 1200, 780);
	Rectangle lightHB = new Rectangle(0, 0, 50, 15);

	Button br = new Button(490, 119, 275, 60);
	Button fx = new Button(490, 199, 275, 60);
	Button blt1 = new Button(250, 339, 200, 60);
	Button blt2 = new Button(250, 444, 200, 60);
	Button blt3 = new Button(250, 555, 200, 60);
	Button bal1 = new Button(523, 339, 200, 60);
	Button bal2 = new Button(523, 444, 200, 60);
	Button bal3 = new Button(523, 555, 200, 60);
	Button ici1 = new Button(800, 339, 200, 60);
	Button ici2 = new Button(800, 444, 200, 60);
	Button ici3 = new Button(800, 555, 200, 60);
	
	Button[] buttons1 = {br, fx};
	Button[] buttons2 = {blt1, blt2, blt3, bal1, bal2, bal3, ici1, ici2, ici3};
	
	ArrayList<Projectile> projs = new ArrayList<Projectile>();
	
	BufferedImage sheet;
	BufferedImage lightning;
	BufferedImage fire;
	BufferedImage ice;
	
	BufferedImage bigFire;
	BufferedImage bigIce;
	
	BufferedImage cloud;
	
	HealthBar rechargeBar = new HealthBar(60, 10, 10, 300, 75);
	
	public Player(int p, BufferedImage pr, int d, int r, BufferedImage she, SoundDriver s){
		proj = pr;
		damage = d;
		rate = r;
		sheet = she;
		lightning = sheet.getSubimage(950, 0, 175, 675);
		fire = sheet.getSubimage(97, 150, 22, 25); 
		ice = sheet.getSubimage(94, 297, 21, 25);
		bigFire = sheet.getSubimage(119, 150, 50, 50);
		bigIce = sheet.getSubimage(115, 297, 50, 50);
		cloud = sheet.getSubimage(585, 0, 340, 130);
		
		rechargeBar.c1 = Color.BLUE;
		
		s1 = s;
	}
	
	public void moveAndDraw(Graphics2D win, int x){
		if(pla == 10){
			activePlayer = sheet.getSubimage(0, 0, 70, 150);
		}
		if(pla == 11){
			activePlayer = sheet.getSubimage(90, 0, 74, 150);
		}
		if(pla == 20){
			activePlayer = sheet.getSubimage(0, 444, 76, 150);
		}
		if(pla == 30){
			activePlayer = sheet.getSubimage(0, 147, 75, 150);
		}
		if(pla == 40){
			activePlayer = sheet.getSubimage(0, 297, 75, 148);
		}
		if(fireUp2){
			fireDamage = 2;
			fireTime = 60;
		}
		if(iceUp2){
			iceTime = 90;
			iceSpeed = 0;
		}
		if(lightningUp2){
			lightningDamage = 30;
		}
		win.drawImage(activePlayer, null, 80 + x, 310);
		rechargeBar.gainH(1);
		if(activateL){
			if(o1 > 150){
				win.drawImage(lightning, null, lxPos - 95, -45);
				//win.setColor(Color.CYAN);
				//win.fill(lightHB);
			}
			Color c = new Color(255, 255, 255, o1);
			win.setColor(c);
			win.fill(flash);
			o1-= 8;
			if(o1 < 1){
				activateL = false;
				o1 = 255;
				lightHB.move(0, 0);
			}
		}
		if(activateL2){
			if(o2 < 220){
				activateL = true;
			}
			if(o2 > 150){
				win.drawImage(lightning, null, lxPos - 35, -45);
				//win.setColor(Color.CYAN);
				//win.fill(lightHB);
			}
			Color c = new Color(255, 255, 255, o2);
			win.setColor(c);
			win.fill(flash);
			o2-= 12;
			if(o2 < 1){
				activateL2 = false;
				o2 = 255;
				lightHB.move(0, 0);
			}
		}
		if(activateL3){
			if(stormTime > 0){
				stormTime--;
				if(moveCloud > 0){
					moveCloud-= 5;
				}
				if(stormTime < 20){
					moveCloud += 10;
				}
				win.drawImage(cloud, null, 200, -50 - moveCloud);
				win.drawImage(cloud, null, 500, -50 - moveCloud);
				win.drawImage(cloud, null, 800, -50 - moveCloud);
				if(stormTime % 25 == 0){
					this.activateLight((int)(Math.random() * 1000) + 200);
				}
			} else {
				activateL3 = false;
			}
		}
		if(activateI){
			if(stormTime > 0){
				stormTime--;
				if(moveCloud > 0){
					moveCloud-= 5;
				}
				if(stormTime < 20){
					moveCloud += 10;
				}
				win.drawImage(cloud, null, 200, -50 - moveCloud);
				win.drawImage(cloud, null, 500, -50 - moveCloud);
				win.drawImage(cloud, null, 800, -50 - moveCloud);
				if(stormTime % 15 == 0){
					double temp = (Math.random() * 1000) + 200.0;
					
					projs.add(new Projectile(bigIce, temp, 0.0, temp + 100.0, 590.0, 2, 20.0));
				}
			} else {
				activateI = false;
			}
		}
		if(activateF){
			if(stormTime % 30 == 0){
				s1.play(3);
				projs.add(0, new BombProjectile(bigFire, 150.0, 305.0, 1000.0, 590.0, 0));
				projs.add(0, new BombProjectile(bigFire, 150.0, 305.0, 700.0, 590.0, 0));
				projs.add(0, new BombProjectile(bigFire, 150.0, 305.0, 400.0, 590.0, 0));
				
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 1100.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 1150.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 1200.0, 590.0, 0));
				
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 500.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 550.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 600.0, 590.0, 0));
				
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 800.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 850.0, 590.0, 0));
				projs.add(0, new BombProjectile(fire, 150.0, 305.0, 900.0, 590.0, 0));
				for(int i = 0; i < 12; i++){
					projs.get(i).type = 1;
				}
			}
			stormTime--;
			if(stormTime < 0){
				activateF = false;
			}
		}
	}
	
	public void shoot(double x, SoundDriver s){
		time++;
		if(time % rate == 0){
			s.play(2);
			projs.add(new Projectile(proj, 120.0, 305.0, x, 590.0, 0, 10.0));
		}
	}
	
	public void activateLight(int x){
		if(lightningUp && rechargeBar.fullHp()){
			activateL = true;
			lxPos = x;
			lightHB.move(x - 25, 610);
			o1 = 200;
			if(!activateL3){
				rechargeBar.zeroHP();
			}
			s1.play(2);
		}
	}
	
	public void activateFire(int x){
		if(fireUp && rechargeBar.fullHp()){
			projs.add(new Projectile(fire, 150.0, 305.0, x, 590.0, 1, 20.0));
			rechargeBar.zeroHP();
			s1.play(3);
		}
	}
	
	public void activateIce(int x){
		if(iceUp && rechargeBar.fullHp()){
			projs.add(new Projectile(ice, 150.0, 305.0, x, 590.0, 2, 20.0));
			rechargeBar.zeroHP();
		}
	}
	
	public void createLight(int x){
		if(lightningUp2 && rechargeBar.fullHp()){
			activateL2 = true;
			lxPos = x;
			lightHB.move(x - 25, 610);
			o2 = 200;
			rechargeBar.zeroHP();
			s1.play(2);
		}
	}
	
	public void createFire(int x){
		if(fireUp2 && rechargeBar.fullHp()){
			projs.add(new Projectile(bigFire, 150.0, 305.0, x, 590.0, 1, 20.0));
			rechargeBar.zeroHP();
			s1.play(3);
		}
	}
	

	public void createIce(int x){
		if(iceUp2 && rechargeBar.fullHp()){
			projs.add(new Projectile(bigIce, 150.0, 305.0, x, 590.0, 2, 20.0));
			rechargeBar.zeroHP();
		}
	}
	

	public void masterLight(){
		if(lightningUp3){
			activateL3 = true;
			stormTime = 400;
		}
	}
	
	public void masterFire(){
		if(fireUp3){
			activateF = true;
			stormTime = 90;
			//s1.play(1);
		}
	}
	

	public void masterIce(){
		if(iceUp3){
			activateI = true;
			stormTime = 400;
		}
	}
	
	public void cycle(){
		boolean temp = false;
		if(!temp){
			if(cycle == 1 && fireUp){
				cycle = 2;
				pla = 30;
				temp = true;
			}
			if(cycle == 1 && !fireUp && iceUp){
				cycle = 3;
				pla = 40;
				temp = true;
			}
		}
		if(!temp){
			if(cycle == 2 && iceUp){
				cycle = 3;
				pla = 40;
				temp = true;
			}
			if(cycle == 2 && !iceUp && lightningUp){
				cycle = 1;
				pla = 20;
				temp = true;
			}
		}
		if(!temp){
			if(cycle == 3 && lightningUp){
				cycle = 1;
				pla = 20;
				temp = true;
			}
			if(cycle == 3 && !lightningUp && fireUp){
				cycle = 2;
				pla = 30;
				temp = true;
			}
		}

	}
}
