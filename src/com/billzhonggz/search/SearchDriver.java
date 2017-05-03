package com.billzhonggz.search;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

/**
 * Created by ZHONG on 2017/5/1.
 */
public class SearchDriver {
    public static void main(String[] args) {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(SearchDriver.class);
        conf.setJobName("Search");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(SearchMapper.class);
        conf.setReducerClass(SearchReducer.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf,new Path(args[1]));

        client.setConf(conf);
        try {
            client.runJob(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
