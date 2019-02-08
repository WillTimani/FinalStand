import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class TankMonster extends Monster{
	
	public TankMonster(BufferedImage b, BufferedImage f, BufferedImage i){
		super(b, f, i);
		hB = new Rectangle(1200, 565, 128, 70);
		h = new HealthBar(120, 1200, 550, 120, 10);
		speed = -1;
		damage = 3;
		
		defaultSpeed = -1;
		
		h.two = true;
		
	}
}
