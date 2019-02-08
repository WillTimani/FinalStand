import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmallMonster extends Monster{

	public SmallMonster(BufferedImage b, int x, BufferedImage f, BufferedImage i){
		super(b, f, i);
		hB = new Rectangle(x, 595, 40, 50);
		h = new HealthBar(10, x, 575, 40, 10);
		speed = -5;
		damage = 1;
	}
	
}
