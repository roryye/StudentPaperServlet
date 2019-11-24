package cn.edu.bnuz.stu;

import cn.edu.bnuz.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/addstu")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String addStuNo = (String)request.getParameter("addStuNo");
        String addStuName = (String)request.getParameter("addStuName");
        String addStuSchool = (String)request.getParameter("addStuSchool");
        int effectedNum = StuDao.addStu(addStuNo, addStuName, addStuSchool);
        String str = null;
        if(effectedNum == 1){
            str = "{\"success\":true}";
        }else{
            str = "{\"success\":false}";
        }
        out.print(str);
        out.flush();
        out.close();
    }
}
