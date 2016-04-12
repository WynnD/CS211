import java.io.*;
import java.util.*;

/**
 * Wynn Drahorad - wdraho2
 * Project 6 - Airport Directification (my name for it)
 */


public class FileChain {
	FileNode head;

	public FileChain() {
		head = null;
	}

	public void add(String f) {
		FileNode fn = new FileNode(f);

		if (head == null) {
			head = fn;
		} else {
			FileNode temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}

			temp.next = fn;
		}
	}

	public void rem(String f) {
		FileNode temp = head;

		if (temp == null) {
			return;
		}

		if (temp.file.equals(f)) {
			head = head.next;
		} else {
			while (temp.next != null && !temp.next.equals(f)) {
				temp = temp.next;
			}

			if (temp.next != null) {
				temp = temp.next.next;
			}
		}
	}

	public boolean contains(String f) {
		FileNode temp = head;

		if (temp == null) {
			return false;
		}

		if (temp.file.equals(f)) {
			return true;
		} else {
			while (temp.next != null && !temp.next.equals(f)) {
				temp = temp.next;
			}

			if (temp.next != null) {
				return true;
			}
		}
		return false;
	}



	public class FileNode {
		String file;
		FileNode next;

		public FileNode(String f) {
			file = new String(f);
			next = null;
		} 
	}
}
