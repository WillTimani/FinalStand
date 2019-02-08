import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LaserTurret extends Turret{
	ArrayList<LaserProjectile> projs = new ArrayList<LaserProjectile>();
	int c = 1;
	
	
	
	public LaserTurret(BufferedImage t, BufferedImage c, int pos){
		super(t, c, pos);
		type = 2;
		rate = 10;
	}
	
	public void shoot(Graphics2D win, BufferedImage p, double x, double x2, double x3, double x4, double x5){
		shootX = 10.0 - ((x - 225.0)/100.0);
		if(projs.size() == 0){
			projs.add(new LaserProjectile(p, 242.0, (double)(yPos + 28), x, 590.0));
		}
		if(speedUps[0]){
			c = 2;
		}
		if(speedUps[1]){
			c = 3;
		}
		if(speedUps[2]){
			c = 5;
		}
		if(this.damageUpB() == 2){
			rate = 8;
		}
		if(this.damageUpB() == 1){
			rate = 6;
		}
		if(this.damageUpB() == 0){
			rate = 4;
		}
		for(int i = 0; i < projs.size(); i++){
			projs.get(i).shootP(win, x + 10.0, 605.0, x2 + 10.0, x3 + 10.0, x4 + 10.0, x5 + 10.0, c, shootX);
		}
		theta = (560.0 - (double)(yPos + 21))/(x - 225.0);
	}
}
