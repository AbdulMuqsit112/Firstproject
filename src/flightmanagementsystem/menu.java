
package flightmanagementsystem;
import java.util.Scanner;
import static flightmanagementsystem.FlightManagementSystem.atoi;
import static flightmanagementsystem.FlightManagementSystem.checkNumber;
import static flightmanagementsystem.FlightManagementSystem.flist;
import static flightmanagementsystem.FlightManagementSystem.plist;
import static java.lang.System.exit;

public class menu 
{
   public static  void displayMenu()
    {
	Scanner s = new Scanner(System.in);
	int selection;	// user's menu choice
	String temp="/0"; // temp to store user's input
	
	do{
			System.out.println( "-----------------------------------------------");
			
			System.out.println( "\n\t AIRLINE RESERVATION SYSTEM ");
			System.out.println( "Please pick an option from the menu below. ");
			System.out.println( "1. Add new flights ");
			System.out.println( "2. Delete flights ");
			System.out.println( "3. Display flight schedule ");
			System.out.println( "4. Display flight details ");
			System.out.println( "5. Display passenger personal info ");
			System.out.println( "6. Book reservation ");
			System.out.println( "7. Cancel reservation ");
			System.out.println( "8. Exit \n");
			
			System.out.println( "-----------------------------------------------" );
			
			System.out.println( "Enter the number of a menu option: ");
			temp=s.nextLine();
			
			// check validity of input
			while (!checkNumber(temp)) {
				System.out.println( "Please enter a number!" );
				temp=s.nextLine();
			}
			System.out.println( );
			
			selection = atoi(temp);
			select(selection);
	
	}while(true);
}

public static void select(int selection)
{
	Scanner s = new Scanner(System.in);
	Flight f =new Flight(); // FLight's object
	Person p=new Person(); // class Person's object
	String temp; // temp to store input
	
	switch(selection){
		case 1:
			f.addFlight();
			break;
		case 2:
			if (!flist.isEmpty()) {
				System.out.print( "Enter the Flight Number you want to delete: ");
                                temp=s.nextLine();
				System.out.println( );
				while (!checkNumber(temp)){
					System.out.println( "Flight Number must be a number!" ); 
					temp=s.nextLine();
					System.out.println();
				}
				
				Flight.deleteFlight( atoi(temp));
			}else {
				System.out.println( "There are no flights to delete" );
			}
						
			break;
		case 3:
			if (!flist.isEmpty()) {
				Flight.displaySchedule();
			}else {
				System.out.println( "There are no scheduled flights!" );
			}
			break;
		case 4:
			if (!flist.isEmpty()) {
				System.out.print( "Please insert flight's number: ");
				temp=s.nextLine();
                                System.out.println( );
				while (!checkNumber(temp)){
					System.out.println( "Flight number must be a number!" ); 
					temp=s.nextLine();
					System.out.println( );
				}
				Flight.displayFlightInfo( atoi(temp) );
			}else {
				System.out.println( "There are no scheduled flights!" );
			}			
			break;
		case 5:
			if (!plist.isEmpty()){
				System.out.print( "Please insert passport number: ");
				temp=s.nextLine();
				while (!Person.displayPersonInfo( atoi(temp)))
                                        {
					System.out.println( "Wrong passport number!" );
					
					temp=s.nextLine();
					System.out.println( );
				}
			}else
                        {
				System.out.println( "There are no registered clients at the moment!" );
			}
			break;
		case 6:
			p.book();
			break;
		case 7:
			p.cancel();
			break;
		case 8:
			exit_prog();
			break;
		default:
			System.out.println( "Invalid selection "); 
	}
	
}

public static void exit_prog(){
	
	System.out.println( "Thank you for using our system! ");
	exit(1); // exit
}

    
}
