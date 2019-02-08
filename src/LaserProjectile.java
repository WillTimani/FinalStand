import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class LaserProjectile extends Projectile{
	Line2D.Double l1;
	Line2D.Double l2;
	Line2D.Double l3;
	Line2D.Double l4;
	Line2D.Double l5;
	Line2D.Double l6;
	Line2D.Double l7;
	
	Line2D.Double al1;
	Line2D.Double bl1;
	Line2D.Double cl1;
	Line2D.Double dl1;
	
	Line2D.Double[] lines = {al1, bl1, cl1, dl1};
	
	double currX;
	double currY;
	
	boolean moving;
	
	public LaserProjectile(BufferedImage b, double x, double y, double tX, double tY){
		super(b, x, y, tX, tY, 0, 10.0);
		currX = x;
		currY = y;
		l1 = new Line2D.Double(currX,currY,1000,tY);
		l2 = new Line2D.Double(currX,currY - 1,1000,tY);
		l3 = new Line2D.Double(currX,currY - 2,1000,tY);
		l4 = new Line2D.Double(currX,currY - 3,1000,tY);
		l5 = new Line2D.Double(currX,currY + 1,1000,tY);
		l6 = new Line2D.Double(currX,currY + 2,1000,tY);
		l7 = new Line2D.Double(currX,currY + 3,1000,tY);
	}
	
	public void shootP(Graphics2D win, double tX, double tY, double tX2, double tX3, double tX4, double tX5, int c, double move){
		win.setColor(Color.GREEN);
		if(l1.getX2() < tX){
			l1 = new Line2D.Double(currX - move,currY + move,l1.getX2() + 7,tY);
			l2 = new Line2D.Double(currX - move,currY - 1 + move,l1.getX2() + 7,tY);
			l3 = new Line2D.Double(currX - move,currY - 2 + move,l1.getX2() + 7,tY);
			l4 = new Line2D.Double(currX - move,currY - 3 + move,l1.getX2() + 7,tY);
			l5 = new Line2D.Double(currX - move,currY + 1 + move,l1.getX2() + 7,tY);
			l6 = new Line2D.Double(currX - move,currY + 2 + move,l1.getX2() + 7,tY);
			l7 = new Line2D.Double(currX - move,currY + 3 + move,l1.getX2() + 7,tY);
			moving = true;
		} else {
			l1 = new Line2D.Double(currX - move,currY + move,tX,tY);
			l2 = new Line2D.Double(currX - move,currY - 1 + move,tX,tY);
			l3 = new Line2D.Double(currX - move,currY - 2 + move,tX,tY);
			l4 = new Line2D.Double(currX - move,currY - 3 + move,tX,tY);
			l5 = new Line2D.Double(currX - move,currY + 1 + move,tX,tY);
			l6 = new Line2D.Double(currX - move,currY + 2 + move,tX,tY);
			l7 = new Line2D.Double(currX - move,currY + 3 + move,tX,tY);
			moving = false;
		}
		if(c == 2 && moving == false){
			this.shootP(win, l1.getX2(), l1.getY2(), tX2, tY, 0);
		} 
		if(c == 3 && moving == false){
			this.shootP(win, l1.getX2(), l1.getY2(), tX2, tY, 0);
			this.shootP(win, lines[0].getX2(), lines[0].getY2(), tX3, tY, 1);
		}
		if(c == 5 && moving == false){
			this.shootP(win, l1.getX2(), l1.getY2(), tX2, tY, 0);
			this.shootP(win, lines[0].getX2(), lines[0].getY2(), tX3, tY, 1);
			this.shootP(win, lines[1].getX2(), lines[1].getY2(), tX4, tY, 2);
			this.shootP(win, lines[2].getX2(), lines[2].getY2(), tX5, tY, 3);
		}
		win.setColor(Color.GREEN);
		win.draw(l1);
		win.draw(l2);
		win.draw(l3);
	
		win.draw(l5);
		win.draw(l6);
		
		win.setColor(Color.BLACK);
		win.draw(l7);
		win.draw(l4);

	}
	
	public void shootP(Graphics2D win, double x, double y, double tX, double tY, int n){
		lines[n] = new Line2D.Double(x,y,tX,tY);
		Line2D.Double al2 = new Line2D.Double(x,y - 1,tX,tY);
		Line2D.Double al3 = new Line2D.Double(x,y - 2,tX,tY);
		Line2D.Double al4 = new Line2D.Double(x,y - 3,tX,tY);
		Line2D.Double al5 = new Line2D.Double(x,y + 1,tX,tY);
		Line2D.Double al6 = new Line2D.Double(x,y + 2,tX,tY);
		Line2D.Double al7 = new Line2D.Double(x,y + 3,tX,tY);
		
		win.setColor(Color.GREEN);
		win.draw(lines[n]);
		win.draw(al2);
		win.draw(al3);
		win.draw(al5);
		win.draw(al6);
		
		win.setColor(Color.BLACK);
		win.draw(al7);
		win.draw(al4);
		
	}

}
