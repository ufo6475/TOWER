package TermProject;
import java.awt.*;


import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.awt.event.*;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.math.*;

public class Board extends JFrame implements ActionListener{
	Timer timer;
	Timer newtimer=new Timer(); ////
	private Image bufferImage;
	private Graphics screenGraphic;
	public Status status= new Status();
	public int stage_number;
//	public int[] monster_number= {5,10,10,10,10,10,10,10,10,10};
	public int[] monster_number= {10,20,20,20,30,30,30,40,40,50};	//number of monsters in stages
	public int[] speed_number= {30,30,20,20,15,15,10,10,5,3};		//speed of monsters in stages
	public int[] HP_number= {50,100,100,150,150,200,200,250,300,300};		//HP of monsters in stages
	public int[] MakingSpeed= {2000,1500,1000,800,700,500,400,300,200,100};	//Speed of Making monsters
	public int[] monster_gold= {10,15,20,25,30,35,40,45,55,60};	//gold when kill monster
	public int[] monster_score= {10,20,30,40,50,60,70,80,90,100};
	
	public int [][]TowerRange= {{100,150,200},{150,150,150},{200,250,300}};		//Attack Range of Tower level
	public int[][] TowerPower= {{20,30,40},{20,40,60},{70,85,100}};		//Power of Tower
	public int[][] FireSpeed= {{1000,900,800},{700,500,300},{1000,900,800}};		//Speed of Fire
	public int[] TowerGold= {50,150,300};
	public int[] UpgradeGold= {50,100,150};
	
	public int[][] bullet_speed= {{50,50,50},{50,50,50},{50,50,50}};
	
	Image [] mage;
	Image [][]Tower;
	Image [][]BULLET;
	BufferedImage [][]Tower_buf;
	BufferedImage [][]Bullet_buf;
	
	public static int created_moster;
	public static int cur_time;
	public static boolean start;
	public static int cnt;
	public static int num_bullet;
	public static int num_tower;
	public static boolean skill_use;
	public static int a_selected=-1;
	public static int Cooltime=0;
	public static boolean skillbutton_on; ////
	
	int []Xlist=new int [1390];
	int []Ylist=new int [1390];
	ConcurrentHashMap<Integer,EnemyObject>MonsterMap=new ConcurrentHashMap<>();	//Monster Hashmap
	ConcurrentHashMap<Integer,TeamObject>TowerMap=new ConcurrentHashMap<>();
	ConcurrentHashMap<Integer,Bullet>BulletMap=new ConcurrentHashMap<>();

//	Hashtable<Integer,EnemyObject>MonsterMap=new Hashtable<>();	//Monster Hashmap
//	Hashtable<Integer,TeamObject>TowerMap=new Hashtable<>();
//	Hashtable<Integer,Bullet>BulletMap=new Hashtable<>();

	
	Image maze = new ImageIcon("src/images/background.png").getImage();
	Image resize = maze.getScaledInstance(800, 720, Image.SCALE_SMOOTH);

	
	Image v1= new ImageIcon("src/Images/Blue_Virus.png").getImage();
	Image v1_re=v1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image v2= new ImageIcon("src/Images/Green_Virus.png").getImage();
	Image v2_re=v2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image v3= new ImageIcon("src/Images/Red_Virus.png").getImage();
	Image v3_re=v3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image v4= new ImageIcon("src/Images/Pink_Virus.png").getImage();
	Image v4_re=v4.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image v5= new ImageIcon("src/Images/Yellow_Virus.png").getImage();
	Image v5_re=v5.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image T= new ImageIcon("src/Images/Tower.png").getImage();
	Image T_re=T.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	
	Image T1_1= new ImageIcon("src/Images/Cannon.png").getImage();
	Image T1_1_re=T1_1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image icont_1=T1_1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

	Image T1_2= new ImageIcon("src/Images/Cannon2.png").getImage();
	Image T1_2_re=T1_2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image T1_3= new ImageIcon("src/Images/Cannon3.png").getImage();
	Image T1_3_re=T1_3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	
	Image T2_1= new ImageIcon("src/Images/MG.png").getImage();
	Image T2_1_re=T2_1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image icont_2=T2_1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

