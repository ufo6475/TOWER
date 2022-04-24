package TermProject;

import java.math.*;
public class Bullet {
	private int x;
	private int y;
	
	private float real_x;
	private float real_y;
	
	private EnemyObject target;
	private int damage;
	private boolean end;
	private int time;
	private float xmove;
	private float ymove;
	private int dest_x;
	private int dest_y;
	private int level;
	private int upgrade;
	private int[] Xlist;
	private int[] Ylist;
	private int rotate;

	public void setX(int x) {
		this.x=x;
		this.real_x=x;
	}
	public void setY(int y) {
		this.y=y;
		this.real_y=y;
	}
	public void setTarget(EnemyObject target) {
		this.target=target;
	}

	public void setDamage(int dm) {
		this.damage=dm;
	}
	public void setTime(int time) {
		this.time=time;
	}
	public void setLevel(int level) {
		this.level=level;
	}
	public void setUpgrade(int upgrade) {
		this.upgrade=upgrade;
	}
	public void setXlist(int[] xlist) {
		this.Xlist=xlist;
	}
	public void setYlist(int[] ylist) {
		this.Ylist=ylist;
	}
	

	
	public int getX() {
		return (int)real_x;
	}
	public int getY() {
		return (int)real_y;
	}
	public EnemyObject getTarget() {
		return this.target;
	}

	public int getDamage() {
		return this.damage;
	}
	public int getLevel() {
		return this.level;
	}
	public int getUpgrade() {
		return this.upgrade;
	}
	public void setRotate(double rotate) {
		this.rotate=(int)rotate;
	}
	public int getRotate() {
		return this.rotate;
	}

	public void move() {
		if(target.getDied()==true) {
			setEnd(true);
		}
//		else if(target.getLocation()<1390&&(Xlist[target.getLocation()]-x)<40 && (Xlist[target.getLocation()]-x)>0 && (Ylist[target.getLocation()]-y)<40&& (Ylist[target.getLocation()]-y)<40) {
//			setEnd(true);
//		}
		else {

			real_x+=xmove;
			real_y+=ymove;
		}
	}
	
	
	
	public void setEnd(boolean x) {
		this.end=x;
	}
	public boolean getEnd() {
		return this.end;
	}
	public int getTime() {
		return this.time;
	}
	public void Calculate() {
		
		
	//	dest_x=Xlist[target.getLocation()+time*100/target.getSpeed()]+20;
	//	dest_y=Ylist[target.getLocation()+time*100/target.getSpeed()]+20;
		dest_x=Xlist[target.getLocation()+time/target.getSpeed()]+20;
		dest_y=Ylist[target.getLocation()+time/target.getSpeed()]+20;
		xmove=(dest_x-(float)x)/time;
		ymove=(dest_y-(float)y)/time;
		
	}

	public boolean Ishit() {
	//	if(target.getLocation()<1390&&(Xlist[target.getLocation()]-x)<40 && (Xlist[target.getLocation()]-x)>0 && (Ylist[target.getLocation()]-y)<40&& (Ylist[target.getLocation()]-y)<40) {
		if(getEnd()==false&&Math.abs(real_x-dest_x)<10&&Math.abs(real_y-dest_y)<10) {
			this.setEnd(true);
			return true;
		}
		return false;
	}
	
}
