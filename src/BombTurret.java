import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BombTurret extends Turret{
	
	ArrayList<BombProjectile> bProjs = new ArrayList<BombProjectile>();
	
	public BombTurret(BufferedImage t, BufferedImage c, int pos){
		super(t, c, pos);
		type = 3;
		rate = 175;
		damage = 7;
	}
	
	public void shoot(Graphics2D win, BufferedImage p, double x, SoundDriver s){
		time++;
		if(this.speedUpB() == 2){
			rate = 225;
		}
		if(this.speedUpB() == 1){
			rate = 300;
		}
		if(this.speedUpB() == 0){
			rate = 300;
		}
		if(this.damageUpB() == 2){
			damage = 12;
		}
		if(this.damageUpB() == 1){
			damage = 18;
		}
		if(this.damageUpB() == 0){
			damage = 25;
		}
		if(time % 150 == 0){
			s.play(2);
			bProjs.add(new BombProjectile(p, 235.0, (double)(yPos), x, 595.0, 0));
		}
		/*if(bProjs.get(0).b.getBounds2D().getY() > 800){
			bProjs.remove(0);
		}*/
		theta = (560.0 - (double)(yPos + 21))/(x - 225.0);
	
	}
}