	Image T2_2= new ImageIcon("src/Images/MG2.png").getImage();
	Image T2_2_re=T2_2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image T2_3= new ImageIcon("src/Images/MG3.png").getImage();
	Image T2_3_re=T2_3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	
	Image T3_1= new ImageIcon("src/Images/Missile_Launcher.png").getImage();
	Image T3_1_re=T3_1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image icont_3=T3_1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

	
	Image T3_2= new ImageIcon("src/Images/Missile_Launcher2.png").getImage();
	Image T3_2_re=T3_2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	Image T3_3= new ImageIcon("src/Images/Missile_Launcher3.png").getImage();
	Image T3_3_re=T3_3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

	
	
	Image Bullet=new ImageIcon("src/Images/Bullet_MG.png").getImage();
	Image Bullet_re=Bullet.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	Image Bullet_re1=Bullet.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	Image Bullet_re2=Bullet.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	Image Rocket=new ImageIcon("src/Images/Missile.png").getImage();
	Image Rocket_re=Rocket.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	Image Rocket_re1=Rocket.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	Image Rocket_re2=Rocket.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	Image Heart_icon = new ImageIcon("src/Images/heart.png").getImage();
	Image Heart_re = Heart_icon.getScaledInstance(25,25,Image.SCALE_SMOOTH);
	ImageIcon Hicon = new ImageIcon(Heart_re);
	
	Image Skill_Icon = new ImageIcon("src/Images/Skill.png").getImage();
	Image Skill_Re = Skill_Icon.getScaledInstance(60,60,Image.SCALE_SMOOTH);
	Image skill1 =Toolkit.getDefaultToolkit().createImage("src/Images/Skill1.gif");
	Image skill1_re=skill1.getScaledInstance(800,720 ,Image.SCALE_SMOOTH);
	
	ImageIcon Sicon = new ImageIcon(Skill_Re);
	
	GamePanel gpanel;
	
	Container contentPane=getContentPane();
	
	ImageIcon t_1 = new ImageIcon(icont_1);
	ImageIcon t_2 = new ImageIcon(icont_2);
	ImageIcon t_3 = new ImageIcon(icont_3);
	
	ImageIcon [][] tcon = {{new ImageIcon(T1_2_re), new ImageIcon(T1_3_re)}, {new ImageIcon(T2_2_re), new ImageIcon(T2_3_re)}, {new ImageIcon(T3_2_re), new ImageIcon(T3_3_re)}};
	
	JLabel gold_label, heart_label, Skill_label, Score_label, Skill_cost, Tower_cost, Ug_cost,Stage_label;
	JButton nbutton, Skill, tank1, tank2, tank3, upGrade,Quit;
	JButton [] loca = new JButton [33];
	int [] button_enable=new int[33];
	
	int []button_x=new int[33];
	int []button_y=new int[33];
	
	public Board(){
		Init();
		start=false;
		setTitle("Corona");
		setSize(Rungame.WIDTH,Rungame.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		mage = new Image[5];
		mage[0] = v1_re;
		mage[1] = v2_re;
		mage[2] = v3_re;
		mage[3] = v4_re;
		mage[4] = v5_re;
		
		Tower= new Image[3][3];
		Tower[0][0]=T1_1_re;
		Tower[0][1]=T1_2_re;
		Tower[0][2]=T1_3_re;
		Tower[1][0]=T2_1_re;
		Tower[1][1]=T2_2_re;
		Tower[1][2]=T2_3_re;
		Tower[2][0]=T3_1_re;
		Tower[2][1]=T3_2_re;
		Tower[2][2]=T3_3_re;
		
		BULLET=new Image[3][3];
		BULLET[0][0]=Bullet_re;
		BULLET[0][1]=Bullet_re1;
		BULLET[0][2]=Bullet_re2;
		BULLET[1][0]=Bullet_re;
		BULLET[1][1]=Bullet_re1;
		BULLET[1][2]=Bullet_re2;
		BULLET[2][0]=Rocket_re;
		BULLET[2][1]=Rocket_re1;
		BULLET[2][2]=Rocket_re2;
	

		
		Tower_buf= new BufferedImage[3][3];
		MediaTracker mt=new MediaTracker(new JPanel());
	
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				mt.addImage(Tower[i][j], 0);
				try {
					mt.waitForAll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedImage bi=new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d=bi.createGraphics();
				g2d.drawImage(Tower[i][j],0,0,null);
				g2d.dispose();
				Tower_buf[i][j]=bi;
			}
		}
		
		Bullet_buf=new BufferedImage[3][3];
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				mt.addImage(BULLET[i][j], 0);
				try {
					mt.waitForAll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedImage bi=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d=bi.createGraphics();
				g2d.drawImage(BULLET[i][j],0,0,null);
				g2d.dispose();
				Bullet_buf[i][j]=bi;
			}
		}
		
		
		
