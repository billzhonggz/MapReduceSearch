package com.billzhonggz.search;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.ByteArrayOutputStream;
import java.net.URI;

/**
 * Created by zhong on 17-5-9.
 */
public class Search {
    public static void main(String[] args) throws Exception {
        String result = doSearch(args);
        System.out.println(result);
    }

    public static String doSearch(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("keyword", args[3]);
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
        // Read output file.
        String outputFileUri = args[2] + "/part-r-00000";
        Configuration confReadFile = new Configuration();
        String ret = "";
        FileSystem fsReadFile = FileSystem.get(URI.create(outputFileUri), confReadFile);
        Path pathReadFile = new Path(outputFileUri);
        FSDataInputStream in = fsReadFile.open(pathReadFile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copyBytes(in, out, 4096, true);
        ret = out.toString();
        return ret;
    }
}