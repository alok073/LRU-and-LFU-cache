package cache;

import java.util.HashMap;
import java.util.Map;

import dataStructure.DoublyLinkedList;
import dataStructure.DoublyLinkedListNode;

public class LRUcache implements EvictionPolicy {
	
	private final int capacity;
	private int curCapacity;
	private Map<Integer, DoublyLinkedListNode> cache;
	private DoublyLinkedList dll;
	
	public LRUcache(int capacity) {
		this.capacity = capacity;
		this.curCapacity = 0;
		this.cache = new HashMap<Integer, DoublyLinkedListNode>();
		this.dll = new DoublyLinkedList();
	}
	
	@Override
	public int get(int key) {
		System.out.println("Operation GET: key = " + key);
		
		if(!cache.containsKey(key)) {
			return -1;
		}
		
		DoublyLinkedListNode node = cache.get(key);
		this.dll.detachNode(node);
		this.dll.addNode(node);
		
		return node.value;
	}
	
	@Override
	public void put(int key, int value) {
		System.out.println("Operation PUT: key = " + key + " Value = " + value);
		
		if(capacity <= 0) {
			System.out.println("Invalid capacity");
			return;
		}
		
		if(cache.containsKey(key)) {
			DoublyLinkedListNode node = cache.get(key);
			node.value = value;
			this.dll.detachNode(node);
			this.dll.addNode(node);
		}
		else {
			curCapacity++;
			if(curCapacity > capacity) {
				//remove LRU node
				System.out.println("    - Capacity is full. Evicting LRU key");
				DoublyLinkedListNode lruNode = this.dll.tail.prev;
				System.out.println("    - Evicting LRU node with key = " + lruNode.key);
				cache.remove(lruNode.key);
				this.dll.detachNode(lruNode);
				curCapacity--;
			}
			DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
			this.dll.addNode(newNode);
			cache.put(key, newNode);
		}
	}
}
