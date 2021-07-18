package dataStructure;

public class DoublyLinkedList {
	public int listSize;
	public DoublyLinkedListNode tail;
	public DoublyLinkedListNode head;
	
	public DoublyLinkedList() {
		this.listSize = 0;
		this.tail = new DoublyLinkedListNode(0, 0);
		this.head = new DoublyLinkedListNode(0, 0);
		head.next = tail;
		tail.prev = head;
	}
	
	public void addNode(DoublyLinkedListNode node) {
		System.out.println("    - Adding node to front");
		DoublyLinkedListNode nextNode = head.next;
		node.next = nextNode;
		node.prev = head;
		head.next = node;
		nextNode.prev = node;
		listSize++;
	}
	
	public void removeNode(DoublyLinkedListNode node) {
		System.out.println("    - detaching node");
		detachNode(node);
		listSize--;
	}
	
	public void detachNode(DoublyLinkedListNode node) {
		DoublyLinkedListNode prevNode = node.prev;
		DoublyLinkedListNode nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
	}
}
