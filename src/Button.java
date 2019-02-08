import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Button extends Rectangle{
	BufferedImage currentState; 
	
	public Button(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void moveAndDraw(Graphics2D win){
		if(currentState != null){
			win.drawImage(currentState, null, (int)super.getX(), (int)super.getY());
		}
	}
	
	
}
