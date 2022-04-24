package TermProject;

public class Status {
	private int gold;
	private int heart;
	private int died_monster;
	private int score;
	
	public int getGold() {
		return this.gold;
	}
	public void up() {
		this.gold++;
	}
	
	public void setGold(int gold) {
		this.gold=gold;
	}
	public void upGold(int gold) {
		this.gold+=gold;
	}
	public void downGold(int gold) {
		this.gold-=gold;
	}
	
	public int getHeart() {
		return heart;
	}
	public void setHeart(int heart) {
		this.heart=heart;
	}
	public void downHeart() {
		this.heart--;
	}
	public void upHeart() {
		this.heart++;
	}
	public void setdied(int x) {
		this.died_monster=x;
	}
	public void updied() {
		this.died_monster++;
	}
	public int getdied() {
		return this.died_monster;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public int getScore() {
		return this.score;
	}
	public void downScore(int a) {
		this.score-=a;
	}
	public void upScore(int a) {
		this.score+=a;
	}
	
}
