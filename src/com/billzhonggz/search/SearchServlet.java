package com.billzhonggz.search;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZHONG on 2017/5/1.
 */

public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strAction = req.getParameter("action");
        String strKeyword = req.getParameter("keyword");
        String strStartRecord = req.getParameter("startRecord");
        String strEndRecord = req.getParameter("endRecord");
        // FOR TEST: print out parameters. SUCCEED.
        System.out.println(strKeyword);

        // Transfer keyword to MapReduce driver.
        // Initialize argument string array.
        String[] args = new String[3];
        // Keyword.
        args[0] = strKeyword;
        // Input path.
        //args[1] = "D:\\ZHONG\\Documents\\IdeaProjects\\CloudComputingCourseProject\\input\\13.txt";
        args[1] = "hdfs://vm1:9000/search/input";
        // Output path.
        //args[2] = "D:\\ZHONG\\Documents\\IdeaProjects\\CloudComputingCourseProject\\output";
        args[2] = "hdfs://vm1:9000/search/output";
        // Call method. Do mapreduce.
        Search.main(args);

        // TODO: Collect results from MapReduce output.
        // Read file from output path.


        // TODO: Forward results to result.jsp.
    }
}
