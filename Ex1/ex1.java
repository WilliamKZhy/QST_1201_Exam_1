package Solution;

import org.omg.CORBA.NO_IMPLEMENT;

public class QSTLinkList {
	public static class Node {
		public int value;
		public Node next = null;

		Node(int value) {
			this.value = value;
		}

		Node(int value, Node next) {
			this.value = value;
			this.next = next;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public static Node reverseLinkList(Node head) {
		if(head == null){
	           return null;
	       }
			Node newhead =new Node(0);
			newhead.next=head;
			Node first;
			Node last;
			first=head;
			//System.out.println(first);
			last=head.next;
			//System.out.println(last);
			while(first.next != null){
				first.next = last.next;
				last.next = newhead.next;
				newhead.next = last;
				last = first.next;
			}
			return newhead.next;
	}

	private static void printLinkList(Node head) {
		while (head.next != null) {
			if (head.next.next == null) {
				System.out.println(head.value + "->" + head.next.value);
				head.next = head.next.next;
			} else {
				System.out.print(head.value + "->");
				head.value = head.next.value;
				head.next = head.next.next;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, 5, 7, 2, 4 };
		Node head = createLinkList(arr);
		// System.out.println(head);
		printLinkList(head);
		Node head2 = createLinkList(arr);
		printLinkList(reverseLinkList(head2));
	}

	private static Node createLinkList(int[] arr) {
		// TODO Auto-generated method stub
		Node[] linkArr = new Node[arr.length];
		for (int i = 0; i < arr.length; i++) {
			// System.out.println(arr[i]);
			linkArr[i] = new Node(arr[i]);
		}
		/*
		 * for (int i = 0; i < arr.length; i++) {
		 * System.out.println(linkArr[i]); }
		 */
		for (int i = 0; i < arr.length; i++) {
			linkArr[i].setValue(arr[i]);
			if (i == arr.length - 1) {
				linkArr[i].next = null;
			} else {
				linkArr[i].next = linkArr[i + 1];
			}
		}
		return linkArr[0];
	}
}
