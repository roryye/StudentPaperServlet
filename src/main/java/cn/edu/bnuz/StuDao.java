package cn.edu.bnuz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuDao {

    public static int deleteStu(String No){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("delete from tb_stu where stu_no=?");
            ps.setInt(1, Integer.parseInt(No));
            effectedNum = ps.executeUpdate();
            ps = conn.prepareStatement("delete from tb_paper where stu_no=" + No);
            int effectedNum2 = ps.executeUpdate();
            if(effectedNum > 0  && effectedNum2 > 0){
                System.out.println("删除学生信息成功");
            }else{
                System.out.println("删除学生信息失败");
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

    public static int modifyStu(String No, String Name, String School){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            ps = conn.prepareStatement("update tb_stu set stu_name=?,stu_school=? where stu_no=?");
            ps.setString(1, Name);
            ps.setString(2, School);
            ps.setInt(3, Integer.parseInt(No));
            effectedNum = ps.executeUpdate();
            if(effectedNum == 1){
                System.out.println("修改学生信息成功");
            }else{
                System.out.println("修改学生信息失败");
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

    public static List<Student> getStu(){
        List<Student> studentList = new ArrayList<Student>();
        try{
            Connection conn = DBUtil.getConn();
            String sql = "select * from tb_stu";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Integer stuNo = rs.getInt("stu_no");
                String stuName = rs.getString("stu_name");
                String stuSchool = rs.getString("stu_school");
                Student student = new Student(stuNo, stuName, stuSchool);
                studentList.add(student);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return studentList;
    }

    public static int addStu(String No, String Name, String School){
        Connection conn = null;
        PreparedStatement ps = null;
        int effectedNum = 0;
        try{
            conn = DBUtil.getConn();
            String sql = "insert into tb_stu (stu_no,stu_name,stu_school) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(No));
            ps.setString(2, Name);
            ps.setString(3, School);
            effectedNum = ps.executeUpdate();
            if(effectedNum == 1){
                System.out.println("插入学生信息成功");
            }else{
                System.out.println("插入学生信息失败");
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
//        //查询学生列表
//        for(Student student: getStu()){
//            System.out.println(student.getStuName());
//        }

        //增加学生
        System.out.println(addStu("1701040100", "叶威强", "BNUZ"));

        //删除学生
//        System.out.println(deleteStu("1701040100"));

//        //修改学生
//        int effectedNum = modifyStu("1701040100", "叶", "bnuz");
//        System.out.println(effectedNum);

    }
}
