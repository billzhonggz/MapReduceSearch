package com.billzhonggz.search;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by zhong on 17-5-10.
 */
public class SearchMapper extends Mapper<Object, Text, Text, Text> {
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String keyword = conf.get("keyword");
        Text outputRow = new Text();
        // Get a row
        String row = value.toString();
        StringTokenizer tokenizerArticle = new StringTokenizer(row, "\n");
        while (tokenizerArticle.hasMoreTokens()) {
            StringTokenizer tokenizer = new StringTokenizer(tokenizerArticle.nextToken());
            String[] words = row.split(" ");
            // Traversal string array.
            for (String word : words) {
                // Compare words.
                if (word.equals(keyword)) {
                    outputRow.set(row);
                    // Collect outputs. Set incoming keyword as a key, its row as value.
                    context.write(new Text(keyword), outputRow);
                }
            }
        }
    }
}
