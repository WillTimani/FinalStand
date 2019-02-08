import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Wave{
	BufferedImage sheet;
	BufferedImage basicM;
	BufferedImage speedM;
	BufferedImage tankM;
	BufferedImage splitM;
	BufferedImage smallM;
	BufferedImage bossM;
	
	BufferedImage BbasicM;
	BufferedImage BspeedM;
	BufferedImage BtankM;
	BufferedImage BsplitM;
	BufferedImage BsmallM;
	BufferedImage BbossM;
	
	BufferedImage IbasicM;
	BufferedImage IspeedM;
	BufferedImage ItankM;
	BufferedImage IsplitM;
	BufferedImage IsmallM;
	BufferedImage IbossM;
	
	int countB;
	int countS;
	int countT;
	int countSp;
	int countBb;
	
	int time = 0;
	
	boolean usedMaster = false;
	boolean bossDay = false;
	
	ArrayList<Effect> effects = new ArrayList<Effect>();
	
	ArrayList<Monster> mons = new ArrayList<Monster>();
	
	public Wave(int b, int s, int t, int sp, int bB, BufferedImage she){
		sheet = she;
		basicM = sheet.getSubimage(0, 0, 120, 45);
		speedM = sheet.getSubimage(125, 0, 73, 37);
		tankM = sheet.getSubimage(195, 0, 138, 73);
		splitM = sheet.getSubimage(333, 0, 125, 49);
		smallM = sheet.getSubimage(455, 0, 36, 38);
		bossM = sheet.getSubimage(492, 0, 265, 345);
		
		BbasicM = sheet.getSubimage(0, 46, 120, 43);
		BspeedM = sheet.getSubimage(125, 37, 72, 37);
		BtankM = sheet.getSubimage(195, 74, 138, 73);
		BsplitM = sheet.getSubimage(333, 50, 125, 49);
		BsmallM = sheet.getSubimage(455, 39, 36, 38);
		BbossM = sheet.getSubimage(757, 0, 265, 345);
		
		IbasicM = sheet.getSubimage(0, 89, 120, 45);
		IspeedM = sheet.getSubimage(125, 75, 72, 37);
		ItankM = sheet.getSubimage(195, 148, 138, 75);
		IsplitM = sheet.getSubimage(333, 100, 125, 49);
		IsmallM = sheet.getSubimage(455, 78, 36, 38);
		IbossM = sheet.getSubimage(492, 345, 265, 345);
		
		countB = b;
		countS = s;
		countT = t;
		countSp = sp;
		countBb = bB;
		
		if(bB >= 1){
			bossDay = true;
		}
	}
	
	public void startWave(Graphics2D win){
		time++;
		if(time % 80 == 0 && countB > 0){
			mons.add(new Monster(basicM, BbasicM, IbasicM));
			countB--;
		}
		if(time % 40 == 0 && countS > 0){
			mons.add(new SpeedMonster(speedM, BspeedM, IspeedM));
			countS--;
		}
		if(time % 180 == 0 && countT > 0){
			mons.add(new TankMonster(tankM, BtankM, ItankM));
			countT--;
		}
		if(time % 100 == 0 && countSp > 0){
			mons.add(new SplitterMonster(splitM, BsplitM, IsplitM));
			countSp--;
		}
		if(countBb > 0){
			mons.add(new BossMonster(bossM, BbossM, IbossM));
			countBb--;
		}
		if(bossDay && time % 140 == 0){
			int temp = (int)(Math.random() * 4);
			if(temp == 0){
				mons.add(new Monster(basicM, BbasicM, IbasicM));
			}
			if(temp == 1){
				mons.add(new SpeedMonster(speedM, BspeedM, IspeedM));
			}
			if(temp == 2){
				mons.add(new TankMonster(tankM, BtankM, ItankM));
			}
			if(temp == 3){
				mons.add(new SplitterMonster(splitM, BsplitM, IsplitM));
			}
			mons.get(mons.size() - 1).hB.move((int)mons.get(0).returnHB().getX(), 595);
			mons.get(mons.size() - 1).h.move((int)mons.get(0).returnHB().getX(), 585);
			
			if(temp == 2){
				mons.get(mons.size() - 1).hB.move((int)mons.get(0).returnHB().getX(), 575);
				mons.get(mons.size() - 1).h.move((int)mons.get(0).returnHB().getX(), 565);
			}
		}
		
		for(int i = 0; i < mons.size(); i++){
			mons.get(i).moveMons(win);
		}
		
		/*for(int i = 0; i < mons.size(); i++){
			for(int j = 0; j < mons.size() - 1; j++){
				if(mons.get(j).returnHB().getX() > mons.get(j+1).returnHB().getX()){
					mons.add(j, mons.remove(j+1));
				}
			}
		}*/
		for(int i = 0; i < effects.size(); i++){
			effects.get(i).moveAndDraw(win);
		}
	}
	
	public void addSplits(int x){
		for(int i = 0; i < 5; i++){
			mons.add(new SmallMonster(smallM, x + i * 20, BsmallM, IsmallM));
		}
	}
	
	public int indexOfFirst(){
		int result = 0;
		for(int i = 1; i < mons.size(); i++){
			if(mons.get(i).returnHB().getX() < mons.get(result).returnHB().getX()){
				result = i;
			}
		}
		return result;
	}
	
	public int indexOfSecond(){
		int dontCheck = this.indexOfFirst();
		int result = 0;
		if(dontCheck == 0 && mons.size() >= 2){
			result = 1;
		}
		for(int i = result + 1; i < mons.size(); i++){
			if(i != dontCheck){
				if(mons.get(i).returnHB().getX() < mons.get(result).returnHB().getX()){
					result = i;
				}
			}
		}
		
		return result; 
	}
	public int indexOfThird(){
		int dontCheck = this.indexOfFirst();
		int dontCheck2 = this.indexOfSecond();
		int result = 0;
		if(mons.size() == 1){
			result = this.indexOfFirst();
		}
		if(mons.size() == 2){
			result = this.indexOfSecond();
		}
		if(mons.size() >= 3){
			while(result == dontCheck || result == dontCheck2){
				result++;
			}
			for(int i = result + 1; i < mons.size(); i++){
				if(i != dontCheck || i != dontCheck2){
					if(mons.get(i).returnHB().getX() < mons.get(result).returnHB().getX()){
						result = i;
					}
				}
			}
		}
		return result; 
	}
	public int indexOfFourth(){
		int dontCheck = this.indexOfFirst();
		int dontCheck2 = this.indexOfSecond();
		int dontCheck3 = this.indexOfThird();
		int result = 0;
		if(mons.size() == 1){
			result = this.indexOfFirst();
		}
		if(mons.size() == 2){
			result = this.indexOfSecond();
		}
		if(mons.size() == 3){
			result = this.indexOfThird();
		}
		if(mons.size() >= 4){
			while(result == dontCheck || result == dontCheck2 || result == dontCheck3){
				result++;
			}
			for(int i = result + 1; i < mons.size(); i++){
				if(i != dontCheck || i != dontCheck2 || i != dontCheck3){
					if(mons.get(i).returnHB().getX() < mons.get(result).returnHB().getX()){
						result = i;
					}
				}
			}
		}
		return result; 
	}
	public int indexOfFifth(){
		int dontCheck = this.indexOfFirst();
		int dontCheck2 = this.indexOfSecond();
		int dontCheck3 = this.indexOfThird();
		int dontCheck4 = this.indexOfFourth();
		int result = 0;
		if(mons.size() == 1){
			result = this.indexOfFirst();
		}
		if(mons.size() == 2){
			result = this.indexOfSecond();
		}
		if(mons.size() == 3){
			result = this.indexOfThird();
		}
		if(mons.size() == 4){
			result = this.indexOfFourth();
		}
		if(mons.size() >= 5){
			while(result == dontCheck || result == dontCheck2 || result == dontCheck3 || result == dontCheck4){
				result++;
			}
			for(int i = result + 1; i < mons.size(); i++){
				if(i != dontCheck || i != dontCheck2 || i != dontCheck3 || i != dontCheck4){
					if(mons.get(i).returnHB().getX() < mons.get(result).returnHB().getX()){
						result = i;
					}
				}
			}
		}
		return result; 
	}

	public void deathEffect(int x, int y){
		
	}
	
	
	public boolean waveDone(){
		if(countB == 0 && countS == 0 && countT == 0 && countSp == 0 && mons.size() == 0){
			return true;
		}
		return false;
	}
	
}
