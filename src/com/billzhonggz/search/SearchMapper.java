package com.billzhonggz.search;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by ZHONG on 2017/5/1.
 */
public class SearchMapper extends MapReduceBase implements Mapper<Object, Text, Text, Text> {
    private String keywords = "is";
    private Text outputRow = new Text();

    @Override
    public void map(Object key, Text text, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {
        // Get a row
        String row = text.toString();
        StringTokenizer tokenizerArticle = new StringTokenizer(row, "\n");

        // TODO: Test substring solution.
        while (tokenizerArticle.hasMoreTokens()) {
            StringTokenizer tokenizer = new StringTokenizer(tokenizerArticle.nextToken());
            String[] words = row.split(" ");
            // Traversal string array.
            for (String word : words) {
                // Compare words.
                if (word.equals(keywords)) {
                    outputRow.set(row);
                    // Collect outputs. Set incoming keyword as a key, its row as value.
                    outputCollector.collect(new Text(keywords), outputRow);
                }
            }
        }
    }
}
