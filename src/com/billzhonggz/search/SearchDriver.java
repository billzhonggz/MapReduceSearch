package com.billzhonggz.search;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

/**
 * Created by ZHONG on 2017/5/1.
 */
public class SearchDriver {
    public static void main(String[] args) {
        // FOR DEBUG
        System.out.println("In Driver. \nKeyword: " + args[0] + "\nInput path: " + args[1] + "\nOutput path: " + args[2]);

        JobClient client = new JobClient();
        JobConf conf = new JobConf(SearchDriver.class);
        conf.setJobName("Search");

        conf.setBoolean("mapreduce.app-submission.cross-platform", true);
        conf.set("fs.defaultFS", "hdfs://vm1:9000");
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("yarn.resoursemanager.address", "vm1:8032");
        conf.set("yarn.resoursemanager.scheduler.address", "vm1:8030");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(SearchMapper.class);
        conf.setReducerClass(SearchReducer.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        conf.set("keyword", args[0]);

        FileInputFormat.setInputPaths(conf, new Path(args[1]));
        FileOutputFormat.setOutputPath(conf, new Path(args[2]));

        client.setConf(conf);

        try {
            // Delete existing output.
            FileSystem fs = FileSystem.get(conf);
            fs.delete(new Path(args[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.runJob(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
