package flightmanagementsystem;

import static flightmanagementsystem.FlightManagementSystem.qlist;
import java.util.Iterator;

public class Queue // class that stores passengers on each flight's queue
{



	int queueNo;
        Node rear;
        Node front;	
	
	
		
		/* --ADD/REMOVE ELEMENTS-- */
       	Node getFront()
        { return this . front; }
	Node getRear()
        { return this . rear; }
        /* --SETTERS-- */

void setFront(Node f) { 
			if (f == null){
				this . front = null;
			}else{
				this . front = f; 
			}
		}void setRear(Node r) {
			this . rear = r; 
		}        
Queue(int queueId) {
                     this . queueNo = queueId;
                     this . rear = null;
                     this . front = null;
}
void  enqueue(Person p){
	
    Node temp = new Node();

    temp . person = p;
    temp . next = null;

    if(this . front == null)
    {
       this . front = temp;
    }
    else
    {
        this . rear . next = temp;
    }
    this . rear = temp;
}
void  dequeue(){

    Node temp = new Node();

	temp = this . front;

	this . front = this . front . next;

	
}
public static void  display(int num){
	
            Queue a;
	for (Iterator<Queue> i= qlist.iterator(); i.hasNext();)
        {
            a=i.next();
		if(num == a.queueNo)
                {
			Node p = new Node();
			p = a . rear;

			if(a . rear == null){
				System.out.print("\nEmpty queue.\n");
			}
                        else
                        {
                                
				System.out.println("\nPassengers waiting on queue:") ;
				System.out.printf("%-15s%-15s%-10s\n","Passport","Name","Surame");
                                while(p!=null)
                                {
                                        System.out.printf("%-15d%-15s%-15s\n", p . person.getPassport(),p . person.getName(),p . person.getSurname());
					p = p . next;
				}
			}
		}
	}

    

}
public int getNo()
{
    return this . queueNo;
}
Person  oldest(){
	
	return this . front . person;
}
boolean  isEmpty(){
 if(this . front == null && this . rear == null){
  return true;
 }
 
 return false;
} 

                
                
}
