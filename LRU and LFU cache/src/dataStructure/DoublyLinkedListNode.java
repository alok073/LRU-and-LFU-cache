package dataStructure;

public class DoublyLinkedListNode {
	public int key;
	public int value;
	public int frequency;
	public DoublyLinkedListNode prev;
	public DoublyLinkedListNode next;
	
	public DoublyLinkedListNode(int key, int value) {
		this.key = key;
		this.value = value;
		this.frequency = 1;
		this.prev = null;
		this.next = null;
	}
}
