package flightmanagementsystem;
import static flightmanagementsystem.FlightManagementSystem.atoi;
import static flightmanagementsystem.FlightManagementSystem.checkNumber;
import static flightmanagementsystem.FlightManagementSystem.checkTime2;
import static flightmanagementsystem.FlightManagementSystem.checkString;
import static flightmanagementsystem.FlightManagementSystem.flist;
import static flightmanagementsystem.FlightManagementSystem.plist;
import static flightmanagementsystem.FlightManagementSystem.qlist;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;
public class Person 
{
  int passportNo, tel;
  String name, surname, nationallity, address;
   LinkedList<Integer> flights = new LinkedList<Integer>();
  
  int getPassport() { return this . passportNo; }
		String getName() { return this . name; }
		String getSurname() { return this . surname; }
		
   
public void book()
  {
	
	/* ----INITIALISE VARS----*/
	/* --FLIGHTS-- */
	String temp; //temp to store user's input, to be checked
	int num; // stores flight's number, after successful check	
	/* --VARS FOR NON DIRECT FLIGHTS-- */
	int counter = 1; // stores the amount(>=2) of the non-direct flights
	String choice; // stores user's choice for adding or not more flights to their reservation
	Time tArriving = new Time(); 
	Time tLeaving = new Time(); 
	String Departure = new String(); 
	String Destination = new String(); 
	LinkedList<Integer> nums = new LinkedList<Integer>(); // store flights' numbers 
	 Iterator i1 = nums.iterator();
	/* --VAR FOR LOOPS-- */
	boolean flag = true;
	Scanner s = new Scanner(System.in);
	if (!FlightManagementSystem.flist.isEmpty()) {
		System.out.print( "Insert y (yes) for a new client or n (no) for an existing client. ");
		 choice =s.nextLine();
		
		// enter if client is new
		if ("y".equals(choice) || "Y".equals(choice))
                {
			
			System.out.println( "Please give us your personal info. " );
		
			/* --NAME-- */
			System.out.print( "Name: ");
			flag = false;
			
			// check input
			LOOP8:do{
			 this. name=s.nextLine();
				if ( (this. name.length() <= 10) && (checkString(this. name)) ){
					flag = true;
				}else 
                                {
					System.out.print( "Please insert a valid Name! ");					
				}
			}
                        while(!flag);
			
			/* --SURNAME-- */
			System.out.print( "Surname: ");
			flag = false;
			
			// check input
			LOOP9:do{
				 this. surname=s.nextLine();
				if ( (this. surname.length() <= 10) && (checkString(this. surname)) ){
					flag = true;
				}else {
					System.out.print( "Please insert a valid Surname! ");
					
				}
			}
                        while(!flag);
			
			/* --PASPPORT No-- */
			System.out.print( "Passport number: ");
			
			// check input
                        lp10 :
			LOOP10:do{
				
			 temp=s.nextLine();
				flag = true;
				
				if (!checkNumber(temp)){
					System.out.print( "Please insert a valid passport number" );
					flag = false;
					
				}
                                else if (!uniquePass( atoi(temp)))
                                        {
					System.out.print( "Please check the validity of your passport number" );
					flag = false;
					continue;
				}else{
					flag = true;
					this. passportNo = atoi(temp);
				}	
			}while(!flag);
			
			/* --NATIONALLITY-- */
			System.out.print( "Nationallity: ");
			flag = false;
			
			// check input
			LOOP11:do{
				
				 this. nationallity =s.nextLine();
				
				if ( (this. nationallity.length() <= 10) && (checkString(this. nationallity)) ){
					flag = true;
				}else {
					System.out.print( "Please insert a valid Nationallity! ");
					
				}
			}while(!flag);
			
			/* --ADDRESS-- */
			System.out.print( "Address: ");
			 this. address=s.nextLine();
			
			/* --TEL-- */
			System.out.print( "Telephone: "); 
			 temp=s.nextLine();
			
			// check input
			while (!checkNumber(temp)) {
				System.out.print( "Please insert a valid telephone number!" );
				 temp=s.nextLine();
			}
			this. tel = atoi(temp);
		}
                else
                { // existing customer
			System.out.print( "Pleas give us your passport No: ");
			 temp=s.nextLine();
			
			// check input
			while(!checkNumber(temp))
                        {
				System.out.print( "Please insert a valid passport number!" );
				flag = false;
				 temp=s.nextLine();
			}
				
			// check if passport No is unique
			if ( !(uniquePass( atoi(temp ))))
        {                        Person p;    
            
			     for (Iterator<Person> i= plist.iterator(); i.hasNext(); ) 
        
            {
                                             
                                                p=i.next();
						if (atoi(temp)==p.passportNo) 
                                                        {
							this. name = p. name;
							this. surname = p. surname;
							this. passportNo = p. passportNo;
							this. nationallity = p. nationallity;
							this. address = p. address;
							this. tel = p. tel;
							Integer ip2;
							for (Iterator<Integer> i2=p.flights.iterator(); i2.hasNext();)
                                                        {
                                                            ip2=(Integer)i2.next();
								this. flights.add(ip2);
							}
							
							// after copying the customers info,
							// delete the old object pointing to them
							 break;
						}
		}
	}
                        else
                        {
				System.out.print( "Wrong passport number!" );
				return;
			}
		}
		
		
		// display flights
		Flight.displaySchedule(); 
		
		/* --FLIGTH No-- */
		System.out.print( "\n\nEnter the number of the flight you 're intrested in: ");
		 temp=s.nextLine();
		flag = true;
		
		// check input
		while ( !checkNumber(temp) && !Flight.flightExists(atoi(temp)) )
                {
			System.out.print( "Please insert a valid Flight No!" );
			 temp=s.nextLine();
		}
		num = atoi(temp);
		
		/*
		------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ 
							BOOK CUSTOMER'S CHOSEN FLIGHT(S)	 
		------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		*/
		
		//DIRECT FLIGHT
		if(Flight.checkForSeats(num)){ // check for vacancy		
			this. flights.add(num);
			Flight.resSeat(num,1);
			System.out.print( "Your flight with No: " + num + " was successfully booked." );
		
		// NONO-DIRECT FLIGHT	
		}else{
			choice = "y";
			
			System.out.print( "There are no available direct flights for this destination." );
			System.out.print( "Please enter the special numbers for the alternative flights.");
			
			while(choice == "y" || choice == "Y"){ // exit if user doesn't want to add more flights
                    lp12 :	
		  LOOP12:
                  System.out.print( "\nNumber #" + counter + " : ");
				 temp=s.nextLine();
				
				// check input
				while ( !checkNumber(temp) && !Flight.flightExists(atoi(temp)) )
                                {
					System.out.print( "Please insert a valid Flight No!" );
					 temp=s.nextLine();
				}
				
				num = atoi(temp);	
								
				if (counter > 1){ // enter if user has entered 2 or more flights			
				
					// store the arrival, departure time and the departure, arriving city
					Flight f;
                                        for (Iterator<Flight> i2= flist.iterator(); i2.hasNext();)
                                        {
                                            Integer n;
                                            n=(Integer)i1.next();
                                      
                                            f=i2.next();
						if (n == f. getFlightNo()) {					
							tArriving = f. getArrivingTime();
							Destination = f. getDestination();
						}else if (num == f. getFlightNo()) {
							tLeaving = f. getLeavingTime();
							Departure = f. getDeparture();
						}
					}
					
					// check validity of each flight
					if (!(checkTime2(tLeaving,tArriving)))
                                        {
                                                
                                                if(Departure.compareTo(Destination)!=0) 
                                        {
						System.out.print( "Please enter a flight to a valid Destination!" );
						continue;	
					}}
				}
				
				nums.add(num); // add the flight's number		
				i1.hasNext();
				
				if(counter >= 2){ 
					System.out.print( "Do you want to add more flight numbers?(y/n) "); 
					 choice=s.nextLine();
				}
				
				counter++; 
			} 
			
			
			// check for vacancies in the chosen flights
			// and add all of the flight's numbers to the specific node of the list flights
			
                        for (Iterator i=nums.iterator();i.hasNext();)
                        {
                            Integer n;
                          n=(Integer)i.next();
                                      
				flag = Flight.checkForSeats(n);
				this. flights.add(n);
			}
			
			//enter ONLY if there are vacant seats and reserve a seat to each chosen flight
			if(flag){
                            Integer n;
				for (Iterator i=nums.iterator();i.hasNext();)
                                {
                                       
                                n=(Integer)i.next();
				Flight.resSeat(n,1);
				}
				System.out.print( "Your flights were successfully booked." );
			
			// if, at least, one flight is fully booked add the customer to the queues of each flight
                        }
                        else
                        {
				System.out.println();
				System.out.print( "Some of the flights you inserted were over booked." );
				Queue q;
				Integer n;
                                for (Iterator i=nums.iterator();i.hasNext();)
                                {
                                    n=(Integer)i.next();
                                   
					for (Iterator<Queue> i2= qlist.iterator(); i2.hasNext();){
					        q=i2.next();	
                                            if( n == q. getNo()){
							q. enqueue(this);
						}
					}
				}
				System.out.print( "We have added you in queues for each of these flights." );
			}
		}
		plist.add(this); // add object to plist
	
        }
        else
        {
		System.out.print( "There are no available flights at the moment." );
		return;
	}	
}
public void bookFromQueue(int num){  //is called only from cancel()
	
	boolean flag = true;
	Person queueFront = new Person(); // object of the customer added last
        Queue ab;
	// find the oldest customer
	for (Iterator<Queue> it= qlist.iterator(); it.hasNext();){
	ab=it.next();	
            if (num == ab. getNo() ){
			if( !(ab. isEmpty()) ){
				queueFront = ab. oldest();
				break;
			}else{
				return;
			}	
		}
	}
	
Integer c;	
// check if all of the customer's chosen flights have vacant seats
	for (Iterator i = queueFront.flights.iterator();i.hasNext();)
        {
            c=(Integer)i.next();
		flag = Flight.checkForSeats(c);
	}
	
	// enter ONLY if there are vacancies in ALL of the customer's chosen flights
	// and book them
	if(flag){			
		Node p = new Node(); 
		
		// find the flights of the oldest customer
                Integer a;
                Integer b;
		for (Iterator i =queueFront.flights.iterator(); i.hasNext();)
                {
			b=(Integer)i.next();
			// delete him from the queues
                    
			for (Iterator i2 =qlist.iterator(); i2.hasNext();)
                        {
                            a=(Integer)i2.next();
				if(b == qlist.get(a).getNo()){
					p = qlist.get(a).getFront();		
					while(p != null){					
						if(queueFront.passportNo == p . person.passportNo){
							qlist.get(a).dequeue();
							Flight.resSeat(b,1);	
						}							
						p = p.next;
					}
				}
			}
					
		}	
	}	
}
public void cancel()
{
	Scanner s = new Scanner(System.in);	
	String passport, num; 
	int counter = 1; //counter that stores user's wrong inputs
	boolean flightFound = false; // is false if customer has already booked a seat in a flight
	boolean isInQueue = false; //is false if the customer, that will be deleted, doesn't belong in
				// the queue of each flight
	
	if (!plist.isEmpty()) {
		// clean stream
		
		
		System.out.print( "Please give us your passport number: ");
		passport= s.nextLine();
                System.out.println(); 
		// check input
		while(!checkNumber(passport)){
			System.out.println("Please insert a valid passport number!");
			 passport= s.nextLine();
                        System.out.println(); 
		}
		
		while(!Person.displayPersonInfo( atoi(passport) )) 
                {
					
			//???d?? ap? t? p????aµµa a? d??e? ?a??asµ???? a???µ?? d?aßat????? 5 f????
			if (counter == 5) {
				System.out.println("Wrong passporst number was given too many times.");
				return;
			
                        }
                        else
                        {
				System.out.println("Please check your Passport Number." );
				passport= s.nextLine();
                                System.out.println();
				
				//??e???? a? ? a???µ?? d?aßat????? ap?te?e?ta? µ??? ap? ??f?a
				while(!checkNumber(passport)){
					System.out.println("Please insert a valid passport number!");
					passport= s.nextLine();
                                        System.out.println(); 
				}
			}			
			counter++;
		}
		
		System.out.println("\nWhich flight do you want to cancel (enter the flight number)? "); 
		num =s.nextLine();
		counter = 1;
		
		// check input
		while(!checkNumber(num)){
		System.out.println("Please insert a valid flight number!");
			 num =s.nextLine();
                         System.out.println(); 
		}
		Person pq;
		// iterate through the passenger's flights
		for (Iterator<Person> i = plist.iterator();i.hasNext(); )
                {
                    pq=i.next();
			if (atoi(passport) == pq . passportNo) {	
				
                            Integer n;
				// check input and exit if counter = 5
				do{										
					for (Iterator i2 = (pq. flights).iterator(); i2.hasNext();)
                                        {
                                            n=(Integer)i2.next();
						if (atoi(num) == n)
                                                {
							flightFound = true;
							pq.flights.remove(i2); // delete flight from passenger's info
							break;
						}
                                                
					}
					
					if (counter == 5){
						System.out.print(  "Wrong flight number was given too many times.");
						return;
					}
					else if(!flightFound)
					{
						System.out.print(  "Please check your flight number!.");
						  num=s.nextLine();
                                                  System.out.println( );
						
						// check input
						while(!checkNumber(num))
						{
						System.out.print(  "Please insert a valid flight number!" );
						  num=s.nextLine(); 
                                                  System.out.println( ); 
						}
					}				
					counter++;
				}while(!flightFound);
				break;
			}
		}
		Queue iq;
		// delete client from the queue of the flight, if they are a part of it
		for (Iterator<Queue> i= qlist.iterator(); i.hasNext();)
                {
                    iq=i.next();
			if( atoi(num) == iq .getNo() ){
				if(!iq . isEmpty()){
					Node p;			
					Node previousNode = new Node();
					p = iq . getFront();
					
					while(p!=null){
						if (atoi(passport) == p . person.passportNo){
							isInQueue = true;
							
							// enter if the client is on the first position
							if (p == iq . getFront()){
								iq. setFront(p. next);
								// enter if queue has only one element
								if (iq. getFront() == iq . getRear()){
									iq . setRear(null);
								}						
							}else if(p == iq. getRear()){ // enter if client is in the last poitsion of the queue
								previousNode . next = null;								
							}else{ 
								previousNode . next = p . next;								
							}
							
							break;						
						}
						previousNode = p;
						p = p . next;
					}
				}
			}
		}
		
		//enter if passenger is NOT part of the current queue
		if (!isInQueue){
			Flight.resSeat(atoi(num), -1); 		
		
			this . bookFromQueue(atoi(num));
		}
		System.out.println( "\nYour reservation was successfully removed! \n");
        }
        else 
        {
		System.out.println( "There are no registered clients at the moment!" );
	}
}
public static boolean displayPersonInfo(int passport){
	Person ps;
	for (Iterator <Person> i = plist.iterator(); i.hasNext();)
        {
            ps=i.next();
		
		if(passport == ps .passportNo){
			
			System.out.println(  "\n\t\t\t\t PERSONAL INFO" ) ;
			System.out.printf( "%-15s%-15s%-15s%-15s%-15s%-15s\n" , "NAME" , "SURNAME" , "PASSPORT_No" , "NATIONALLITY" , "ADDRESS" , "TELEPHONE" );
			
			System.out.printf("%-15s%-15s%-15d%-15s%-15s%-15d\n" ,ps. name , ps. surname , ps. passportNo , ps. nationallity , ps. address , ps. tel ) ;
			System.out.println( );
                        System.out.print(  "Your flight(s): ");
			Integer sp;
			for (Iterator i2 = (ps. flights.iterator()); i2.hasNext();)
                        {
				sp=(Integer)i2.next();
				System.out.print( sp + "  ");
			}
			System.out.println( );
			return true;
		}
	}
	 System.out.println("Passport number was not found. Check again!");
	return false;	
}
public boolean uniquePass(int passport)
{
    Person ap;
	for (Iterator<Person> i = plist.iterator(); i.hasNext();)
        {
            ap=i.next();
		if (passport == ap. getPassport()) 
                {
			return false;
		}
	}
	return true;
}

    

}
