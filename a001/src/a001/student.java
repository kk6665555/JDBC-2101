package a001;

import java.io.Serializable;

public class student  implements Serializable{
	private int ch,eng,math;
	private String id;
	public student(String id ,int ch,int eng,int math){
		this.id=id; this.ch=ch;this.eng=eng;this.math=math;
	}
	
	public int total(){
		return ch+eng+math;
	}
	public double avg(){
		return total() / 3.0;
	}
	public String getid(){return id;}
}
