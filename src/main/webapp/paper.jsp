<%@ page import="cn.edu.bnuz.Paper" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.bnuz.PaperDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/23 0023
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Paper</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/css/bootstrap.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="paper.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="panel">
                <div class="list-group">
                    <%--<div class="list-group-item">--%>
                        <%--<span class="author" id="0">杨晓玲</span>--%>
                        <%--<p>--%>
                            <%--<span class="title">基于深度神经网络的癌细胞识别系统研究</span>--%>
                            <%--<a href="#modifyPaper" data-toggle="modal" class="modify"><img src="img/modify.png"--%>
                                                                                           <%--alt="修改"></a>--%>
                            <%--<a href="#deleteModal" data-toggle="modal" class="delete"><img src="img/delete.png"--%>
                                                                                           <%--alt="删除"></a>--%>
                            <%--<span class="school">吉林大学珠海学院</span>--%>
                        <%--</p>--%>
                    <%--</div>--%>

                    <%
                        String stuNo = request.getParameter("stuNo");
                        List<Paper> paperList = PaperDao.getPapers(stuNo);
                        for (Paper paper : paperList) {
                            out.print("<div class=\"list-group-item\" id=\"" + paper.getPaperId() + "\">" +
                                    "<span class=\"author\">" + paper.getPaperAuthor() + "</span>" +
                                    "<p>" +
                                    "<span class=\"title\">" + paper.getPaperTitle() + "</span>" +
                                    "<a href=\"#modifyPaper\" data-toggle=\"modal\" class=\"modify\"><img src=\"img/modify.png\" alt=\"修改\"></a>" +
                                    "<a href=\"#deleteModal\" data-toggle=\"modal\" class=\"delete\"><img src=\"img/delete.png\" alt=\"删除\"></a>" +
                                    "<span class=\"school\">" + paper.getPaperSchool() + "</span>" +
                                    "</p>" +
                                    "</div>");
                        }
                    %>
                    <a href="#addModal" data-toggle="modal" class="list-group-item"
                       style="height: 60px;text-align:center;">
                        <img src="img/add.png" alt="增加" style="margin-top: 12px">
                    </a>

                </div>
            </div>
            <div class="modal fade" id="deleteModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h3>确定删除该论文吗</h3>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary deletePaper">确定</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modifyPaper">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h4>论文题目：<textarea class="modifyPaperTitle"></textarea></h4>
                            <h4>论文作者：<textarea class="modifyPaperAuthor"></textarea></h4>
                            <h4>论文学院：<textarea class="modifyPaperSchool"></textarea></h4>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary modifyPaper">修改</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="addModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h3>论文题目：<input type="text" class="addPaperTitle"></h3>
                            <h3>论文作者：<input type="text" class="addPaperAuthor"></h3>
                            <h3>论文院校：<input type="text" class="addPaperSchool"></h3>
                            <button type="button" class="btn btn-default closeAddModal" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary addPaper">新增</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        var deleteStuNo = null;
        var paperId = null;
        //获取URL的学生学号
        var stuNo = window.location.href.split("?stuNo=")[1];

        $('.delete').click(function () {
            // console.log($(this).parent().prev().text())
            deleteStuNo = $(this).parent().prev().text();
            paperId = $(this).parent().parent().attr('id');
        });

        $('.deletePaper').click(function () {

            $.ajax({
                url: '${pageContext.request.contextPath}/deletepaper',
                dataType: 'json',
                type: 'POST',
                data: {
                    paperId: paperId
                },
                success: function (data) {
                    console.log(data);
                    if (data.success === true) {
                        alert("删除论文成功")
                    } else {
                        alert("删除论文失败")
                    }
                    window.location.href = 'paper.jsp?stuNo=' + stuNo;
                }
            })
        });

        $('.modify').click(function () {

            console.log($(this).prev().text());
            console.log($(this).parent().prev().text());
            console.log($(this).siblings('.school').text());
            $('.modifyPaperTitle').val($(this).prev().text());
            $('.modifyPaperAuthor').val($(this).parent().prev().text());
            $('.modifyPaperSchool').val($(this).siblings('.school').text());

            paperId = $(this).parent().parent().attr('id');
        });

        $('.modifyPaper').click(function () {
            // console.log($('.modifyStuNo').val());
            // console.log($('.modifyName').val());
            // console.log($('.modifyStuSchool').val());
            var modifyPaperTitle = $('.modifyPaperTitle').val();
            var modifyPaperAuthor = $('.modifyPaperAuthor').val();
            var modifyPaperSchool = $('.modifyPaperSchool').val();


            $.ajax({
                url: '${pageContext.request.contextPath}/modifypaper',
                dataType: 'json',
                type: 'POST',
                data: {
                    paperId: paperId,
                    stuNo: stuNo,
                    modifyPaperTitle: modifyPaperTitle,
                    modifyPaperAuthor: modifyPaperAuthor,
                    modifyPaperSchool: modifyPaperSchool
                },
                success: function (data) {
                    console.log(data);
                    if (data.success === true) {
                        alert("修改论文成功")
                    } else {
                        alert("修改论文失败")
                    }
                    window.location.href = 'paper.jsp?stuNo=' + stuNo;
                }
            })

        });

        $('.addPaper').click(function () {
            var addPaperTitle = $('.addPaperTitle').val();
            var addPaperAuthor = $('.addPaperAuthor').val();
            var addPaperSchool = $('.addPaperSchool').val();
            var key = 1;
            if ("" === addPaperTitle) {
                key = 0;
                alert("请输入论文题目！")
            }
            if ("" === addPaperAuthor) {
                key = 0;
                alert("请输入论文作者！")
            }
            if ("" === addPaperSchool) {
                key = 0;
                alert("请输入论文院校！")
            }
            if (key === 1) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/addpaper',
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        stuNo: stuNo,
                        addPaperTitle: addPaperTitle,
                        addPaperAuthor: addPaperAuthor,
                        addPaperSchool: addPaperSchool
                    },
                    success: function (data) {
                        console.log(data);
                        if (data.success === true) {
                            alert("插入成功")
                        } else {
                            alert("插入失败")
                        }
                        window.location.href = 'paper.jsp?stuNo=' + stuNo;
                    }
                })
            }

        });


    });

</script>
</html>
