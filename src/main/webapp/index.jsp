<%@ page import="cn.edu.bnuz.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="StuDao" class="cn.edu.bnuz.StuDao"/>
<jsp:useBean id="Student" class="cn.edu.bnuz.Student"/>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/css/bootstrap.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="index.css"  rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="panel">
                <div class="list-group">
                    <%--<div class="list-group-item">--%>
                        <%--<span class="no">1701040110</span>--%>
                        <%--<p>--%>
                            <%--<span class="name">杨晓玲</span>--%>
                            <%--<a href="#modifyModal" data-toggle="modal" class="modify"><img src="img/modify.png"--%>
                                                                                           <%--alt="修改"></a>--%>
                            <%--<a href="#deleteModal" data-toggle="modal" class="delete"><img src="img/delete.png"--%>
                                                                                           <%--alt="删除"></a>--%>
                            <%--<a href="paper.jsp" target="_blank"><img src="img/paper.png" alt="论文"></a>--%>
                            <%--<span class="school">吉林大学珠海学院</span>--%>
                        <%--</p>--%>
                    <%--</div>--%>

                    <%
                        List<Student> studentList = StuDao.getStu();
                        for (Student student : studentList) {
                            out.print("<div class=\"list-group-item\">" +
                                    "<span class=\"no\">" + student.getStuNo() + "</span>" +
                                    "<p>" +
                                    "<span class=\"name\">" + student.getStuName() + "</span>" +
                                    "<a href=\"#modifyModal\" data-toggle=\"modal\" class=\"modify\"><img src=\"img/modify.png\" alt=\"修改\"></a>" +
                                    "<a href=\"#deleteModal\" data-toggle=\"modal\" class=\"delete\"><img src=\"img/delete.png\" alt=\"删除\"></a>" +
                                    "<a href=\"paper.jsp?stuNo=" + student.getStuNo() + "\"><img src=\"img/paper.png\" alt=\"论文\"></a>" +
                                    "<span class=\"school\">" + student.getStuSchool() + "</span>" +
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
                            <h3>确定删除该学生的信息吗</h3>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary deleteStu">确定</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="modifyModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h3>学号：<input type="text" disabled="disabled" class="modifyStuNo"></h3>
                            <h3>姓名：<input type="text" class="modifyName"></h3>
                            <h3>学校：<input type="text" class="modifyStuSchool"></h3>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary modifyStu">修改</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="addModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <h3>学号：<input type="text" class="addStuNo"></h3>
                            <h3>姓名：<input type="text" class="addStuName"></h3>
                            <h3>学校：<input type="text" class="addStuSchool"></h3>
                            <button type="button" class="btn btn-default closeAddModal" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary addStu">提交</button>
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

        $('.delete').click(function () {
            // console.log($(this).parent().prev().text())
            deleteStuNo = $(this).parent().prev().text();
        });

        $('.deleteStu').click(function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/deletestu',
                dataType: 'json',
                type: 'POST',
                data: {
                    deleteStuNo: deleteStuNo
                },
                success: function (data) {
                    console.log(data);
                    if (data.success === true) {
                        alert("删除学生成功")
                    } else {
                        alert("删除学生失败")
                    }
                    window.location.href = 'index.jsp';
                }
            })
        });

        $('.modify').click(function () {
            // console.log($(this).parent().prev().text());
            // console.log($(this).siblings('.school').text());
            // console.log($(this).prev().text());
            $('.modifyStuNo').val($(this).parent().prev().text());
            $('.modifyName').val($(this).prev().text());
            $('.modifyStuSchool').val($(this).siblings('.school').text());
        });

        $('.modifyStu').click(function () {
            // console.log($('.modifyStuNo').val());
            // console.log($('.modifyName').val());
            // console.log($('.modifyStuSchool').val());
            var modifyStuNo = $('.modifyStuNo').val();
            var modifyName = $('.modifyName').val();
            var modifyStuSchool = $('.modifyStuSchool').val();

            $.ajax({
                url: '${pageContext.request.contextPath}/modifystu',
                dataType: 'json',
                type: 'POST',
                data: {
                    modifyStuNo: modifyStuNo,
                    modifyName: modifyName,
                    modifyStuSchool: modifyStuSchool
                },
                success: function (data) {
                    console.log(data);
                    if (data.success === true) {
                        alert("修改成功")
                    } else {
                        alert("修改失败")
                    }
                    window.location.href = 'index.jsp';
                }
            })

        });

        $('.addStu').click(function () {
            var addStuNo = $('.addStuNo').val();
            var addStuName = $('.addStuName').val();
            var addStuSchool = $('.addStuSchool').val();
            var key = 1;
            if ("" === addStuNo) {
                key = 0;
                alert("请输入学号！")
            }
            if ("" === addStuName) {
                key = 0;
                alert("请输入姓名！")
            }
            if ("" === addStuSchool) {
                key = 0;
                alert("请输入学校！")
            }
            if (key === 1) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/addstu',
                    dataType: 'json',
                    type: 'POST',
                    data: {
                        addStuNo: addStuNo,
                        addStuName: addStuName,
                        addStuSchool: addStuSchool
                    },
                    success: function (data) {
                        console.log(data);
                        if (data.success === true) {
                            alert("插入成功")
                        } else {
                            alert("插入失败")
                        }
                        window.location.href = 'index.jsp';
                    }
                })
            }

        });


    });

</script>
</html>
