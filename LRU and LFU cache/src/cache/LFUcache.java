package cache;

import java.util.HashMap;
import java.util.Map;

import dataStructure.DoublyLinkedList;
import dataStructure.DoublyLinkedListNode;

public class LFUcache implements EvictionPolicy {
	
	private final int capacity;
	private int curCapacity;
	private int minFrequency;
	private Map<Integer, DoublyLinkedListNode> cache;
	private Map<Integer, DoublyLinkedList> freqMap;
	
	public LFUcache(int capacity) {
		this.capacity = capacity;
		this.curCapacity = 0;
		this.minFrequency = 0;
		this.cache = new HashMap<Integer, DoublyLinkedListNode> ();
		this.freqMap = new HashMap<Integer, DoublyLinkedList>();
	}
	
	@Override
	public int get(int key) {
		System.out.println("> Operation GET: key = " + key);
		DoublyLinkedListNode node = cache.get(key);
		if(node == null) {
			return -1;
		}
		updateNode(node);
		return node.value;
	}
	
	@Override
	public void put(int key, int value) {
		if(capacity <= 0) {
			System.out.println("Invalid capacity");
			return;
		}
		
		System.out.println("> Operation PUT: key = " + key + " Value = " + value);
		if(cache.containsKey(key)) {
			DoublyLinkedListNode node = cache.get(key);
			node.value = value;
			updateNode(node);
		}
		else {
			curCapacity++;
			//if capacity is full
			if(curCapacity > capacity) {
				System.out.println("    - Capacity is full. ");
				DoublyLinkedList minFreqList = freqMap.get(minFrequency);
				DoublyLinkedListNode lruNode = minFreqList.tail.prev;
				System.out.println("    - Evicting LRU node with key = " + lruNode.key);
				cache.remove(lruNode.key);
				minFreqList.removeNode(lruNode);
				curCapacity--;
			}
			//add a new node
			minFrequency = 1;
			DoublyLinkedListNode newNode = new DoublyLinkedListNode(key, value);
			DoublyLinkedList newList = freqMap.getOrDefault(1, new DoublyLinkedList());
			newList.addNode(newNode);
			cache.put(key, newNode);
			freqMap.put(1, newList);
		}
	}
	
	private void updateNode(DoublyLinkedListNode node) {
		int curFreq = node.frequency;
		System.out.println("    - freq value of node with key = " + node.key + " is = " + curFreq);
		DoublyLinkedList curFreqList = freqMap.get(curFreq);
		curFreqList.removeNode(node);
		
		if(curFreq == minFrequency && curFreqList.listSize == 0) {
			minFrequency++;
		}
		
		node.frequency++;
		DoublyLinkedList newList = freqMap.getOrDefault(node.frequency, new DoublyLinkedList());
        newList.addNode(node);
        freqMap.put(node.frequency, newList);
	}

}