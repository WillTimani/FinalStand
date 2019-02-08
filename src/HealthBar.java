import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class HealthBar {
	private Rectangle[] r;
	private Rectangle[] r1;
	private Rectangle[] r2;
 	
	private boolean[] rS;
	private boolean[] rS1;
	private boolean[] rS2; 
	
	Rectangle border;
	
	//Timer t = new Timer(2);
	Color c1 = Color.GREEN;
	Color c2 = Color.RED;
	
	Color c3 = Color.ORANGE;
	Color c4 = Color.PINK;
	
	boolean two = false;
	boolean three = false;
	
	int length;
	int num;
	
	public HealthBar(int n, int x, int y, int l, int h){
		r = new Rectangle[n];
		rS = new boolean[n];
		r1 = new Rectangle[n];
		rS1 = new boolean[n];
		r2 = new Rectangle[n];
		rS2 = new boolean[n];
		
		border = new Rectangle(x - 1, y - 1, l + 2, h + 2);
		
		length = l;
		num = n;
		
		for(int i = 0; i < n; i++){
			r[i] = new Rectangle(x + i * (l/n), y, (l/n), h);
			rS[i] = true;
			r1[i] = new Rectangle(x + i * (l/n), y, (l/n), h);
			rS1[i] = true;
			r2[i] = new Rectangle(x + i * (l/n), y, (l/n), h);
			rS2[i] = true;
		}
	}
	
	public void moveAndDraw(Graphics2D win){
		win.setColor(Color.black);
		win.fill(border);
		for(int i = 0; i < r.length; i++){
			if(rS[i]){
				win.setColor(c1);
				win.fill(r[i]);
			}
			else{
				win.setColor(c2);
				win.fill(r[i]);
			}
			if(rS1[i] && two){
				win.setColor(c3);
				win.fill(r1[i]);
			}
			if(rS2[i] && three){
				win.setColor(c4);
				win.fill(r2[i]);
			}
		}
		//t.startTimer();
	}
	
	public void translate(int x, int y){
		for(int i = 0; i < r.length; i++){
			r[i].translate(x, y);
			r1[i].translate(x, y);
			r2[i].translate(x, y);
		}
		border.translate(x, y);
	}
	
	public void move(int x, int y){
		for(int i = 0; i < num; i++){
			r[i].move(x + i * (length/num), y);
			r1[i].move(x + i * (length/num), y);
			r2[i].move(x + i * (length/num), y);
		}
		border.move(x - 1, y - 1);
	}
	
	public void loseH(int h){
		if(!two && !three){
			for(int i = r.length - 1; i >= 0; i--){
				if(rS[i] && h > 0){
					rS[i] = false;
					h--;
				}
			}
		} else if(!three && two){
			for(int i = r1.length - 1; i >= 0; i--){
				if(rS1[i] && h > 0){
					rS1[i] = false;
					h--;
				}
			}
			if(!rS1[0]){
				two = false;
			}
		} else {
			for(int i = r1.length - 1; i >= 0; i--){
				if(rS2[i] && h > 0){
					rS2[i] = false;
					h--;
				}
			}
			if(!rS2[0]){
				three = false;
			}
		}
	}
	
	public void gainH(int h){
		for(int i = 0; i < r.length; i++){
			if(!rS[i] && h > 0){
				rS[i] = true;
				h--;
			}
		}
	}
	
	
	public boolean noHp(){
		for(int i = r.length - 1; i >= 0; i--){
			if(rS[i]){
				return false;
			}
		}
		if(two){
			for(int i = r1.length - 1; i >= 0; i--){
				if(rS1[i]){
					return false;
				}
			}
		}
		if(three){
			for(int i = r1.length - 1; i >= 0; i--){
				if(rS2[i]){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean fullHp(){
		for(int i = r.length - 1; i >= 0; i--){
			if(!rS[i]){
				return false;
			}
		}
		return true;
	}
	
	public void resetHP(){
		for(int i = 0; i < r.length; i++){
			rS[i] = true;
		}
	}
	
	public void zeroHP(){
		for(int i = 0; i < r.length; i++){
			rS[i] = false;
		}
	}
}
