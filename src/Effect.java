import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Effect {
	Rectangle[] eff = new Rectangle[300];
	double gravity = 1;
	int time = 0;
	
	
	public Effect(int x, int y){
		for(int i = 0; i < 30; i++){
			for(int j = 0; j < 10; j++){
				if(i < 3 && j > 7 || i > 27 && j > 7){
					eff[i * 10 + j] = new Rectangle(-20, 0, 3, 3);
				} else {
					eff[i * 10 + j] = new Rectangle(x + i * 4, y - 5 + j * 3, 3, 3);
				}
			}
			
		}
	}
	
	public void moveAndDraw(Graphics2D win){
		win.setColor(Color.DARK_GRAY);
		for(int i = 0; i < 300; i++){
			win.fill(eff[i]);
			if(eff[i].getY() < 625){
				eff[i].translate(0, (int)gravity);
			}
			time++;
			if(time % 30 == 0){
				gravity+= 0.3;
			}
		}
		
	}
}
