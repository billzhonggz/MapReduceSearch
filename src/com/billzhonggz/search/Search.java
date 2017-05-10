package com.billzhonggz.search;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by zhong on 17-5-9.
 */
public class Search {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //conf.set("mapred.map.child.env","export HADOOP_CLASSPATH=$HADOOP_CLASSPATH:`echo *.jar`:`echo lib/*.jar | sed 's/ /:/g'`");
        //conf.set("mapreduce.job.jar", "full_path_of_my_jobexecutor.jar_in_my_deploy_folder_on_wild‌​fly");
        conf.set("keyword", args[3]);
        //conf.set("mapred.jar", args[0]);
        conf.addResource("core-site.xml");
        conf.addResource("mapred-site.xml");
        conf.addResource("yarn-site.xml");
        Job job = Job.getInstance(conf, "search");
        job.setJar(args[0]);
        job.setJarByClass(Search.class);
        job.setMapperClass(SearchMapper.class);
        job.setReducerClass(SearchReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[1]));
        Path output = new Path(args[2]);
        FileOutputFormat.setOutputPath(job, output);
        // Delete output directory if exist.
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(output)) {
            fs.delete(output, true);
        }
        job.waitForCompletion(true);
    }
}