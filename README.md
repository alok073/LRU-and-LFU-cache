# LRU & LFU cache

A low-level design of LRU & LFU cache eviction policy.

## LRU cache

1. get(key)
   * Check if a node with the given key is present or not
   * If present, move the node with this key to the front of DLL and return its value
   * If not, then return "-1"
2.  put(key, value)
    * Check if a node with the given key is present or not.
    * If present, get the node, update its value & move the node to the front of the DLL.
    * If not, then check if the no. of nodes exceeds the defined capacity of the cache. If yes, remove the LRU node i.e last node in the DLL.
    * Add a new node to the front of the DLL with the given key & value. 

## LFU cache
1. get(key)
   * Check if a node with the given key is present or not in cache
   * If present, detach the node from its current DLL, increment its freq and insert it to the front of the DLL against its current freq. And return its value
   * If not, return "-1"

2. put(key, value)
   * Check if a node with the given key is present or not in cache hashMap.
   * If present, get the node, update its value, increment its freq, move it to the front of the DLL against its current freq.
   * If not, check if the capacity of the DLL with minFreq exceeds the cache size. If yes, remove the LRU node from the minFreq DLL. Also remove the node from cache hashMap.
   * Make a new node with freq as 1 and insert it to the front of the DLL against its freq. Also insert this node in the cache hashMap along with its key.

## References

* [LRU and LFU cache - java](https://www.programmersought.com/article/57934487802/#:~:text=Data%20Structure%20and%20Algorithm%20%2D%20LRU,of%20the%20application%20access%20data.)
* [Doubly linked list](https://www.geeksforgeeks.org/doubly-linked-list/)
* [Where to use LFU](https://ieftimov.com/post/when-why-least-frequently-used-cache-implementation-golang/)
