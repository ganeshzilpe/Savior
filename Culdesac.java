import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Culdesac
{
    static int count,dt,mnth,yr;
    static String bb1,bb2,bb3,bb4,bb5;
    static PreparedStatement ca,cs;
    static Connection con;
    static ResultSet rs;
    static
    {
        count=dt=mnth=yr=0;
        bb1=bb2=bb3=bb4=bb5="";
        rs=null;
        try
        {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql:postgres","postgres","it");
            ca=con.prepareStatement("insert into culdesac values(?,?,?,?,?,?,?)");
            cs=con.prepareStatement("select count(did) from donor where (dt=? and mnth=? and yr=?)");
        }
        catch(Exception e){}
    }
    
    static void cadd() throws Exception 
    {
        String str=""+dt+"/"+mnth+"/"+yr;
        ca.setString(1,str);
        ca.setInt(2,count);
        ca.setString(3,bb1);
        ca.setString(4,bb2);
        ca.setString(5,bb3);
        ca.setString(6,bb4);
        ca.setString(7,bb5);
        ca.executeUpdate();
    }
    static ResultSet csearch() throws Exception
    {
        cs.setInt(1,dt);
        cs.setInt(2,mnth);
        cs.setInt(3,yr);
        rs=cs.executeQuery();
        return rs;
    }
}