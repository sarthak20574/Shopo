package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;




public class querries
{
	
	static int cartids = 130;
	static Connection con;
	public static void connect(String args[])throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/project";
		String username = "root";
		String password = "x1x2x3x4";
		//String querry = "SELECT * FROM project.Product WHERE p_id<10;";		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");	
		}catch(ClassNotFoundException e )
		{
			e.printStackTrace();	}
		try 
		{	con = DriverManager.getConnection(url, username, password); 
			System.out.println("connected successfully");
		}catch(SQLException e)
		{	e.printStackTrace();	}
		
	}
	public static void displayresult(ResultSet result) throws SQLException 
	{ 
		ResultSetMetaData rsmd = result.getMetaData();
		while(result.next())
		{
			String u = "";
			for(int i=1;i<=rsmd.getColumnCount();i++)
			{
				u+=result.getString(i)+" :"; 
			}
			System.out.println(u);
		}
	}	
	public static ResultSet executequerry(String querry)
	{
		ResultSet result = null;
		try 
		{
			Statement statement = con.createStatement();
			result = statement.executeQuery(querry);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		return result;
	}
	public static void executequerry2(String querry)
	{
		try 
		{
			Statement statement = con.createStatement();
			statement.executeUpdate(querry);
			System.out.println("Executed successfully");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
			
		}
	}	

	public static ResultSet search(String pname)
	{
		String querry = "SELECT * FROM Product WHERE p_name = '"+pname+"'";
		return executequerry(querry);
	}
	public static ResultSet searchcat(String pname)
	{
		System.out.print("procedding "+pname);
		String querry = "SELECT * FROM Product WHERE p_id IN( SELECT P_id from categorising where cat_name = " + ("'"+pname+"'")+ " );";
		return executequerry(querry);
	}
	public static ResultSet getproductdetails(String name) throws SQLException
	{
		String querry = "SELECT * FROM Product WHERE p_name = "+"'"+name+"'";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(querry);
		//displayresult(result);
		return result;
	}
	public static void displayallproduct() throws SQLException 
	{
		String querry = "SELECT * FROM Product ";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(querry);
		displayresult(result);
	}
	public static ResultSet displayallcategories() throws SQLException {
		String querry = "SELECT * FROM Category";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(querry);
		return result;
	}
	
	public static int getcartid(long cid) 
	{
		try {
		String querry = "SELECT * FROM project.makes WHERE c_phone = "+cid+";";
		ResultSet result = executequerry(querry);
		if(result.next())
		{
			int cart;
				cart = Integer.parseInt(result.getString(1));
				return cart;
		}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return 0;
	}
	public static ResultSet getcart(long cart) throws SQLException
	{
		String querry2 = "SELECT * FROM project.Cart WHERE c_id = "+cart+";";
		ResultSet result2 = executequerry(querry2);
		return result2;
	}
	public static ResultSet getprofiledata(long cid)
	{
		System.out.println("cid-> "+cid);
		String querry = "SELECT * From project.Customer WHERE c_phone = "+cid;
		ResultSet result = executequerry(querry);
		return result;
	}
	public static boolean changeaddres(long cid ,String add) 
	{
		try {
			String querry = "UPDATE Customer SET c_address = '"+add+"'  WHERE c_phone = "+cid+" ;";
			System.out.println(querry);
			Statement statement = con.createStatement();
			boolean b;
			b = statement.execute(querry);
			System.out.println(b);
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	public static boolean changemail(long cid ,String mail) 
	{
		try {
			String querry = "UPDATE Customer SET c_email = '"+mail+"'  WHERE c_phone = "+cid+" ;";
			System.out.println(querry);
			Statement statement = con.createStatement();
			boolean b;
			b = statement.execute(querry);
			System.out.println(b);
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	public static boolean changename(long cid ,String name) 
	{
		try {
			String querry = "UPDATE Customer SET c_name = '"+name+"'  WHERE c_phone = "+cid+" ;";
			System.out.println(querry);
			Statement statement = con.createStatement();
			boolean b;
			b = statement.execute(querry);
			System.out.println(b);
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	public static boolean changedob(long cid ,String dob) 
	{
		try {
			String querry = "UPDATE Customer SET c_DOB = '"+dob+"'  WHERE c_phone = "+cid+" ;";
			System.out.println(querry);
			Statement statement = con.createStatement();
			boolean b;
			b = statement.execute(querry);
			System.out.println(b);
			return true;
		} catch (SQLException e) {e.printStackTrace(); return false;}
	}
	public static int newcart(long uid)
	{
		cartids+=1;
		String querry  = "INSERT INTO  `Cart` VALUES ( '"+(cartids)+"', 0 );";								
		String querry2  = "INSERT INTO `makes` VALUES ( "+cartids+", "+uid+" );";								
		System.out.println(querry);
		System.out.println(querry2);
		executequerry2(querry);
		executequerry2(querry2);
		return (cartids);
	}
	public static boolean addtocart(int cid ,int pid,int q)
	{
		String querry  = "INSERT INTO `consists_of` VALUES ( "+pid+", "+q+", \"2022/04/28\","+cid+");";					
		System.out.println(querry);
		executequerry2(querry);
		return false;
	}
	public static boolean checkcart(int pid ,int cid)
	{
		String querry = "SELECT * FROM `consists_of` WHERE cart_ID = "+cid +" AND p_id = "+pid+";" ;									
		ResultSet result = executequerry(querry);
		try {
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public static boolean updatequantity(int cid,int pid,int q)
	{
		
		String querry  = "";					
		System.out.println(querry);
		executequerry2(querry);
		return false;
	}
	
	public static String getname(long uid)
	{
		try {
		String querry = "SELECt * FROM `Customer` WHERE c_phone = "+uid+" ;";
		ResultSet result = executequerry(querry);
			if(result.next())
			{
				return result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}