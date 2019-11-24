package cn.edu.bnuz.paper;

import cn.edu.bnuz.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/selectpapers")
public class SelectPaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        PrintWriter out = response.getWriter();
//        int effectedNum = StuDao.addStu(addStuNo, addStuName, addStuSchool);
//        String str = null;
//        if (effectedNum == 1) {
//            str = "{\"success\":true}";
//        } else {
//            str = "{\"success\":false}";
//        }
//        out.print(str);
//        out.flush();
//        out.close();
    }
}
