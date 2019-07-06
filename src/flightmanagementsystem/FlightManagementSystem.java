package flightmanagementsystem;
import static flightmanagementsystem.menu.displayMenu;
import java.util.*;
public class FlightManagementSystem 
{
public static LinkedList<Flight> flist =new LinkedList<Flight>(); // store the flights of the system
public static LinkedList<Person> plist =new LinkedList<Person>(); // store the passengers 
public static LinkedList<Queu6e> qlist =new LinkedList<Queue>(); // store the customers in the flights' waiting queues


     public static  boolean checkTime(String time) { // check the validity of the time (must be xx:xx, where x = Natural number)
	
	
	if(!time.isEmpty()){
		for (int i=0; i < time.length(); i++)
                {		
			if (i==2)
                        {
				if (time.charAt(i) != ':') {
				return false;
				}
				
			}
                        else if ( !(time.charAt(i) >= '0' && time.charAt(i) <= '9') ) 
                        {
				return false;
			}				
		}
		return true;
	}
        else
        { 
		return false;
	}
	
}
      public static boolean checkTime2(Time tLeaving, Time tArriving){	// checks the validity of arrival and departure time
	if(tLeaving.hour > tArriving.hour)
        {
		return false;
	}
        else if(tLeaving.hour == tArriving.hour)
        {
		if(tLeaving.min > tArriving.min)
                {
			return false;
		}
	}
	return true;	
}
    public static int atoi(String str)
    {
if (str == null || str.length() < 1)
return 0;
str = str.trim();
char flag = '+';
int i = 0;
if (str.charAt(0) == '-') {
flag = '-';
i++;
} else if (str.charAt(0) == '+') {
i++;
}
double result = 0;

while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
result = result * 10 + (str.charAt(i) - '0');
i++;
}
if (flag == '-')
result = -result;
// handle max and min
if (result > Integer.MAX_VALUE)
return Integer.MAX_VALUE;
if (result < Integer.MIN_VALUE)
return Integer.MIN_VALUE;
return (int) result;
}
    public static boolean checkNumber(String s)
    {
        if(!s.isEmpty()){
		for (int i = 0; i < s.length(); i++){
			if ( ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) == ' ')) )
                        {
				return false;
			}
		}
	
		// check if string is a postive number
		if ( atoi(s) > 0 )
                {
			return true;
		}
                else
                {
			return false;
		}
	}else{
		return false;
	}

    }
  public static boolean checkString(String s)
    {
	
	if(!s.isEmpty()){
		for (int i = 0; i < s.length(); i++)
                {
			if ( !((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) )
                        {
				return false;
			}       
		}
		return true;
	}
	else
        { 
		return false;
	}
	
	
}    
      
    public static void main(String[] args)
    {
        displayMenu();
        
    }
    
}
