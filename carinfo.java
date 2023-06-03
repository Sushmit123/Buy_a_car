package JDBC_DML;

import java.sql.*;
import java.util.Scanner;

public class carinfo {
	String fqcn="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306?user=root&password=root";
public static void main(String[] args) {
	
    Scanner sc = new Scanner(System.in);
	System.out.println("WELCOME TO CAR-WALE");
	System.out.println("press 1 to Buy Car");
	System.out.println("press 2 to Sell Car");
	System.out.println("press 3 to See Upcoming car");
	System.out.println("press 4 to See all car");
	
//String qry1="insert into carinfo.car values(3001,'Alto','5lakh','white')";
//	String qry2="insert into carinfo.car values(3011,'Swift','7lakh','Red')";
//	String qry3="insert into carinfo.car values(3111,'I20','10lakh','Grey')";
//	String qry4="insert into carinfo.car values(3112,'Jimmy','18lakh','green')";
//	String qry5="insert into carinfo.car values(3113,'Thar','27lakh','white')";
	
//	try {
//		Class.forName(fqcn);
//		Connection con=DriverManager.getConnection(url);
//		PreparedStatement pstmt = con.prepareStatement(qry6);
//		pstmt.setString(1,cname);
//		System.out.println("Which car do you want");	
//		ResultSet rs = pstmt.executeQuery();
		//Statement stmt = con.createStatement();
//		stmt.executeUpdate(qry1);
//		stmt.executeUpdate(qry2);
//		stmt.executeUpdate(qry3);
//		stmt.executeUpdate(qry4);
//		stmt.executeUpdate(qry5);
//		System.out.println("data inserted");
	
//	String fqcn="com.mysql.jdbc.Driver";
//	String url="jdbc:mysql://localhost:3306?user=root&password=root";
//} catch (Exception e) {
//	
//	e.printStackTrace();
//}
//}
	
	int car = sc.nextInt();
	switch(car){
	case 1:{
		buy b = new buy();
		b.buy1();
	}
	break;
	case 2 :{
		sell s = new sell();
		s.sell1();
		}
	break;
	case 3 :{
		see_upcoming_car up = new see_upcoming_car();
		up.see_upcoming_car1();
	}
	break;
	case 4 :{
		see_all_car sa = new see_all_car();
		sa.see_all_car1();
	}
	}
	}

}



class buy extends carinfo{
	public void buy1() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Which car do you want ?");
		String qry = "select * from carinfo.car where cname=?";
	    String cname = sc.next();	
		
	    ResultSet rs = null;
	    PreparedStatement pstmt = null;
	    Statement stmt = null;
	    Connection con = null;
	    
		try {
			Class.forName(fqcn);
			 con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			 pstmt = con.prepareStatement(qry);
			pstmt.setString(1, cname);
			 rs = pstmt.executeQuery();
			if(rs.next())
			{
				int carno = rs.getInt(1);
				String carname = rs.getString(2);
				String cprice = rs.getString(3);
				String ccolor = rs.getString(4);
				//printing details of car 
				System.out.println("Car no=> "+carno);
				System.out.println("Car name=> "+carname);
				System.out.println("Car price=> "+cprice);
				System.out.println("Car color=> "+ccolor);
				
				System.out.println("Do you want to buy then press 1");
				int buy = sc.nextInt();
				switch(buy){
				case 1:{
					System.out.println("sucessful buy");
					String qry1 = "Delete from carinfo.car where cno = "+carno;
					stmt.executeUpdate(qry1);
					
					
				}
				break;
				
				default : System.out.println("wrong input");
				}
				
			}
			else {
				System.out.println("car not found");
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(rs!=null)
			{
				try {
					rs.close();
					System.out.println("resultset closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
					System.out.println("Prepared statement closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null)
			{
				try {
					stmt.close();
					System.out.println("statement closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
					System.out.println("connection closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		}
}



class sell extends carinfo{
public void sell1() {
	
		System.out.println("Give me details of car in the sequence ");
		System.out.println("sequence -> Car no , Car name , Car price , Car color");
		Scanner sc= new Scanner(System.in);
		int cno = sc.nextInt();
		String cname = sc.next();
		String cprice = sc.next();
		String ccolor = sc.next();
		
		String qry = "insert into carinfo.car values (?,?,?,?)";
		
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			Class.forName(fqcn);
			 con =DriverManager.getConnection(url);
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, cno);
			pstmt.setString(2,cname);
			pstmt.setString(3,cprice);
			pstmt.setString(4,ccolor);
			
			pstmt.executeUpdate();
			
			System.out.println("your car details is successful save ");
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null)
			{
				try {
					pstmt.close();
					System.out.println("preparedstatement closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
					System.out.println("connection closed");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
	}
}

class see_upcoming_car extends carinfo{
	
public void see_upcoming_car1() {
	
	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;
	
	
	try {
		Class.forName(fqcn);
	 con = DriverManager.getConnection(url);
	 stmt = con.createStatement();
	 rs = stmt.executeQuery("select * from carinfo.upcomingcar");
		System.out.println("car no   car name   car price    car color");
		System.out.println("================================================");
		while(rs.next())
		{
			int cno = rs.getInt(1);
			String cname =  rs.getString(2);
			String cprice = rs.getString(3);
			String ccolor = rs.getString(4);
			
			System.out.println(cno + "     "+cname + "      "+cprice + "        " + ccolor ); 
			
		}System.out.println("======================================================");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		if(rs!=null)
		{
			try {
				rs.close();
				System.out.println("resultset closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt !=null)
		{
			try {
				stmt.close();
				System.out.println("statement closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null)
		{
			try {
				con.close();
				System.out.println("connection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
}



class see_all_car extends carinfo{
public void see_all_car1() {
	
	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;
	
	try {
		Class.forName(fqcn);
		con = DriverManager.getConnection(url);
		 stmt = con.createStatement();
		 rs = stmt.executeQuery("select * from carinfo.upcomingcar");
		
		
		System.out.println("Cars in shop");
		System.out.println();
		System.out.println("Car no   Car name   Car price    Car color");
		System.out.println("================================================");
		while( rs.next())
		{
			int cno = rs.getInt(1);
			String cname =  rs.getString(2);
			String cprice = rs.getString(3);
			String ccolor = rs.getString(4);
			
			
			System.out.println(cno + "     "+cname + "      "+cprice + "        " + ccolor ); 
			//System.out.println("======================================================");
			
		}
		
		ResultSet rs1 = stmt.executeQuery("select * from carinfo.car");
		System.out.println(" ");
		System.out.println("Upcoming cars");
		System.out.println();
        System.out.println("car no   car name   car price    car color");
     	System.out.println("================================================");
		while(rs1.next())
		{
			int cno = rs1.getInt(1);
			String cname =  rs1.getString(2);
			String cprice = rs1.getString(3);
			String ccolor = rs1.getString(4);
			
			
			System.out.println(cno + "     "+cname + "      "+cprice + "        " + ccolor ); 
			
		}
		System.out.println("======================================================");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		if(rs!=null)
		{
			try {
				rs.close();
				System.out.println("resultset closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try {
				stmt.close();
				System.out.println("statement closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
				System.out.println("connection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}