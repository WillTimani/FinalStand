import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Turret {
	//SoundDriver s1;
	
	BufferedImage turr;
	BufferedImage cov;
	
	int xPos;
	int yPos;
	int time = 0;
	int type;
	
	double shootX;
	double shootY;
	
	int rate = 45;
	int damage = 3;
	
	int turretValue = 0;
	
	double theta = 0;
	
	Rectangle hB;
	
	Button ba = new Button(200, 128, 275, 60);
	Button la = new Button(487, 128, 275, 60);
	Button bo = new Button(775, 128, 275, 60);
	
	Button bup11 = new Button(213, 232, 80, 64);
	Button bup12 = new Button(213, 351, 80, 64);
	Button bup13 = new Button(213, 471, 80, 64);
	Button bup21 = new Button(359, 232, 80, 64);
	Button bup22 = new Button(359, 352, 80, 64);
	Button bup23 = new Button(358, 468, 80, 64);
	
	Button lup11 = new Button(502, 231, 80, 64);
	Button lup12 = new Button(502, 351, 80, 64);
	Button lup13 = new Button(502, 471, 80, 64);
	Button lup21 = new Button(650, 232, 80, 64);
	Button lup22 = new Button(650, 351, 80, 64);
	Button lup23 = new Button(650, 468, 80, 64);
	
	Button bomup11 = new Button(785, 231, 80, 64);
	Button bomup12 = new Button(785, 351, 80, 64);
	Button bomup13 = new Button(785, 471, 80, 64);
	Button bomup21 = new Button(933, 232, 80, 64);
	Button bomup22 = new Button(932, 352, 80, 64);
	Button bomup23 = new Button(932, 468, 80, 64);
	
	Button[] Bups = {bup11, bup12, bup13, bup21, bup22, bup23};
	Button[] Lups = {lup11, lup12, lup13, lup21, lup22, lup23};
	Button[] Bomups = {bomup11, bomup12, bomup13, bomup21, bomup22, bomup23};
	
	ArrayList<Projectile> projs = new ArrayList<Projectile>();
	
	boolean[] speedUps = new boolean[3];
	boolean[] damageUps = new boolean[3];
	
	AffineTransform a = new AffineTransform();
	AffineTransformOp aO;
	
	public Turret(BufferedImage t, BufferedImage c, int pos){
		turr = t;
		cov = c;
		xPos = 187;
		if(pos == 1){
			yPos = 451;
		} else if(pos == 2){
			yPos = 510;
		} else if(pos == 3){
			yPos = 567;
		}
		hB = new Rectangle(xPos, yPos, 50, 55);
		type = 1;
		if(yPos == 0){
			type = 0;
		}
		
		for(int i = 0; i < 3; i++){
			speedUps[i] = false;
			damageUps[i] = false;
		}
		
		shootX = 225.0;
		shootY = yPos + 21.0;
		
	}
	
	public void moveAndDraw(Graphics2D win, int m){
		win.setColor(Color.CYAN);
		//win.fill(hB);
		int temp = 0;
		if(type == 3){
			temp = -50;
			theta = 0;
		}
		if(yPos == 567){
			theta = 0;
		}
		if(theta <= 1){
			a = AffineTransform.getRotateInstance(theta, 0, 0);
		}
		if(this.speedUpB() == 2){
			rate = 35;
		}
		if(this.speedUpB() == 1){
			rate = 25;
		}
		if(this.speedUpB() == 0){
			rate = 15;
		}
		if(this.damageUpB() == 2){
			damage = 4;
		}
		if(this.damageUpB() == 1){
			damage = 6;
		}
		if(this.damageUpB() == 0){
			damage = 9;
		}
		if(yPos != 0){
			aO = new AffineTransformOp(a, AffineTransformOp.TYPE_BILINEAR);
			win.drawImage(aO.filter(turr,null), 195 + m + temp, yPos + 20, null);
			win.drawImage(cov, null, 187 + m, yPos);
		}
		
	}
	
	public void shoot(Graphics2D win, BufferedImage p, double x, SoundDriver s){
		time++;
		shootX = 10.0 - ((x - 225.0)/100.0);
		shootY = 10.0 -((x - 225.0)/100.0);
		if(shootX < 0){
			shootX = 0;
		}
		if(shootY < 00){
			shootY = 0;
		}
		if(time == rate && yPos != 0){
			projs.add(new Projectile(p, 225.0 - shootX, (double)(yPos + 21) + shootX, x, 590.0, 0, 10.0));
			if(yPos == 567){
				projs.get(projs.size() - 1).slope = 0;
			}
			time = 0;
			s.play(1);
		}
		theta = (560.0 - (double)(yPos + 21))/(x - 225.0);
	
	}
	
	public ArrayList<Projectile> returnA(){
		return projs;
	}
	
	public void deleteProj(int x){
		projs.remove(x);
	}
	
	public int speedUpB(){
		int result = 0;
		for(int i = 0; i < 3; i++){
			if(speedUps[i] == false){
				result++;
			}
		}
		return result;
	}
	
	public int damageUpB(){
		int result = 0;
		for(int i = 0; i < 3; i++){
			if(damageUps[i] == false){
				result++;
			}
		}
		return result;
	}
	
	
	
	
}
