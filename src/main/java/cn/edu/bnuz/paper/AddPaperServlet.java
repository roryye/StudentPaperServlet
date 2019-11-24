package cn.edu.bnuz.paper;

import cn.edu.bnuz.PaperDao;
import cn.edu.bnuz.StuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addpaper")
public class AddPaperServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String stuNo = (String)request.getParameter("stuNo");
        String addPaperTitle = (String)request.getParameter("addPaperTitle");
        String addPaperAuthor = (String)request.getParameter("addPaperAuthor");
        String addPaperSchool = (String)request.getParameter("addPaperSchool");
        int effectedNum = PaperDao.addPaper(stuNo, addPaperTitle, addPaperAuthor, addPaperSchool);
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
