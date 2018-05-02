package com.mapreduce.saavn;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import com.amazonaws.thirdparty.apache.http.ParseException;

public class SaavnMapper extends Mapper<Object,Text,Text,Song> {
	
	public void map(Object key , Text record , Context con) throws IOException, InterruptedException 
	{
		String[] info = record.toString().split(",");
		if(info.length >= 4){
		String songid = info[0];
		String date = info[4];
		con.write(new Text(date), new Song(songid,1));  //write the date and song id count.
		}
	
	}

}
