import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Donor Entry Form
class Donor
{
    static int did,id,age,wt,cnt,dt,mnth,yr,admyr;
    static String name,blg,cno,email,gen,bb;
    static PreparedStatement da,du,ds,dfid,dab,dfid1;
    static ResultSet rs;
    static Connection con;

    static
    {
        did=id=age=wt=dt=mnth=yr=cnt=admyr=0;
        name=blg=email=gen=cno=bb="";
        rs=null;
        try
        {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql:postgres","postgres","it");
            da=con.prepareStatement("insert into donor values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            du=con.prepareStatement("Update donor set did=?,blg=?,age=?,wt=?,cno=?,email=?,admyr=?,gen=?,cnt=?,dt=?,mnth=?,yr=? where name=?");
            ds=con.prepareStatement("select * from donor where name=?");
            dfid=con.prepareStatement("select max(did) from donor");
            dab=con.prepareStatement("insert into bbinfo values(?,?,?)");
            dfid1=con.prepareStatement("select max(id) from bbinfo");
        }
        catch(Exception e1){}
    }

    static void dadd() throws Exception
    {
        da.setInt(1,did);
        da.setString(2,name);
        da.setString(3,blg);
        da.setInt(4,age);
        da.setInt(5,wt);
        da.setString(6,cno);
        da.setString(7,email);
        da.setString(8,gen);
        da.setInt(9,dt);
        da.setInt(10,mnth);
        da.setInt(11,yr);
        da.setInt(12,admyr);
        da.setInt(13,cnt);
        da.executeUpdate();
    }

    static void dmod() throws Exception
    {
        du.setInt(1,did);
        du.setString(2,blg);
        du.setInt(3,age);
        du.setInt(4,wt);
        du.setString(5,cno);
        du.setString(6,email);
        du.setInt(7,admyr);
        du.setString(8,gen);
        du.setInt(9,cnt);
        du.setInt(10,dt);
        du.setInt(11,mnth);
        du.setInt(12,yr);
        du.setString(13,name);
        du.executeUpdate();
    }

    static boolean dsearch(String str) throws Exception
    {
        ds.setString(1,str);
        rs=ds.executeQuery();
        return rs.next();
    }

    static ResultSet dFindId() throws Exception
    {
        rs=dfid.executeQuery();
        return rs;
    }

    static void badd() throws Exception
    {
        dab.setInt(1,id);
        dab.setInt(2,did);
        dab.setString(3,bb);
        dab.executeUpdate();
    }

    static ResultSet dFindId1() throws Exception
    {
        rs=dfid1.executeQuery();
        return rs;
    }
}