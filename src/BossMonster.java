import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BossMonster extends Monster{
	
	public BossMonster(BufferedImage b, BufferedImage f, BufferedImage i){
		super(b, f, i);
		hB = new Rectangle(1200, 315, 200, 375);
		h = new HealthBar(675, 350, 15, 675, 15);
		speed = -1;
		defaultSpeed = -1;
		damage = 15;
		
		boss = true;
		h.two = true;
		h.three = true;
	}
}
