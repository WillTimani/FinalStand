import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Projectile {
	BufferedImage proj;
	public Rectangle hB;
	public double slope;
	private double direction;
	
	double moveX;
	double moveY;
	
	int type;
	
	AffineTransform a;
	Shape b; 
	
	public Projectile(BufferedImage p, double x, double y, double tX, double tY, int t, double s){
		//type 1 burning
		//type 2 slowed
		proj = p;
		//hB = new Rectangle(240 - (int)Math.pow(2, pos), 564 + (int)Math.pow(2, pos) + 2, 14, 12);
		hB = new Rectangle((int)x, (int)y, 13, 11);
		slope = (tY - y)/(tX - x);
		direction = Math.atan(slope); 
		if(slope > 1){
			slope = 1;
		}
		if(slope < -1){
			slope = -1;
		}

		moveX = ((s * Math.cos(direction)));
		moveY = ((s * Math.sin(direction)));
		if(moveY <= 0){
			moveY = 0;
		}
	
		type = t;
		
		a = AffineTransform.getTranslateInstance(moveX, moveY + 0.5);
		b = a.createTransformedShape(hB);
	
	}
	
	public void shootP(Graphics2D win){
		win.setColor(Color.CYAN);
		//win.fill(hB);
		
		//hB.translate(x, y);
		
		b = a.createTransformedShape(b);
		//win.fill(b);
		
		win.drawImage(proj, null, (int)b.getBounds().getX(), (int)b.getBounds().getY());
	}
	
	
	public Rectangle returnHB(){
		return b.getBounds();
	}
}
