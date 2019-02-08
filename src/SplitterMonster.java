import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SplitterMonster extends Monster{
	
	public SplitterMonster(BufferedImage b, BufferedImage f, BufferedImage i){
		super(b, f, i);
		hB = new Rectangle(1200, 595, 128, 50);
		h = new HealthBar(40, 1200, 580, 120, 10);
		speed = -2;
		damage = 2;
		canSplit = true;
	}
	

}
