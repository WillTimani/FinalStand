import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SpeedMonster extends Monster{
	
	public SpeedMonster(BufferedImage b, BufferedImage f, BufferedImage i){
		super(b, f, i);
		hB = new Rectangle(1200, 605, 63, 40);
		h = new HealthBar(15, 1200, 590, 60, 10);
		speed = -4;
		damage = 1;
	}
	
}
