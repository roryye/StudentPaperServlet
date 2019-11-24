package cn.edu.bnuz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaperDao {

    public static List<Paper> getPapers(String stuNo){
        List<Paper> paperList = new ArrayList<Paper>();
        try{
            Connection conn = DBUtil.getConn();
            Statement st = conn.createStatement();
            String sql = "select * from tb_paper where stu_no=" + stuNo;
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Integer paperId = rs.getInt("paper_id");
                String paperTitle = rs.getString("paper_title");
                String paperAuthor = rs.getString("paper_author");
                String paperSchool = rs.getString("paper_school");
                Paper paper = new Paper(paperId, paperTitle, paperAuthor, paperSchool);
                paperList.add(paper);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return paperList;
    }

    public static int modifyPaper(String paperId, String stuNo, String paperTitle, String paperAuthor, String paperSchool){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("update tb_paper set paper_title=?,paper_author=?,paper_school=? where stu_no=? and paper_id=?");
            ps.setString(1, paperTitle);
            ps.setString(2, paperAuthor);
            ps.setString(3, paperSchool);
            ps.setInt(4, Integer.parseInt(stuNo));
            ps.setInt(5, Integer.parseInt(paperId));
            effectedNum = ps.executeUpdate();
            if(effectedNum > 0){
                System.out.println("修改论文信息成功");
            }else{
                System.out.println("修改论文信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return effectedNum;
    }

    public static int deletePaper(String paperId){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("delete from tb_paper where paper_id=?");
            ps.setInt(1, Integer.parseInt(paperId));
            effectedNum = ps.executeUpdate();
            if(effectedNum == 1){
                System.out.println("删除论文信息成功");
            }else{
                System.out.println("删除论文信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return effectedNum;
    }

    public static int addPaper(String stuNo, String paperTitle, String paperAuthor, String paperSchool){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            String sql = "insert into tb_paper (paper_title,paper_author,paper_school, stu_no) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, paperTitle);
            ps.setString(2, paperAuthor);
            ps.setString(3, paperSchool);
            ps.setInt(4, Integer.parseInt(stuNo));

            effectedNum = ps.executeUpdate();
            if(effectedNum > 0){
                System.out.println("插入论文信息成功");
            }else{
                System.out.println("插入论文信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return effectedNum;

    }

    public static void main(String[] args) {
//        //查询论文列表
//        for(Paper paper: getPapers("1701040110")){
//            System.out.println(paper.getPaperId());
//        }

//        int effectedNum = modifyPaper("1701040120", "3", "3", "3");
//        System.out.println(effectedNum);

//        //删除论文
//        int effectedNum = deletePaper("2");
//        System.out.println(effectedNum);

        //新增论文
        int effectedNum = addPaper("1701040120", "3", "4", "5");
        System.out.println(effectedNum);

    }
}
