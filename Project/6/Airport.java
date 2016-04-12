import java.io.*;
import java.util.*;

/**
 * Wynn Drahorad - wdraho2
 * Project 6 - Airport Directification (my name for it)
 */


public class Airport {

	public boolean visited;
	public int number;
	public Flight head;

	public Airport(int i) {
		head = null;
		visited = false;
		number = i;
	}


	public void addFlight(Airport new_dest) {
		Flight flight = new Flight(new_dest);

		if (head == null) {
			head = flight;
		} else {
			Flight temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}

			temp.next = flight;
		}
	}

	public void removeFlight(Airport toDelete) {
		Flight temp = head;

		if (temp == null) {
			return;
		}

		if (temp.dest == toDelete) {
			head = head.next;
		} else {
			while (temp.next != null && temp.next.dest != toDelete) {
				temp = temp.next;
			}

			if (temp.next != null) {
				temp.next = temp.next.next;
			}
		}
	}



}
class Flight {

  public Flight next;
  public Airport dest;


  public Flight(Airport destin) {
  	dest = destin;
  	next = null;
  }
}


