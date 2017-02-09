package College.Students;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import College.Students.DBConnections2Mysql.DBConnections;

public class DBOperations {

	public static Connection con;
	DBConnections dbc;
	
	
	public DBOperations(){
			dbc =new DBConnections();
			con=dbc.createConnect();

			}
	
	public void create(){
		int check;
		while(true){
		System.out.println("Is this the first time entering records? press 1 if yes! press 0 if no");
		Scanner se=new Scanner(System.in);
		check=se.nextInt();
		
		if(check==1||check==0){
			break;
		}
		}
		if(check==1){
			con=dbc.createTable(con);
		}
		while(true)
		{
			Scanner s=new Scanner(System.in);

			ArrayList arr=new ArrayList();
			
			System.out.println("Enter Student name:");
			String name=s.next();
			
			arr.add(name);
			
			System.out.println("Enter unique identification number:");
			int num=s.nextInt();
			arr.add(num);
			
			System.out.println("Enter total marks:");
			int marks=s.nextInt();
			arr.add(marks);
			
			con=dbc.insertRecords(con,arr);
			
			System.out.println("Want to enter more records? Press 1 to continue and 0 to stop");
			arr.clear();
			int k=2;
			k=s.nextInt();
			if(k==1){
				continue;
			}
			if(k==0){
				
				break;
			}
	}
		
	}
		public void delete(){
			System.out.println("Enter uin to delete");
			Scanner sca=new Scanner(System.in);
			int id=sca.nextInt();
			sca.close();
			con=dbc.deleteRecord(con,id);
			
		
	}
	public void select() {
		
		con=dbc.selectRecords(con);
		
	}
		
	

	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Enter uin to update");
		Scanner scaan=new Scanner(System.in);
		int id=scaan.nextInt();
		System.out.println("Enter the number of corresponding feild you want to update");
		System.out.println("1. Name");
		System.out.println("2. Uid");
		System.out.println("3. Marks");
		int choice=scaan.nextInt();
		String feild;
		String newValue;
		switch(choice){
		case 1:{
			feild="Name";
			System.out.println("Enter the updated value for "+feild);
			Scanner sn=new Scanner(System.in);
			newValue=sn.nextLine();
			sn.close();
			con=dbc.updateRecord(con,id,feild,newValue);
			break;
		}
		case 2:{
			feild="Uid";
			System.out.println("Enter the updated value for "+feild);
			newValue=String.valueOf(scaan.nextInt());
			con=dbc.updateRecord(con,id,feild,newValue);
			break;
		}
		case 3:{
			feild="Marks";
			System.out.println("Enter the updated value for "+feild);
			newValue=String.valueOf(scaan.nextInt());
			con=dbc.updateRecord(con,id,feild,newValue);
			break;
		}
		default:{
			System.out.println("You entered wrong choice. Please try again.");
		}
		scaan.close();
		}
		
		
	}
	

	public void highestMarks() {
		// TODO Auto-generated method stub

		con=dbc.highestMarksInStudents(con);
		
		
	}
	protected void finalize(){
		System.out.println("bye from DBO");
	}
}
