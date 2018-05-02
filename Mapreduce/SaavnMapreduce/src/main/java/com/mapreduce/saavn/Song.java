package com.mapreduce.saavn;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;



public class Song implements WritableComparable {
	
	String songID;
	public String getSongID() {
		return songID;
	}
	public void setSongID(String songID) {
		this.songID = songID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
	count = count;
	}
	int count;
	
	
	public Song(){super();}
	
	public Song(String songID,int count){
		this.songID=songID;
		this.count=count;
		
	}
	
	public void readFields(DataInput dataInput) throws IOException {
	    songID = WritableUtils.readString(dataInput);
	    count = WritableUtils.readVInt(dataInput);
	    
	    
	}

	
	public void write(DataOutput dataOutput) throws IOException {
	    WritableUtils.writeString(dataOutput, songID);
	    WritableUtils.writeVInt(dataOutput, count);
	   

	}
	
	
	public int compareTo(Object s){
		Song sc  = (Song)s;
		int count = this.count;
		int valueToCompare = sc.count;
		return count < valueToCompare ? -1 : (count == valueToCompare? 0:1);
	}
	
	
	
	
	

}
