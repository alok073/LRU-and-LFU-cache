package cache;

public interface EvictionPolicy {

	public int get(int key);
	
	public void put(int key, int value);

}
