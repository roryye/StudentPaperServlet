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

@WebServlet("/modifypaper")
public class ModifyPaperServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String paperId = (String) request.getParameter("paperId");
        String stuNo = (String)request.getParameter("stuNo");
        String modifyPaperTitle = (String)request.getParameter("modifyPaperTitle");
        String modifyPaperAuthor = (String)request.getParameter("modifyPaperAuthor");
        String modifyPaperSchool = (String)request.getParameter("modifyPaperSchool");
        int effectedNum = PaperDao.modifyPaper(paperId, stuNo, modifyPaperTitle, modifyPaperAuthor, modifyPaperSchool);
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
