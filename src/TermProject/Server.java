package TermProject;

import java.util.List;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
	private static ArrayList<Thread>arr=new ArrayList<Thread>();
	static int num=-1;
	static HashMap<String,Integer>ScoreMap=new HashMap<>();
	Socket socket;
	public static void main(String[] args){
		int id=0;
		ServerSocket serverSocket=null;
		final int SERVER_PORT=5000;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server starts");
		while (true) {
			try {
			Socket socket=serverSocket.accept();
			Thread tt=new Receive(socket,ScoreMap);
			tt.start();
			arr.add(tt);
			Iterator<Thread>iter =arr.iterator();
			while(iter.hasNext()) {
				tt=iter.next();
				if(!tt.isAlive()) {
					tt=iter.next();
					if(!tt.isAlive()) {
						iter.remove();
					}
				}
			}
			
			/*
			Thread t=new CommThread(socket,id++,ScoreMap);
			t.start();
			arr.add(t);
				Iterator<Thread>iter2 =arr.iterator();
				while(iter2.hasNext()) {
					t=iter2.next();
					if(!t.isAlive()) {
						t=iter2.next();
						if(!t.isAlive()) {
							iter2.remove();
						}
					}
				}
				*/
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
class Receive extends Thread {
	private Socket soc;
	HashMap<String,Integer>ScoreMap;
	List<Entry<String,Integer>> keySetList;
	Receive(Socket soc,HashMap<String,Integer>ScoreMap){
		this.soc=soc;
		this.ScoreMap=ScoreMap;

	}

	public void run () {
		try {
			InputStream is=soc.getInputStream();
			BufferedReader reader =new BufferedReader(new InputStreamReader(is));
			OutputStream out=soc.getOutputStream();
			PrintWriter writer =new PrintWriter(out,true);
			
			String scorename;
			scorename=reader.readLine();
			System.out.println(scorename);
			String[] input=scorename.split(",");
			
			String name=input[0];
			Integer score=Integer.valueOf(input[1]);
			
			ScoreMap.put(name, score);
			keySetList=new ArrayList<>(ScoreMap.entrySet());
			keySetList.sort(Entry.comparingByValue());
			for(int i=keySetList.size()-1;i>=0;i--) {
				String output=keySetList.get(i).getKey()+","+keySetList.get(i).getValue();
				writer.println(output);
				writer.flush();
			}
			writer.println("end");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CommThread extends Thread{
	private Socket soc;
	private int id;
	HashMap<String,Integer>ScoreMap;
	List<Entry<String,Integer>> keySetList;
	
	public CommThread(Socket soc,int id,HashMap<String,Integer>ScoreMap) {
		this.soc=soc;
		this.id=id;
		this.ScoreMap=ScoreMap;
		keySetList=new ArrayList<>(ScoreMap.entrySet());
		keySetList.sort(Entry.comparingByValue());
//		keySetList=new ArrayList<>(this.ScoreMap.keySet());
//		Collections.sort(null);
}
	public void run() {
		try {
			OutputStream os=soc.getOutputStream();
			int readbyte;
			try {
				byte[] buff;
				synchronized(this) {
				buff=new byte[100];
				}
	//			Iterator<Entry<String,Integer>>keys =keySetList.iterator();
				
				for(int i=keySetList.size()-1;i>=0;i--) {
					String output=keySetList.get(i).getKey()+","+keySetList.get(i).getValue();
					buff=output.getBytes();
					os.write(buff,0,buff.length);
				}
				os.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
