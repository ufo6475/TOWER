package TermProject;

public class TeamObject {
	private int x;
	private int y;
	private int power;
	private int range;
	private int level;
	private int upgrade;
	private int rotate;
	private int rotate_des;
	private int location;
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	public void setLocation(int location) {
		this.location=location;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	
	public void setLevel(int level) {
		this.level=level;
	}
	public int getLevel() {
		return level;
	}
	
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power=power;
	}
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range=range;
	}
	public void UPGRADE() {
		this.upgrade++;
	}
	public void setUpgrade(int upgrade) {
		this.upgrade=upgrade;
	}
	public int getUpgrade() {
		return upgrade;
	}

	public void setRotate(double nrotate) 
	{	
		if(nrotate<0) {
			nrotate=360+nrotate;
		}
		this.rotate_des=(int)(nrotate);
	}
	public void UpRotate() {
		if(rotate>360)
			rotate-=360;
		else if(rotate<-360)
			rotate+=360;
		
		
		int p_rotate;
		if(rotate<0) {
			p_rotate=rotate+360;
		}
		else if(rotate>360)
			p_rotate=rotate-360;
		else {
			p_rotate=rotate;
		}
		int res=Math.abs(rotate_des-p_rotate);
		if(p_rotate<rotate_des&&res<180)
			rotate++;
		else if(p_rotate<rotate_des&&res>180)
			rotate--;
		else if(p_rotate>rotate_des&&res<180)
			rotate--;
		else if(p_rotate>rotate_des&&res>180)
			rotate++;
		else;
	}
	
	
	public int getRotate() {
		return this.rotate;
	}
	}
