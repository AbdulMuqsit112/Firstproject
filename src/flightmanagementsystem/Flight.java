package flightmanagementsystem;
import static flightmanagementsystem.FlightManagementSystem.atoi;
import static flightmanagementsystem.FlightManagementSystem.checkNumber;
import static flightmanagementsystem.FlightManagementSystem.checkString;
import static flightmanagementsystem.FlightManagementSystem.checkTime;
import static flightmanagementsystem.FlightManagementSystem.flist;
import static flightmanagementsystem.FlightManagementSystem.qlist;
import static flightmanagementsystem.Queue.display;
import java.util.Scanner;
import java.util.*;

public class Flight
{
    int flightNo, cost, seats, booked_seats;
    String from, to, plane_type;	
    Time t_leave, t_arrive;
		
   int getFlightNo() { return this . flightNo; }
		Time getLeavingTime() { return this . t_leave; }
		Time getArrivingTime() { return this . t_arrive; }
		String getDeparture() { return this . from; }
		String getDestination() { return this . to; }

    
 public void addFlight() 
 {
        boolean  flag = false; 
	
	ArrayList<String> fields = new ArrayList(); 
	String temp; 
        Scanner s = new Scanner(System.in);
	String stk;  
	int hour; 
	int min; 

	System.out.print( "Add new flights by giving the following attributes: \n");
	System.out.println( "Flight Number: "); 
	temp=s.nextLine();
	
	do{
		flag = true;
		
		// check input
		if (!checkNumber(temp))
                {
			System.out.println("Please insert a valid Flight Number! " );
			flag = false;
			temp=s.nextLine();
		}
                else if(flightExists( (atoi(temp))))
                {
			System.out.println("This Flight already exists!");
			System.out.println( "Please insert a valid Flight Number!" );
			flag = false;
			temp=s.nextLine();
		}
                        else 
                {
			flag = true;
			this.flightNo = atoi(temp);
		}
	}
        while(!flag);
	
	
	System.out.println("Departure: ");
	flag = false;
	
	LOOP:do
        {
		temp=s.nextLine();
		if ( (temp.length() <= 10) && (checkString(temp)) )
                {
			this.from = temp;
			flag = true; 
                }
                else
                {
			System.out.println("Please insert a valid Departure city! ");
			
		}
	}
        while(!flag);
	System.out.println("Destination: "); 
	flag = false;
	
	LOOP2:do
        {
		temp=s.nextLine();
		if ( (temp.length() <= 10) )
                {
                    if(checkString(temp))
                    {
                     if(temp.compareTo(this.from)!=0)
                     {
                        
                        this.to = temp;
			flag = true;
                     }
                    }
                }
			
                
                else
                {
			System.out.print( "Please insert a valid Destination city! ");
			
		}
	}
        while(!flag);
	
	/* --DEPARTURE TIME-- */
	System.out.println( "Boarding time (e.g. 19:40): "); 
	flag = false;
	
	// check input
        lp3 :
	LOOP3:do
        {
		temp=s.nextLine();
		
		if( temp.length() != 5 || !checkTime(temp) )
                {
			System.out.println("Please insert a valid boarding time (e.g. 19:40)! ");
			continue ;			 
		}
		
		String t_temp;
                
                
                
                    
		t_temp= temp;
		StringTokenizer st1 = new StringTokenizer(t_temp, ":");
		stk = st1.nextToken();
		fields.add(stk); 	
		hour = atoi(fields.get(0)); 
		stk = st1.nextToken();
                fields.add(stk);
                min = atoi(fields.get(1)); 
		
	
		if ((hour >=0 && hour<=23) && (min>=0 && min <=59)){ 
			this.t_leave.hour = hour; 
			this. t_leave.min = min; 
			flag = true;
		}
                else
                {
			System.out.println( "Please insert a valid boarding time (e.g. 19:40)! ");
			fields.clear();		
		}	
		
	}
        while(!flag); 
	
	/* --ARRIVAL TIME-- */
	System.out.println("Arriving time (e.g. 21:40): ");
	flag = false;
	fields.clear();	// clear fields (because it was used before, at "DEPARTURE TIME")
	
	// check input
        lp4 :
	LOOP4:do{
		temp=s.nextLine();
		
		if( temp.length() > 5 || !checkTime(temp) )
                {
			System.out.println("Please insert a valid boarding time (e.g. 19:40)! ");
			continue ;			 
		}
		
		String t_temp;
		t_temp= temp;
		StringTokenizer st1 = new StringTokenizer(t_temp, ":");
		stk = st1.nextToken();
		fields.add(stk); 
		hour = atoi(fields.get(0));
                stk = st1.nextToken();	
		 fields.add(stk);
		min = atoi(fields.get(1));
		
		
		// check validity of time
		if ((hour >=0 && hour<=23) && (min>=0 && min <=59)){ 
			this. t_arrive.hour = hour; 
			this. t_arrive.min = min; 
			flag = true;
		}else{
			System.out.println("Please insert a valid arriving time (e.g. 19:40)! ");
			fields.clear();		
		}	
		
	}while(!flag); 
	
	/* --TICKET COST-- */
	System.out.println("Ticket price: ");
        lp5 :
	LOOP5:do{
		temp=s.nextLine();
		flag = true;
		
		// check input
		if (!checkNumber(temp)){
			System.out.println( "Please insert a valid ticket price!");
			flag = false;
			continue;
		}
                else
                {
			flag = true;
			this.cost = atoi(temp);
		}
	}
        while(!flag);
	
	/* --AIRCRAFT TYPE-- */
	System.out.println("Aeroplane type: ");
	this.plane_type=s.nextLine();
	while(this.plane_type.isEmpty()){
		System.out.println("Please insert a valid Aeroplane type!");
		this.plane_type=s.nextLine();
	}
	
	/* --No OF SEATS-- */
	System.out.println( "Number of seats: ");
        lp6 :
        LOOP6:do{
		
		temp=s.nextLine();
		flag = true;
		
		// check input
		if (!checkNumber(temp))
                {
			System.out.println("Please insert a valid number of seats!");
			flag = false;
			continue;
		}
                else
                {
			flag = true;
			this.seats = atoi(temp);
		}
	}while(!flag);
	
	System.out.println("Number of booked seats: ");
        lp7 :
	LOOP7:do{
		
		 temp=s.nextLine();
		flag = true;
		
		// check input
		if (!checkNumber(temp)){
			System.out.println("Please insert a valid number of booked seats!" );
			flag = false;
			continue;
		}
                else if ( atoi(temp) > this. seats ) {
			System.out.println("Booked seats must be less than plane's seats!" );
			flag = false;
			continue;
		}
                else
                {
			flag = true;
			this. booked_seats = atoi(temp);
		}
	}while(!flag);
	System.out.println();
	
	
	FlightManagementSystem.flist.add(this); // add object to the flist
	
	Queue q = new Queue(this. flightNo); 
	qlist.add(q); // add object to the qlist
	
	System.out.println("Flight No: "+ this.flightNo +" was successfully added!");
	
}
public static void deleteFlight(int num){
		Queue a;
		for (Iterator<Queue> i= qlist.iterator(); i.hasNext();)
                {
                    a=i.next();
			if( num == a. getNo() )
                        {
				
				// enter if waiting queue for the flight is NOT empty
				if (a.isEmpty()) 
                                {
					// delete object from flist
                                        Flight f;
					for (Iterator<Flight> i2= flist.iterator(); i2.hasNext();)
                                        {
                                            f=i2.next();
						if( num == (f . flightNo) )
                                                {
							i2.remove();
							i.remove();
							System.out.println("Flight with number: " + num +" was successfully deleted");
							return;
						}
					}
				}
                                else
                                {
					System.out.println("There are passengers in the queue of the flight with No: "+num );
					System.out.println("Remove ALL of them from the queue first!");
					return;
				}
			}
		}
		System.out.println("This flight number doesn't exist!");
		return;					
}
public static void displayFlightInfo(int num)
{

	String l_time, a_time; // departure and arrival time
	Flight f;
	for (Iterator<Flight> i = flist.iterator();i.hasNext();) 
        {
            f=i.next();
		if (num == f . flightNo)
                
                {	
                     l_time=String.valueOf(f.t_arrive.hour);
                     l_time=l_time+":";
                     l_time=l_time+String.valueOf(f.t_arrive.min);
                     a_time=String.valueOf(f.t_arrive.hour);
                     a_time=a_time+":";
                     a_time=a_time+String.valueOf(f.t_arrive.min);
		
                    
			System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n" , "FLIGHT" , "FROM" , "TO" ,"LEAVE" ,"ARRIVE" , "COST","TYPE" ,"SEATS" , "BOOKED" );
			System.out.printf("%-10d%-10s%-10s%-10s%-10s%-10d%-10s%-10d%-10d\n" , f . flightNo , f . from , f . to , l_time , a_time , f . cost , f . plane_type , f . seats , f . booked_seats );
			
			display(num);
			return;
		}
	}	
	
	System.out.println("Invalid number of flight was given.");
}
public static void displaySchedule(){
	
	String l_time, a_time; // departure and arrivale time
	
	 
		System.out.println( "\n\t\t\t\t FLIGHT SCHEDULE");
                System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n","FLIGHT" , "FROM",   "TO",  "LEAVE"  , "ARRIVE"  , "COST"  , "TYPE"  , "SEATS"  , "BOOKED");
	
		Flight f;
		for (Iterator<Flight> i = flist.iterator();i.hasNext();)
                {
                     f=i.next();
                     l_time=String.valueOf(f.t_arrive.hour);
                     l_time=l_time+":";
                     l_time=l_time+String.valueOf(f.t_arrive.min);
                     a_time=String.valueOf(f.t_arrive.hour);
                     a_time=a_time+":";
                     a_time=a_time+String.valueOf(f.t_arrive.min);
			
                     
				 System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", f. flightNo , f. from , f. to , l_time,  a_time ,  f. cost , f. plane_type , f. seats ,f. booked_seats);
		
			}
		
		System.out.println();
}
public static void resSeat(int num, int val)
{
    Flight f;
	for (Iterator<Flight> i = flist.iterator();i.hasNext();)
                {
                     f=i.next();
                 	if( num == (f . flightNo) ){			
			f. booked_seats += val ;
			break;
		}
	}	
}
public static boolean flightExists(int num)
{
    Flight f;
	for (Iterator<Flight> i = flist.iterator();i.hasNext();)
        {
                     f=i.next();
		if (num == f. flightNo)
                {
			return true;
		}
	}
	return false;
}
public static boolean checkForSeats(int num)
{
    Flight f;
    
	for (Iterator<Flight> i = flist.iterator();i.hasNext();)
        {
                     f=i.next();
                 	if( num == (f. flightNo) )
                {
				if( f. seats == f. booked_seats )
					return false;
				else
					return true;
		}
                        
                            
	}
        return false;
}


		 
}
