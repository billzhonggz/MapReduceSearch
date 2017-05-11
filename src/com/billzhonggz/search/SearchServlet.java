package com.billzhonggz.search;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZHONG on 2017/5/1.
 */

public class SearchServlet extends HttpServlet {
    private String strResult = "";
    private String message = "";

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
        // Determine incoming action.
        if (strAction.equals("search")) {
            // Transfer keyword to MapReduce driver.
            // Initialize argument string array.
            String[] args = new String[4];
            // Keyword.
            args[3] = strKeyword;
            // jar file path.
            String jarPath = System.getProperty("catalina.home") + "/SearchMapReduce.jar";
            // FOR TEST: Print out jar path.
            resp.getWriter().append("Server at: " + jarPath);
            // Set jar file path.
            args[0] = jarPath;
            // Input path.
            args[1] = "/search/input";
            // Output path.
            args[2] = "/search/output";
            // Call method. Do mapreduce.
            try {
                strResult = Search.doSearch(args);
            } catch (Exception e) {
                e.printStackTrace();
                // Search failed.
                message = "System internal error while search.";
                req.setAttribute("message", message);
                RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
                rd.forward(req, resp);
            }

            // Take returning string as input, write back to web page.
            if (strResult != "") {
                // Search success with result.
                message = "Search succeed for keyword \"" + strKeyword + "\".";
                req.setAttribute("message", message);
                req.setAttribute("result", strResult);
                RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
                rd.forward(req, resp);
            } else {
                // Search failed.
                message = "No result for keyword \"" + strKeyword + "\".";
                req.setAttribute("message", message);
                RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
                rd.forward(req, resp);
            }

        }
    }
}
