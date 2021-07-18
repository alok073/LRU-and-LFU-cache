package cache;

public class Main {

	public static void main(String[] args) {
		
		EvictionPolicy policy = new LFUcache(3);
		policy.put(1, 10);
		policy.put(2, 20);
		policy.put(3, 30);
		policy.put(4, 40);
		System.out.println("Value received for key = 3 is = " + policy.get(3));
		System.out.println("Value received for key = 2 is = " + policy.get(2));
		System.out.println("Value received for key = 4 is = " + policy.get(4));
		policy.put(5, 50);
		policy.put(2, 12);
		
	}

}
