package cn.edu.bnuz.stu;

import cn.edu.bnuz.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modifystu")
public class ModifyStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String modifyStuNo = (String)request.getParameter("modifyStuNo");
        String modifyName = (String)request.getParameter("modifyName");
        String modifyStuSchool = (String)request.getParameter("modifyStuSchool");
        int effectedNum = StuDao.modifyStu(modifyStuNo, modifyName, modifyStuSchool);
        String str = null;
        if(effectedNum > 0){
            str = "{\"success\":true}";
        }else{
            str = "{\"success\":false}";
        }
        out.print(str);
        out.flush();
        out.close();
    }
}
