<%--
  Created by IntelliJ IDEA.
  User: ZHONG
  Date: 2017/5/2
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>Search Result</title>
    <link rel="stylesheet" type="text/css" href="assets/components/reset.css">
    <link rel="stylesheet" type="text/css" href="assets/components/site.css">

    <link rel="stylesheet" type="text/css" href="assets/components/container.css">
    <link rel="stylesheet" type="text/css" href="assets/components/grid.css">
    <link rel="stylesheet" type="text/css" href="assets/components/header.css">
    <link rel="stylesheet" type="text/css" href="assets/components/image.css">
    <link rel="stylesheet" type="text/css" href="assets/components/menu.css">

    <link rel="stylesheet" type="text/css" href="assets/components/divider.css">
    <link rel="stylesheet" type="text/css" href="assets/components/segment.css">
    <link rel="stylesheet" type="text/css" href="assets/components/form.css">
    <link rel="stylesheet" type="text/css" href="assets/components/input.css">
    <link rel="stylesheet" type="text/css" href="assets/components/button.css">
    <link rel="stylesheet" type="text/css" href="assets/components/list.css">
    <link rel="stylesheet" type="text/css" href="assets/components/message.css">
    <link rel="stylesheet" type="text/css" href="assets/components/icon.css">

</head>
<body>
<div class="ui attached stackable menu">
    <div class="ui container">
        <a class="item" href="index.jsp">
            <i class="home icon"></i>Back to main page
        </a>
        <div class="item">
            <i class="info circle icon"></i>
            <strong>Message: </strong>${message}
        </div>
        <a class="right item" href="http://192.168.10.210:8088">
            <i class="help icon"></i>For more technical information: Hadoop Job Status Page
        </a>
    </div>
</div>
<div class="ui raised very padded text container segment">
    <h2 class="ui header">Search Result</h2>
    <p>${result}</p>
    <p>
        <a class="item" href="index.jsp"><i class="home icon"></i>Back to main page</a>
    </p>
</div>
<div class="ui raised very padded text container segment">
    <p>Search Engine Based on Hadoop</p>
    <p><i class="copyright icon"></i>2017 Junru Zhong (1430003045)</p>
    <p>Course Project for Cloud Computing</p>
</div>
</body>
</html>
