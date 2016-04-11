/**
*	Class containing both Stack and Node classes.
*	Project 5 - Infix Expressions - 3-29-16
*	 @Author - Wynn Drahorad - UIN: 663411123 - wdraho2@uic.edu
*/


import java.io.*;
import java.util.*;


public class Stack {

	private Node top;

	public static void main(String[] args) {
		//
	}

	public Stack() {

	}

	public Stack(Node data) {
		if (data.getVal() == -999) {
			top = new Node(data.getToken());
		} else {
			top = new Node(data.getVal());
		}
	}

	public boolean isEmpty() {
		if (top == null) {
			return true;
		}

		return false;
	}

	public void push(Node data) {
		Node temp;

		if (data == null) {
			return;
		}

		if (data.getVal() == -999) {
			temp = new Node(data.getToken());
		} else {
			temp = new Node(data.getVal());
		}

		temp.setNext(top);
		top = temp;

	}

	public void pop() {
		if (top != null) {
			top = top.getNext();
		}
	}

	public Node top() {
		return top;
	}

}

class Node {

	private Token toke;
	private int val;
	private Node next;

	public Node(Token t) {
		toke = t;
		val = -999;
	}

	public Node(int v) {
		val = v;
		toke = null;
	}

	public int getVal() {
		return val;
	}

	public Token getToken() {
		return toke;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node n) {
		next = n;
	}
}