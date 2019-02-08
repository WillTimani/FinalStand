import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Animation {
	
	BufferedImage[] images;
	private int rows;
	private int columns;
	private int posX;
	private int posY;
	private boolean appear;
	
	Timer t;
	
	Animation(BufferedImage sheet, int r, int c, int time) {
		rows = r; 
		columns = c;
		t = new Timer(time);
		
		int width = sheet.getWidth();
		int height = sheet.getHeight();
		
		images = new BufferedImage[rows * columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				images[(j * rows) + i] = sheet.getSubimage(i * (width/rows), j * (height/columns), width/rows, height/columns);
			}
		}
	}
	
	public void moveAndDraw(Graphics2D win){
		if(appear){
			t.startTimer();
			win.drawImage(images[(rows * columns - (t.getFrames() - (t.getOFrames() - rows * columns)))], null, posX, posY);
			if(t.getFrames() < (2 + t.getOFrames() - rows * columns)){
				t.resetTimer();
				appear = false;
			}
		}
	}
	
	public void changeA(boolean b, int x, int y){
		appear = false;
		t.resetTimer();
		appear = b;
		posX = x;
		posY = y;
	}
	
	public void remainA(){
		if(appear == false){
			appear = true;
			t.resetTimer();
		}
	}
	
	public void addPos(int x, int y){
		posX += x;
		posY += y;
	}
	
	public void setPos(int x, int y){
		posX = x;
		posY = y;
	}
	
	
}