		gpanel=new GamePanel();
		gpanel.setBounds(0,0,Rungame.WIDTH,Rungame.HEIGHT);
		gpanel.setLayout(null);
		nbutton=new JButton("START");
		nbutton.setFont(new Font("START",Font.BOLD,20));
		nbutton.setBounds(925, 600, 150, 75);
		nbutton.addActionListener(this);
		gpanel.add(nbutton);
		Quit = new JButton("QUIT");
		Quit.setBounds(1092, 607, 80, 60);
		Quit.addActionListener(this);
		Quit.setEnabled(true);
		gpanel.add(Quit);
		
		tank1 = new JButton(t_1);
		tank1.setBounds(865, 400, 70, 70);
		tank1.addActionListener(this);
		gpanel.add(tank1);
		
		tank2 = new JButton(t_2);
		tank2.setBounds(965, 400, 70, 70);
		tank2.addActionListener(this);
		gpanel.add(tank2);
		
		tank3 = new JButton(t_3);
		tank3.setBounds(1065, 400, 70, 70);
		tank3.addActionListener(this);
		gpanel.add(tank3);

		gold_label=new JLabel("Gold: ");
		gold_label.setText("Gold : " + status.getGold()); 
		gold_label.setBounds(1000,150,150,50);
		gold_label.setFont(new Font("gold",Font.BOLD,20));
		gpanel.add(gold_label);
		
		Tower_cost=new JLabel("Install             50                          150                          300");
		Tower_cost.setBounds(815,360,370,20);
		Tower_cost.setFont(new Font("Install",Font.BOLD,12));
		gpanel.add(Tower_cost);
		
		Ug_cost=new JLabel("UPGRADE       50                          100                          150");
		Ug_cost.setBounds(810,320,380,40);
		Ug_cost.setFont(new Font("UPGRADE",Font.BOLD,10));
		Ug_cost.setFont(new Font("       50                    100                  150",Font.BOLD,12));
		gpanel.add(Ug_cost);
		
		heart_label = new JLabel("X 5",Hicon,JLabel.CENTER);
		heart_label.setBounds(850, 150, 100, 50);
		heart_label.setFont(new Font("X 5",Font.BOLD,25));
		gpanel.add(heart_label);
		
		Skill = new JButton(Sicon);
		Skill.setBounds(970, 230, 60, 60);
		Skill.addActionListener(this);
		Skill.setEnabled(false);
		gpanel.add(Skill);
		
		Skill_label = new JLabel(); ///
		Skill_label.setBounds(1040, 230, 60, 60);///
		Skill_label.setEnabled(false);///
		gpanel.add(Skill_label);///
		
		Score_label = new JLabel("SCORE:"); ///
		Score_label.setBounds(925, 100, 150, 50);///
		Score_label.setFont(new Font("SCORE:", Font.BOLD, 20));
		Score_label.setEnabled(true);///
		gpanel.add(Score_label);
		
		Stage_label = new JLabel("STAGE 1"); ///
		Stage_label.setBounds(925, 30, 150, 50);///
		Stage_label.setFont(new Font("STAGE 1", Font.BOLD, 30));
		Stage_label.setEnabled(true);///
		gpanel.add(Stage_label);
		
		Skill_cost = new JLabel("<HTML><body><center>COST<br>300</center></body></HTML>"); ///
		Skill_cost.setBounds(900, 230, 60, 60);///
		Skill_cost.setFont(new Font("<HTML><body><center>COST<br>300</center></body></HTML>", Font.BOLD, 20));
		Skill_cost.setForeground(Color.orange);
		Skill_cost.setEnabled(true);///
		gpanel.add(Skill_cost);
		
		upGrade = new JButton("UPGRADE");
		upGrade.setBounds(850, 480, 300, 100);
		upGrade.setFont(new Font("UPGRADE", Font.BOLD, 30));
		upGrade.addActionListener(this);
		gpanel.add(upGrade);
		upGrade.setEnabled(false);
		
