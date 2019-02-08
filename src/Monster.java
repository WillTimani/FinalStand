import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Monster {
	BufferedImage mons;
	BufferedImage burnmons;
	BufferedImage icemons;
	BufferedImage dmons;
	Rectangle hB;
	
	HealthBar h;
	
	int hitTime = 0;
	int damage;
	int speed;
	int defaultSpeed;
	
	int burnAmount;
	int slowAmount;
	
	boolean canSplit;
	
	boolean burning = false;
	int burnTime = 0;
	boolean slowed = false;
	int slowTime = 0;
	
	boolean boss = false;
	int bossTime = 0;
	
	public Monster(BufferedImage m, BufferedImage bm, BufferedImage im){
		mons = m;
		dmons = m;
		burnmons = bm;
		icemons = im;
		hB = new Rectangle(1200, 595, 110, 40);
		speed = -2;
		defaultSpeed = speed;
		damage = 2;
		h = new HealthBar(25, 1200, 575, 100, 10);
		canSplit = false;
	}
	
	public void moveMons(Graphics2D win){
		win.setColor(Color.CYAN);
		//win.fill(hB);
		if(hB.getX() >= 230){
			if(!boss){
				hB.translate(speed, 0);
				h.translate(speed, 0);
			}
			bossTime++;
			if(boss && bossTime % 3 == 0){
				hB.translate(-1, 0);
			}
			
		}
		if(burning){
			burnTime--;
			if(burnTime % 5 == 0){
				h.loseH(burnAmount);
			}
			if(burnTime <= 0){
				burning = false;
				mons = dmons;
			}
		}
		if(slowed){
			slowTime--;
			speed = slowAmount;
			if(slowTime <= 0){
				slowed = false;
				speed = defaultSpeed;
				mons = dmons;
			}
		}
		win.drawImage(mons, null, (int)hB.getX(), (int)hB.getY() - 5);
		h.moveAndDraw(win);
		hitTime++;
	}
	
	public int returnPos(){
		return (int)hB.getX();
	}
	
	public Rectangle returnHB(){
		return hB;
	}
	
	public HealthBar returnHealth(){
		return h;
	}
	
	public void burn(int t, int d){
		burnTime = t;
		burning = true;
		burnAmount = d;
		mons = burnmons;
	}
	
	public void slow(int t, int d){
		slowTime = t;
		//defaultSpeed = speed;
		slowed = true;
		slowAmount = d;
		mons = icemons;
		if(boss){
			slowTime = 20;
		}
	}
}
