package TermProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewWindow extends JFrame implements ActionListener
{
	private Image buffer;
	private Graphics screen;
	int total;
	JButton Confirm;
	JTextField Text;
	JLabel sumScore, Name, FinalScore,Finalname, Isvictory;
	String []name_list;
	String []score_list;
	static int num;
	int xa, ya;
	boolean suc;
	
	Image newBack1 = new ImageIcon("src/images/skku.jpg").getImage();
	Image newBack2 = new ImageIcon("src/images/Failed.png").getImage();
	
	Image nBre;
	NewWindow(boolean x, int a) 
	{
		xa = 400;
		ya = 200;
		name_list=new String[100];
		score_list=new String[100];
		num=0;
		this.suc = x;
		this.total = a;
		newJp newWindow = new newJp();
		newWindow.setLayout(null);
		setContentPane(newWindow);
		
		sumScore = new JLabel("SCORE: "+ a);
		sumScore.setBounds(100, 0, 200, 50);
		sumScore.setHorizontalAlignment(JLabel.CENTER);
		Name = new JLabel("NAME: ");
		Name.setBounds(20, 50, 50, 50);
		Name.setHorizontalAlignment(JLabel.RIGHT);
	
		Text = new JTextField();
		Text.setBounds(70, 50, 310, 50);
		Confirm = new JButton("OK");
		Confirm.setBounds(160, 100, 80, 50);
		Confirm.addActionListener(this);
		
		if (x)
		{
			setTitle("Success!");
			nBre= newBack1.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		}
		else 
		{
			setTitle("Failed¡¦");
			Name.setForeground(Color.WHITE);
			sumScore.setForeground(Color.WHITE);
			nBre= newBack2.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		}


		newWindow.add(sumScore);
		newWindow.add(Name);
		newWindow.add(Text);
		newWindow.add(Confirm);
		
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setResizable(false);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton tmp = (JButton)e.getSource();
		if (tmp == Confirm)
		{

			int score=this.total;
			String name=Text.getText();
			try {
				Socket socket=null;
				socket=new Socket("localhost",5000);
				new Send(score,name,socket).start();
				new Receive(socket).start();
			

			}catch(IOException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			xa = 1200;
			ya = 720;
			this.setSize(1200, 720);
			Finalname=new JLabel("NAME  ");
			Finalname.setFont(new Font("NAME: ",Font.BOLD,20));
			
			Finalname.setBounds(450,150,150,600);
			Finalname.setVerticalAlignment(JLabel.NORTH);
			FinalScore=new JLabel("SCORE ");
			FinalScore.setBounds(650,150,150,600);
			FinalScore.setFont(new Font("SCORE: ",Font.BOLD,20));
			FinalScore.setVerticalAlignment(JLabel.NORTH);
			Isvictory=new JLabel("Isvictory");
			Isvictory.setBounds(500,50,300,100);
			Isvictory.setFont(new Font("Isvictory",Font.BOLD,40));
			this.add(Isvictory);
			this.add(Finalname);
			this.add(FinalScore);
			if (suc)
			{	
				nBre = newBack1.getScaledInstance(1200, 720, Image.SCALE_SMOOTH);
				Isvictory.setText("YOU WIN");
			}
			else 
			{
				Finalname.setForeground(Color.red);
				FinalScore.setForeground(Color.red);
				nBre = newBack2.getScaledInstance(1200, 720, Image.SCALE_SMOOTH);
				Isvictory.setText("YOU LOSE");
				Isvictory.setForeground(Color.red);
			}

			
			String nameoutput=name_list[0]+"<br>";
			String scoreoutput=score_list[0]+"<br>";
			for(int i=1;i<num;i++) {
				nameoutput=nameoutput+name_list[i]+"<br>";
				scoreoutput=scoreoutput+score_list[i]+"<br>";
			}
			
			Finalname.setText("<HTML>"+"NAME<br>"+nameoutput+"</HTML>");
			FinalScore.setText("<HTML>"+"SCORE<br>"+scoreoutput+"</HTML>");
			
			this.setLocationRelativeTo(null);
			
			Confirm.setVisible(false);
			Name.setVisible(false);
			Text.setVisible(false);
			sumScore.setVisible(false);
			
			
//			System.exit(0);
		}
	}
	class newJp extends JPanel{

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			componentpaint(g);
		}
	}
	
	public void componentpaint(Graphics g) {
		buffer = createImage(xa, ya);
		screen = buffer.getGraphics();
		screenDraw(screen);
		g.drawImage(buffer, 0, 0, null);
	}
	
	public void screenDraw (Graphics g) {
		g.drawImage(nBre, 0, 0, null);
		repaint();
	}
	
	class Receive extends Thread {
		Socket socket =null;
		
		Receive (Socket socket){
			this.socket=socket;
		}
		
		public void run () {
			try {
				InputStream input=socket.getInputStream();
				BufferedReader reader=new BufferedReader(new InputStreamReader(input));
				String readstring;
				while(true) {
					readstring=reader.readLine();
					if(readstring.equals("end"))
						break;
					String []cmp=readstring.split(",");
					name_list[num]=cmp[0];
					score_list[num++]=cmp[1];					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Send extends Thread {
		int score;
		String name;
		Socket socket=null;
		Send(int score,String name,Socket socket){
			this.score=score;
			this.name=name;
			this.socket=socket;
		}
		
		public void run () {
			try {
				OutputStream out=socket.getOutputStream();
				PrintWriter writer=new PrintWriter(out,true);
				String output=name+","+score;
				writer.println(output);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}