		int or,in = 315;
		int iindex=0;
		for(or = 0; or < 3; or++)
		{	button_x[iindex]=55;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(55, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in -=50;
		}
		in = 110;
		for(; or < 7; or++)
		{
			button_x[iindex]=in;
			button_y[iindex++]=100;
			loca[or] = new JButton("loc");
			loca[or].setBounds(in, 100, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 275;
		for(; or < 10; or++)
		{
			button_x[iindex]=160;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(160, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 275;
		for(; or < 14; or++)
		{
			button_x[iindex]=215;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(215, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 135;
		for(; or < 20; or++)
		{
			button_x[iindex]=325;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(325, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 375;
		for(; or < 22; or++)
		{
			button_x[iindex]=in;
			button_y[iindex++]=385;
			loca[or] = new JButton("loc");
			loca[or].setBounds(in, 385, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 335;
		for(; or < 24; or++)
		{
			button_x[iindex]=425;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(425, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in -=50;
		}
		in = 400;
		for(; or < 26; or++)
		{
			button_x[iindex]=535;
			button_y[iindex++]=in;
			loca[or] = new JButton("loc");
			loca[or].setBounds(535, in, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 540;
		for(; or < 31; or++)
		{
			button_x[iindex]=in;
			button_y[iindex++]=235;
			loca[or] = new JButton("loc");
			loca[or].setBounds(in, 235, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		in = 700;
		for(; or < 33; or++)
		{
			button_x[iindex]=in;
			button_y[iindex++]=360;
			loca[or] = new JButton("loc");
			loca[or].setBounds(in, 360, 50, 50);
			loca[or].addActionListener(this);
			gpanel.add(loca[or]);
			in +=50;
		}
		for(int i=0;i<33;i++) {
			loca[i].setBorderPainted(false);
			loca[i].setContentAreaFilled(false);
			loca[i].setFocusPainted(false);
			loca[i].setText(" ");
			loca[i].setEnabled(false);
		}
		
		contentPane.add(gpanel);
		
		setVisible(true);

		
		//init
	}
	int selected=0;
	int num_selected=0;
	public void actionPerformed(ActionEvent e)
	{
		JButton tmp = (JButton)e.getSource();
		String input = e.getActionCommand();
		int lv, lg;
		if(tmp == nbutton)
		{
			stage_number++;
			RunStage();
			tmp.setEnabled(false);
			upGrade.setEnabled(false);
			Skill.setEnabled(skillbutton_on);
		}
		else if(tmp == Skill)
		{
			if(status.getGold()>=300&&Cooltime==0) {
				Skill_label.setEnabled(true);
				status.downGold(300);
				skill_use=true;
				upGrade.setEnabled(false);
				Cooltime=30;
				Skill.setEnabled(false);
				Skill_label.setVisible(true);
				skillbutton_on=false;////
				Iterator<Integer>keys=MonsterMap.keySet().iterator();
				while(keys.hasNext()) {
					EnemyObject curMonster=MonsterMap.get(keys.next());
					if(curMonster.getDied()==false) {
						curMonster.setDied(true);
		
					}
				}
				gold_label.setText("Gold : " + status.getGold());
			}
		}
		else if(tmp == tank1)
		{
			selected=0;
			for(int i=0;i<33;i++) {
				loca[i].setBorderPainted(true);
				loca[i].setFocusPainted(true);
				loca[i].setText(" ");
				loca[i].setEnabled(true);
				upGrade.setEnabled(false);
			}
		}
		else if(tmp == tank2)
		{
			selected=1;
			for(int i=0;i<33;i++) {
				loca[i].setBorderPainted(true);
				loca[i].setFocusPainted(true);
				loca[i].setText(" ");
				loca[i].setEnabled(true);
				upGrade.setEnabled(false);
			}
		}
		else if(tmp == tank3)
		{
			selected=2;
			for(int i=0;i<33;i++) {
				loca[i].setBorderPainted(true);
				loca[i].setFocusPainted(true);
				loca[i].setText(" ");
				loca[i].setEnabled(true);
				upGrade.setEnabled(false);
			}
		}
		else if(input.equals(" ")) {
			for(int i=0;i<33;i++) {
				if(tmp==loca[i]) {
					if( TowerMap.containsKey(i) && TowerMap.get(i).getUpgrade() < 2)
					{
						
						a_selected = i;
						lv = TowerMap.get(i).getLevel();
						lg = TowerMap.get(i).getUpgrade();
						upGrade.setEnabled(true);
						upGrade.setText("UPGRADE");
						upGrade.setFont(new Font("UPGRADE",Font.BOLD,30));
						upGrade.setIcon(tcon[lv][lg]);
						for(int j=0;j<33;j++) {
							loca[j].setBorderPainted(false);
							loca[j].setFocusPainted(false);
							loca[j].setEnabled(false);
						}
						button_enable[num_selected++]=i;
						for(int k=0;k<num_selected;k++) {
							loca[button_enable[k]].setBorderPainted(true);
							loca[button_enable[k]].setFocusPainted(true);
							loca[button_enable[k]].setEnabled(true);
							
						}
					}
					else if(!TowerMap.containsKey(i))
					{
						int time=200;		//time to reach enemy
						upGrade.setEnabled(false);
						if(TowerGold[selected]<=status.getGold()) {
							status.downGold(TowerGold[selected]);
							gold_label.setText("Gold : " + status.getGold());
						int x=button_x[i];
						int y=button_y[i];
						MakeTower(x,y,selected, i);
						if(start==true) {
							TeamObject curTower=TowerMap.get(i);
							TimerTask Towertask=new TimerTask() {
								public void run() {
										int key=FindClosestEnemy(curTower);
										if(key>-1) {
											EnemyObject TargetEnemy=MonsterMap.get(key);
											if(TargetEnemy.getDied()==false) {
												double degree=Math.toDegrees(Math.atan((double)(curTower.getX()-Xlist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()])/(double)(curTower.getY()-Ylist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()])));
												double c_degree;
												if(curTower.getY()>Ylist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()]) {
													c_degree=-degree;
													curTower.setRotate(c_degree);
												}
												else {
													c_degree=180-degree;
													curTower.setRotate(c_degree);
												}
												MakeBullet(curTower.getX(),curTower.getY(),TargetEnemy,curTower.getLevel(),time,curTower.getUpgrade(),c_degree);
											}
											}
									}
								};
								timer.scheduleAtFixedRate(Towertask, 0, FireSpeed[curTower.getLevel()][curTower.getUpgrade()]);
							
							TimerTask TowerRotate=new TimerTask() {
								public void run() {
									curTower.UpRotate();
								}
							};
							timer.scheduleAtFixedRate(TowerRotate, 0, 1);
						}
						for(int j=0;j<33;j++) {
							loca[j].setBorderPainted(false);
							loca[j].setFocusPainted(false);
							loca[j].setEnabled(false);
						}
						button_enable[num_selected++]=i;
						for(int k=0;k<num_selected;k++) {
							loca[button_enable[k]].setBorderPainted(true);
							loca[button_enable[k]].setFocusPainted(true);
							loca[button_enable[k]].setEnabled(true);
							
							}
						}	
					}
				}
			}
				
		}
		else if (tmp == upGrade)
		{
		
			if (a_selected != -1 && TowerMap.get(a_selected).getUpgrade()<2){
				if(status.getGold()>=UpgradeGold[TowerMap.get(a_selected).getLevel()]) {
					TowerMap.get(a_selected).UPGRADE();
					lv=TowerMap.get(a_selected).getLevel();
					lg=TowerMap.get(a_selected).getUpgrade();
					if(lg<2) {
						upGrade.setEnabled(true);
						upGrade.setIcon(tcon[lv][lg]);
					}
					else {
						upGrade.setEnabled(false);
						upGrade.setText("Complete");
						upGrade.setFont(new Font("Complete",Font.BOLD,30));
					}
					status.downGold(UpgradeGold[TowerMap.get(a_selected).getLevel()]);
					gold_label.setText("Gold : " + status.getGold());
				}
				
			}
		}
		else if (tmp == Quit)
		{
			System.exit(0);
		}
	}

	class GamePanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			componentpaint(g);
		}
	}
	
	public void componentpaint(Graphics g) {
		bufferImage = createImage(800, 720 );
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}

	public void virusDraw(Graphics g) {
		Iterator<Integer>keys=MonsterMap.keySet().iterator();
		while(keys.hasNext()) {
			EnemyObject curEnemy=MonsterMap.get(keys.next());
			int loc=curEnemy.getLocation();
			if(loc<1390&&curEnemy.getDied()==false) {
				int x=Xlist[loc];
				int y=Ylist[loc];
				g.drawImage(mage[stage_number%5],x,y,null);
			}
		}
	}
	public void teamDraw(Graphics g) {
		Iterator<Integer>keys=TowerMap.keySet().iterator();
		while(keys.hasNext()) {
			TeamObject curTower=TowerMap.get(keys.next());
			int x=curTower.getX();
			int y=curTower.getY();
			int level=curTower.getLevel();			
			int rotate=curTower.getRotate();
			BufferedImage newImage=Tower_buf[level][curTower.getUpgrade()];
			g.drawImage(T_re, x-5, y+5,null);
			g.drawImage(getRotateImage(newImage,rotate), x, y,this);
			
		}
	
	}
	  BufferedImage getRotateImage(BufferedImage image, double angle){//angle : degree
          
          double _angle = Math.toRadians(angle);
          double sin = Math.abs(Math.sin(_angle));
          double cos = Math.abs(Math.cos(_angle));
          double w = image.getWidth();
          double h = image.getHeight();
          int newW = (int)Math.floor(w*cos + h*sin);
          int newH = (int)Math.floor(w*sin + h*cos);
         
          GraphicsConfiguration gc = this.getGraphicsConfiguration();
          BufferedImage result = gc.createCompatibleImage(newW, newH, Transparency.TRANSLUCENT);
          Graphics2D g = result.createGraphics();
         
     //     g.translate((newW-w)/2, (newH-h)/2);
          g.rotate(_angle,w/2,h/2+5);
          g.drawRenderedImage(image, null);
          g.dispose();
         
          return result;
  }



	
	public void FireDraw(Graphics g) {
		ArrayList<Thread> BulletDrawlist=new ArrayList<Thread>();
		Iterator<Integer>keys=BulletMap.keySet().iterator();
		while(keys.hasNext()) {
			Bullet curBullet;
			if(!BulletMap.isEmpty()) {
				curBullet=this.BulletMap.get(keys.next());
				if(curBullet.getEnd()==false) {
					int x=curBullet.getX();
					int y=curBullet.getY();
					int level=curBullet.getLevel();	
					int upgrade=curBullet.getUpgrade();
					int rotate=curBullet.getRotate();
					BufferedImage newImage=Bullet_buf[level][upgrade];
					g.drawImage(getRotateImage(newImage,rotate), x, y,this);
				}
				}
		}
		
	}
	
	
	
	
	public void gameDraw(Graphics g) {
		virusDraw(g);
		FireDraw(g);
		
	}

	public void screenDraw (Graphics g) {
		g.drawImage(resize, 0, 0, null);
		teamDraw(g);
		if(start==true) {
			gameDraw(g);
		}
		if(skill_use) {
			g.drawImage(skill1_re,0,0,null);
			TimerTask skilltask=new TimerTask() {
				public void run(){
					skill_use=false;
				} 
			};
			newtimer.schedule(skilltask, 3000);
		}
		repaint();
	}


	
	public void Init() {
		cnt=0;
		cur_time=0;
		stage_number=-1;
		status.setHeart(5);
		status.setGold(100);
		status.setdied(0);
		status.setScore(0);
		created_moster=0;
		num_bullet=0;
		num_tower=0;
		skill_use=false;
		int i;
		for(i=0;i<105;i++) {	//Init Path
			Xlist[i]=i;
			Ylist[i]=365;
		}
		for(int j=1;i<315;i++) {
			Xlist[i]=105;
			Ylist[i]=365-j;
			j++;
		}
		for(int j=1;i<475;i++) {
			Xlist[i]=105+j;
			Ylist[i]=155;
			j++;
		}
		for(int j=1;i<760;i++) {
			Xlist[i]=265;
			Ylist[i]=155+j;
			j++;
		}
		for(int j=1;i<975;i++) {
			Xlist[i]=265+j;
			Ylist[i]=440;
			j++;
		}
		for(int j=1;i<1120;i++) {
			Xlist[i]=480;
			Ylist[i]=440-j;
			j++;
		}
		for(int j=1;i<1390;i++) {
			Xlist[i]=480+j;
			Ylist[i]=295;
			j++;
		}
		
	}
	
	public void MakeTower(int x,int y,int level, int location) {
		TeamObject tower=new TeamObject();
		tower.setX(x);
		tower.setY(y);
		tower.setLevel(level);
		tower.setUpgrade(0);
		tower.setRotate(0);
		tower.setRange(TowerRange[level][0]);
		tower.setLocation(location);
		
		TowerMap.put(location, tower);
	}
	
	public void MakeBullet(int Tower_x,int Tower_y,EnemyObject target,int level,int time,int upgrade,double degree) {
		Bullet bullet=new Bullet();
		bullet.setX(Tower_x+20);
		bullet.setY(Tower_y+20);
		bullet.setTarget(target);
		bullet.setDamage(TowerPower[level][upgrade]);
		bullet.setLevel(level);
		bullet.setUpgrade(0);
		bullet.setXlist(Xlist);
		bullet.setYlist(Ylist);
		bullet.setTime(time);
		bullet.setEnd(false);
		bullet.setRotate(degree);
		bullet.Calculate();
		BulletMap.put(num_bullet++, bullet);
		int distance;
		if(target.getLocation()+time<1390) {
			distance=(int)Math.sqrt((Xlist[target.getLocation()+time]-Tower_x)*(Xlist[target.getLocation()+time]-Tower_x)+(Ylist[target.getLocation()+time]-Tower_y)*(Ylist[target.getLocation()+time]-Tower_y));
		}
		else {
			distance=(int)Math.sqrt((Xlist[target.getLocation()]-Tower_x)*(Xlist[target.getLocation()]-Tower_x)+(Ylist[target.getLocation()]-Tower_y)*(Ylist[target.getLocation()]-Tower_y));
		}
		TimerTask BulletMove=new TimerTask() {
			public void run() {
					if(bullet.getEnd()==true) {
					}
					else if(bullet.Ishit()==true) {
						target.downHP(bullet.getDamage());
						if(target.getHP()<1) {
							target.setDied(true);
							gold_label.setText("Gold : " + status.getGold()); 
						}
					}
					else {
						bullet.move();
					}

				}
			};
		int abcd=distance/time;
		if(abcd==0)
			timer.schedule(BulletMove, 0,1);
		else
			timer.schedule(BulletMove, 0,abcd);
	}
	
	
	public int FindClosestEnemy(TeamObject tower) {
		Iterator<Integer>keys=MonsterMap.keySet().iterator();
		
		double shortest=1000000;
		
		int shortest_key=-1;
		int x=tower.getX();
		int y=tower.getY();
		double squ=tower.getRange()*tower.getRange();
		while(keys.hasNext()) {
			int cur_key=keys.next();
		//	synchronized(this) {
			EnemyObject curEnemy=MonsterMap.get(cur_key);
			int loc=curEnemy.getLocation();
			if(loc<1390&&curEnemy.getDied()==false) {
				double cur=(Xlist[loc]-x)*(Xlist[loc]-x)+(Ylist[loc]-y)*(Ylist[loc]-y);
				if(cur<squ&&cur<shortest) {	
					shortest=cur;
					shortest_key=cur_key;
				}
			}
		//	}
		}
		return shortest_key;
	}
	
	
	
	
	public void RunStage() {
		if(stage_number==0){
			skillbutton_on=true;
		}
		timer=new Timer();
		//RunStage
		status.setdied(0);
		this.created_moster=0;
		TimerTask Cooltimetask=new TimerTask() {	//Cooltime down per sec
			@Override
			public void run() {
				if(Cooltime>0) {
					Skill_label.setVisible(true);
					Cooltime--;
					Skill_label.setText(""+Cooltime);
					Skill_label.setFont(new Font(""+Cooltime,Font.BOLD,50));
				}
				else if(Cooltime==0){
					skillbutton_on=true;
					Skill.setEnabled(skillbutton_on);
					//Skill_label.setEnabled(false);
					Skill_label.setVisible(false);
				}
			}
		};
		timer.scheduleAtFixedRate(Cooltimetask, 0, 1000);
		
		int time=200;
		for(Integer key:TowerMap.keySet()) {
			TeamObject curTower=TowerMap.get(key);
			TimerTask Towertask=new TimerTask() {
				public void run() {
						int key=FindClosestEnemy(curTower);
						if(key>-1) {
							EnemyObject TargetEnemy=MonsterMap.get(key);
							if(TargetEnemy.getDied()==false) {
								double degree=Math.toDegrees(Math.atan((double)(curTower.getX()-Xlist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()])/(double)(curTower.getY()-Ylist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()])));
								double c_degree;
								if(curTower.getY()>Ylist[TargetEnemy.getLocation()+time/TargetEnemy.getSpeed()]) {
									c_degree=-degree;
									curTower.setRotate(c_degree);
								}
								else {
									c_degree=180-degree;
									curTower.setRotate(c_degree);
								}
								MakeBullet(curTower.getX(),curTower.getY(),TargetEnemy,curTower.getLevel(),time,curTower.getUpgrade(),c_degree);
							}
							}
					}
				};
				timer.scheduleAtFixedRate(Towertask, 0, FireSpeed[curTower.getLevel()][curTower.getUpgrade()]);
			
				TimerTask TowerRotate=new TimerTask() {
					public void run() {
						curTower.UpRotate();
					}
				};
				timer.scheduleAtFixedRate(TowerRotate, 0, 1);
				
		}
		

		
		
		
		TimerTask Goldtask=new TimerTask() {	//GOLD per sec
			@Override
			public void run() {
				status.up();
				gold_label.setText("Gold : " + status.getGold()); 
			}
		};
		
		timer.scheduleAtFixedRate(Goldtask, 1000,1000);
		
		TimerTask Monstertask=new TimerTask() {		//Monster per sec
			@Override
			public void run() {
				if(created_moster<monster_number[stage_number]) {
					NewMonster();
				}
			}
		};

		timer.scheduleAtFixedRate(Monstertask, 1000,MakingSpeed[stage_number]);
		
		
		TimerTask CheckFinish= new TimerTask() {
			public void run() {
				StageFinish();
			}
	};
		timer.scheduleAtFixedRate(CheckFinish, 0, 10);
		start=true;
	}

	
	public void NewMonster(){
		EnemyObject NewEnemy=new EnemyObject();
		NewEnemy.setHP(HP_number[stage_number]);
		NewEnemy.setSpeed(speed_number[stage_number]);
		NewEnemy.setLocation(0);
		NewEnemy.setDied(false);
		NewEnemy.setGold(monster_gold[stage_number]);
		NewEnemy.setReflection(false);
		MonsterMap.put(this.created_moster++,NewEnemy);
		TimerTask Monster_move=new TimerTask() {
			public void run() {
				if(NewEnemy.getDied()==true&&NewEnemy.getReflection()==false) {
						NewEnemy.setReflection(true);
						status.updied();
						status.upScore(monster_score[stage_number]);
						status.upGold(NewEnemy.getGold());
				}
				else if(NewEnemy.getLocation()>1389&&NewEnemy.getDied()==false) {
					status.downHeart();
					status.downGold(NewEnemy.getGold());
					status.downScore(monster_score[stage_number]);
					heart_label.setText("X " + status.getHeart());
					NewEnemy.setDied(true);
				}
				else {
					NewEnemy.move();
				}
			}
		};
		timer.scheduleAtFixedRate(Monster_move, 0, speed_number[stage_number]);
	}


	
	public boolean StageFinish() {
//		System.out.println(this.created_moster+","+status.getdied());
		Score_label.setText("SCORE: " + status.getScore());
		if(this.created_moster==monster_number[stage_number]&&this.created_moster<=status.getdied()) {	//Stage win
			Iterator<Integer>keys=MonsterMap.keySet().iterator();
			while(keys.hasNext()) {
				EnemyObject curMonster=MonsterMap.get(keys.next());
				curMonster.setDied(true);
			}
			BulletMap.clear();
			
			MonsterMap.clear();
			
			start=false;
			nbutton.setEnabled(true);
			timer.cancel();
			Skill.setEnabled(false);
			Skill_label.setVisible(false);
			Stage_label.setText("STAGE "+(stage_number+2));
			gold_label.setText("Gold : " + status.getGold());
			if(stage_number==9) {
				this.setVisible(false);
				new NewWindow(true, status.getScore());
			}
			return true;
		}
		else if(status.getHeart()==0) {	//stage lose
			timer.cancel();
			Iterator<Integer>keys=MonsterMap.keySet().iterator();
			while(keys.hasNext()) {
				EnemyObject curMonster=MonsterMap.get(keys.next());
				curMonster.setDied(true);
				curMonster.setDied(true);
			}
			Iterator<Integer>bkeys=BulletMap.keySet().iterator();
			while(keys.hasNext()) {
				Bullet curbullet=BulletMap.get(bkeys.next());
				curbullet.setEnd(true);;
			}
			BulletMap.clear();
			MonsterMap.clear();
			start=false;
			nbutton.setEnabled(true);
			this.setVisible(false);
			gold_label.setText("Gold : " + status.getGold());
			int bscore=status.getScore();
			if (bscore<0)
				bscore=0;
			new NewWindow(false, bscore);
			return true;
		}
		return false;
	}
	
	
}