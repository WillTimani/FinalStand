import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BombProjectile extends Projectile{
	int t = 60;
	double distance;
	boolean explode;
	int cluster;
	
	public BombProjectile(BufferedImage p, double x, double y, double tX, double tY, int c){
		super(p, x, y, tX, tY, 0, 10.0);
		hB = new Rectangle((int)x - 20, (int)y, 20, 20);
		explode = false;
		distance = tX - x;
		cluster = c;
		//double time = (tX - x) / moveX;
		moveY = (-92/(4))/2;
		//moveX = 3; 
		//System.out.println(moveX);
		moveX = (distance + 20)/100 + 1.0;
		
		if(c == 1){
			moveY = -8;
			moveX = 2.5;
		}
		if(c == 2){
			moveY = -8;
			moveX = -2.5;
		}
		
		a = AffineTransform.getTranslateInstance(moveX, moveY + 0.5);
		b = a.createTransformedShape(hB);
		
	}
	
	public void shootP(Graphics2D win){
		win.setColor(Color.CYAN);
		//win.fill(b);
		//System.out.println(moveX);
		a = AffineTransform.getTranslateInstance(moveX, moveY + 0.5);
		win.drawImage(proj, null, (int)b.getBounds().getX() + 5, (int)b.getBounds().getY() - 10);
		if(b.getBounds().getY() <= 595){
			b = a.createTransformedShape(b);
		} else {
			t--;
			if(t <= 0){
				explode = true;
			}
		}

		//hB.translate((int)moveX, (int)moveY);
		moveY+= 0.327; 
	
		
	}
	
	public boolean explode(){
		return explode;
	}
}
