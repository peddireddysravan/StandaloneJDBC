/**
 * 
 */
package College.Students;

import java.util.Scanner;

/**
 * @author Sravan
 *
 */
public class Student {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc=new Scanner(System.in);
int a=0;
DBOperations d=new DBOperations();

		while(true){
			System.out.println("Please choose any of the following:");
			System.out.println("1. Create");
			System.out.println("2. Select");
			System.out.println("3. Delete");
			System.out.println("4. Update");
			System.out.println("5. Highest Marks");
			a=sc.nextInt();
			if(a==1){
				
				d.create();
				
			}
			else if(a==2){
				d.select();
				
			}
			else if(a==3){
				d.delete();
				
			}
			else if(a==4){
				d.update();
			}
			else if(a==5){
				d.highestMarks();
			}
			else{
				System.out.println("Incorrect choice!!");
			}
			
		} 
		
	}

}
