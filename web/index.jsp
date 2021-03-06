<%--
  Created by IntelliJ IDEA.
  User: ZHONG
  Date: 2017/5/1
  Time: 23:16
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
    <title>Search Engine</title>
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

    <style type="text/css">
        body > .grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }
    </style>

</head>
<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2>Search Engine Base on Hadoop</h2>
        <strong>Please only enter ONE valid English word!</strong>
        <form class="ui large form" method="post" action="/mapredsearch/search">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <input type="text" name="keyword">
                    </div>
                </div>
                <div class="field">
                    <input id="submit" type="submit" class="ui fluid large teal submit button">
                </div>
                <p>A search will usually take <strong>40 minutes</strong> or more.</p>
                <p>
                    <i class="help icon"></i>If the page is no responding, please check <a href="http://192.168.10.210:8088">Hadoop Jobs Page</a>.
                </p>
            </div>
        </form>
        <p><strong>This is a course project for Cloud Computing.</strong></p>
        <p>By 1430003045 钟钧儒</p>
        <p>Styling with Semantic UI</p>
    </div>
</div>
</body>

<script src="assets/jquery-3.2.1.min.js"></script>
<script src="assets/semantic.min.js"></script>

<script>
    $(function(){
        $("#submit").click(function () {
            var $keyword=$("#keyword");
            if ($keyword.val() == "") {
                alert("Please enter a keyword!");
                $keyword.focus();
                return false;
            }
            else{}
                //OnSubmitKeyword($keyword.val());
        })
    })
</script>
</html>
