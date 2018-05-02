package com.mapreduce.saavn;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;


public class SaavnReducer extends Reducer<Text,Song,Text,Text> {

	public void reduce(Text key , Iterable<Song> songList,Context con) throws IOException, InterruptedException {

		Map<String,Song> songCount = new HashMap<String,Song>(); //create a hash map to hold the list of date and songs
		PriorityQueue<Song> topList = new PriorityQueue<Song>(); //create a priority queue to hold the top 100 songs

		for(Song s : songList){
			if(songCount.containsKey(s.songID)){  //check if the song exists then increment the count
				Song value = songCount.get(s.songID);
				songCount.put(s.songID,new Song(s.songID,value.count+1));
			}
			else
			{
				songCount.put(s.songID,new Song(s.songID,1)); //if songs does not exists then put count as 1
			}

		}

		for (Map.Entry<String, Song> song : songCount.entrySet())
		{
			if(topList.size() <100) topList.add(song.getValue()); //check if the count of song is 100 else add one to priority queue

			//Get the top element and compare with incoming element
			else if(song.getValue().compareTo(topList.peek()) > 0) {
				topList.remove(); 
				topList.add(song.getValue());
			}

		}

        int i=1;
		for (Song s : topList)
		{
			
			con.write(key,new Text(s.songID+"::"+i));
			i++;
		}
		
		

	}


}
