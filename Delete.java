import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Delete
{
    static int did;
    static String name;
    static String dt;
    static PreparedStatement dl,dl1,ds,ds1,uc,sd,ud,ubb,snm;
    static ResultSet rs;
    static Connection con;

    static
    {
        did=0;
        dt="";
        rs=null;
        try
        {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection("jdbc:postgresql:postgres","postgres","it");
            ds=con.prepareStatement("select * from donor");
            dl=con.prepareStatement("delete from donor where did=?");
            dl1=con.prepareStatement("delete from bbinfo where did=?");
            ds1=con.prepareStatement("select * from culdesac where dt=?");
            uc=con.prepareStatement("update culdesac set count=count-1 where dt=?");
            sd=con.prepareStatement("select * from donor where did=?");
            ud=con.prepareStatement("update donor set did=did-1 where did=?");
            ubb=con.prepareStatement("update bbinfo set did=did-1 where did=?");
            snm=con.prepareStatement("select * from donor where name=?");
        }
        catch(Exception e1){}
    }

    public static ResultSet disp() throws Exception
    {
        rs=ds.executeQuery();
        return rs;
    }

    static void del() throws Exception
    {
        dl.setInt(1,did);
        dl1.setInt(1,did);
        dl.executeUpdate();
        dl1.executeUpdate();
        for(did=did+1;searchDon();did++)
        {
            updtDon();
        }
    }

    static boolean searchCul() throws Exception
    {
        ds1.setString(1,dt);
        rs=ds1.executeQuery();
        return rs.next();
    }

    static void upCul() throws Exception
    {
        uc.setString(1,dt);
        uc.executeUpdate();
    }

    static boolean searchDon() throws Exception
    {
        sd.setInt(1,did);
        rs=sd.executeQuery();
        return rs.next();
    }

    static void updtDon() throws Exception
    {
        ud.setInt(1,did);
        ud.executeUpdate();
        ubb.setInt(1,did);
        ubb.executeUpdate();
    }

    static ResultSet sName() throws Exception
    {
        snm.setString(1,name);
        rs=snm.executeQuery();
        return rs;
    }

}