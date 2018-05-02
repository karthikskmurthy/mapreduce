package com.mapreduce.saavn;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;




public class SaavnDriver extends Configured implements Tool {
public static void main(String[] args) throws Exception{
		
		int returnStatus = ToolRunner.run(new Configuration(), new SaavnDriver(), args);
		System.exit(returnStatus);
	}
	
	
public int run(String[] args) throws IOException{
	
	Job job = new Job(getConf());
	job.setJobName("SaavnTrend");
	job.setJarByClass(SaavnDriver.class);
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Song.class);
	job.setMapperClass(SaavnMapper.class);
	job.setReducerClass(SaavnReducer.class);
	job.setNumReduceTasks(31);


	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job,new Path(args[1]));
	
	try{
		return job.waitForCompletion(true) ? 0 : 1;
	}
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
	
}

}
