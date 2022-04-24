package TermProject;

public class EnemyObject{
	private int location;
	private int speed;
	private int HP;
	private boolean died;
	private int gold;
	private boolean reflection;
	public int getLocation() {
		return this.location;
	}
	
	public void setLocation(int loc) {
		this.location=loc;
	}
	public void move() {

			this.location++;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int sp) {
		this.speed=sp;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hp) {
		this.HP=hp;
	}
	public void downHP(int x) {
		this.HP-=x;
	}
	public void setDied(boolean x) {
		this.died=x;
	}
	public boolean getDied() {
		return this.died;
	}
	public void setGold(int gold) {
		this.gold=gold;
	}
	public int getGold() {
		return this.gold;
	}
	public void setReflection(boolean a) {
		this.reflection=a;
	}
	public boolean getReflection() {
		return this.reflection;
	}
	
}
