package College.Students.DBConnections2Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import College.Students.Misc.Miscellaneous;

import java.sql.PreparedStatement;

import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnections {
	public static Connection con;
	public DBConnections()
	{
		//System.out.println("Loading driver...");

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		//    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}
public Connection createConnect(){

	

	String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false";
	String username = "root";
	String password = "Password";

	System.out.println("Connecting Mysql...");

	try{  
		con = DriverManager.getConnection(url, username, password); 
		
	    System.out.println("Mysql connected!");
//		con=dBConnect(con);
	return con;	
		
	} catch (SQLException e) {
	    throw new IllegalStateException("Cannot connect the database!", e);
	}
}
public Connection dBConnect(Connection con){
	String db="";
	System.out.println("Enter Database to join:");
	Scanner s=new Scanner(System.in);
	db=s.next();
	
	try {
		
	    Statement stmt = con.createStatement();
	    String exe="SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME =\'"+db+"\';";
	    ResultSet rs = stmt.executeQuery(exe);
	    rs=stmt.getResultSet();
	    if (rs.getString(1)==db){
	    	
	    }
	    else{
	    	while(true){
	    	System.out.println("No database matched with given db name \""+db+"\". Would like to create one? press 1 to proceed");
	    	int a=0;
	    	if(a==1){
	    	rs = stmt.executeQuery("Create database "+db+";");
	    	System.out.println(db+"Database created!");
	    	break;
	    	}
	    	
	    	}
	    }
	    System.out.println("Joined "+db+" database");
	   rs.close();
	}
	catch (SQLException ex){
	    // handle any errors
	    System.out.println("SQLException: " + ex);
	   }

	return con;


}

public Connection createTable(Connection con){
	try{  
	PreparedStatement ps= con.prepareStatement("create table studenttable (Name varchar(45) not null, Uid varchar(45) primary key, Marks varchar(45)not null);");

	    ps.executeUpdate();
		System.out.println("Table created!");
	
	ps.close();

return con;
} 
catch (SQLException e) {
throw new IllegalStateException("Cannot connect the database!", e);
}
	
}

public Connection insertRecords(Connection con,ArrayList arr) {
	// TODO Auto-generated method stub
	

try {
    Statement stmt = con.createStatement();
	
    String q="insert into studenttable values (\'"+arr.get(0)+"\', \'"+arr.get(1)+"\', \'"+arr.get(2)+"\');";
    
    PreparedStatement ps= con.prepareStatement(q);
    		ps.executeUpdate();
    System.out.println("Inserted");
    
	stmt.close();
	ps.close();
	
	   }
	catch (SQLException ex){
	   
	    System.out.println("SQLException: " + ex);
	   }
return con;
	

}
public Connection selectRecords(Connection con) {
	
	try {
	    Statement stmt = con.createStatement();
	    String exe="Select * from studenttable";
	    ResultSet rs = stmt.executeQuery(exe);
System.out.println("Name\t\tUid\t\tMarks");
	    if (stmt.execute("Select * from studenttable")) {
	      rs = stmt.getResultSet();
	    }
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();  
	    while(rs.next()){
	    	for(int i = 1; i <= columnsNumber; i++)
	    	{
	    	      System.out.print(rs.getString(i) + "\t\t"); 
	    	}

	    	  System.out.println();         

	    	    }
	   
	   rs.close();
	}
	catch (SQLException ex){
		System.out.println("SQLException: " + ex);
	   }
	return con;
}
public Connection deleteRecord(Connection con, int id) {
	// TODO Auto-generated method stub
	try{
	Statement stmt = con.createStatement();
    String sql = "DELETE FROM studenttable WHERE Uid = "+id;
    stmt.executeUpdate(sql);
    System.out.println("Deleted");
	
}
	catch (SQLException ex){
		System.out.println("SQLException: " + ex);
	   }
	return con;
}
public Connection updateRecord(Connection con, int id, String feild, String newValue) {
	// TODO Auto-generated method stub
	try{
	Statement stmt = con.createStatement();
	Miscellaneous m = new Miscellaneous();
	boolean a=m.isInt(newValue);
	String sql;
	if(a==true){
     sql = "UPDATE studenttable SET "+feild+" = \'"+newValue+"\' WHERE Uid = \'"+id+"\' ;";
	}
	else{

	     sql = "UPDATE studenttable SET "+feild+" = \'"+newValue+"\' WHERE Uid = \'"+id+"\' ;";
	}
		
    stmt.executeUpdate(sql);
    System.out.println("Updated");
}
	catch (SQLException ex){
		System.out.println("SQLException: " + ex);
	   }
	return con;
}
public Connection highestMarksInStudents(Connection con) {
	// TODO Auto-generated method stub
int highest=0;
	try {
	    Statement stmt = con.createStatement();
	    String exe="SELECT MAX(Marks) FROM studentTable";
	    ResultSet rs = stmt.executeQuery(exe);

	    if (stmt.execute("SELECT MAX(Marks) FROM studentTable")) {
	      rs = stmt.getResultSet();
	    }  
	    while(rs.next()){
	    	highest=Integer.parseInt(rs.getString(1)); 
	    }
	    String sql="SELECT * FROM studentTable where Marks=\'"+highest+"\';";
	    ResultSet rse = stmt.executeQuery(sql);
	    if (stmt.execute("SELECT * FROM studentTable where Marks=\'"+highest+"\';")) {
		      rse = stmt.getResultSet();
		    }
		    ResultSetMetaData rsmd = rse.getMetaData();
		    int columnsNumber = rsmd.getColumnCount(); 
		    System.out.println("Name\t\tUid\t\tMarks");
		    while(rse.next()){
		    	for(int i = 1; i <= columnsNumber; i++)
		    	{
		    	      System.out.print(rse.getString(i) + "\t\t"); 
		    	}

		    	  System.out.println();         

		    	    }

	   
	   
	}
	catch (SQLException ex){
		System.out.println("SQLException: " + ex);
	   }
	
	return con;
}
}
