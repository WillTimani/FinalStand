import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class MonsterBasic {
	
	BufferedImage[] images;
	private int rows;
	private int columns;
	private int posX;
	private int posY;
	private int temp;
	private boolean move;
	private boolean appear;
	
	Timer t;
	
	MonsterBasic(BufferedImage sheet, int r, int c, int time, int w, int h) {
		rows = r; 
		columns = c;
		posX = 800;
		posY = 300;
		t = new Timer(2);
		
		temp = 0;
		move = false;
		
		int width = w;
		int height = h;
		
		images = new BufferedImage[rows * columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				images[(j * rows) + i] = sheet.getSubimage(0, height * i + 2, width, height);
			}
		}
	}
	
	public void moveAndDraw(Graphics2D win){
		t.startTimer();
		if(move){
			temp = 4 - t.getFrames()/12;
			move = false;
		}
		win.drawImage(images[temp], null, posX, posY);
		if(t.getFrames() <= 0){
			t.resetTimer();
		}
	}
	
	public void addPos(int x, int y){
		posX += x;
		posY += y;
		move = true;
	}
	
	public void setPos(int x, int y){
		posX = x;
		posY = y;
	}
	
	public int getXPos(){
		return posX;
	}
	
	
}





