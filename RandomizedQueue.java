import java.util.NoSuchElementException;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] rq;
	private int N = 0;
	public RandomizedQueue(){
		rq = (Item[]) new Object[1];
	}                 // construct an empty randomized queue
    public boolean isEmpty(){
    	return N == 0;
    }                 // is the queue empty?
    public int size(){
    	return N;
    }                        // return the number of items on the queue
    public void enqueue(Item item){
    	if(item == null) throw new NullPointerException();
    	rq[N++] = item;
    	if(N == rq.length){
    		resize(2*N);
    	}
    }           // add the item
    public void resize(int k){
    	Item[] tmp = (Item[]) new Object[k];
    	for(int i = 0; i < N; i++){
    		tmp[i] = rq[i];
    	}
    	rq = tmp;
    }
    public Item dequeue(){
    	if(isEmpty()) throw new NoSuchElementException();
    	int i = (int)(Math.random() * N);
    	Item item = rq[i];
    	rq[i] = rq[--N];
    	rq[N] = null;
    	if(N <= rq.length / 4){
    		resize(rq.length/2);
    	}
    	return item;

    }                    // remove and return a random item
    public Item sample(){
    	if(isEmpty()) throw new NoSuchElementException();
    	int i = (int)(Math.random() * N);
    	return rq[i];
    }                     
    public Iterator<Item> iterator(){
    	return new RandomizedQueueIterator();
    }         // return an independent iterator over items in random order

    public class RandomizedQueueIterator implements Iterator{
    	private int i = 0;
    	private Item[] tmp;
    	public RandomizedQueueIterator(){
    		tmp = (Item[]) new Object[N];
    		for(int j = 0; j < N; j++){
    			tmp[j] = rq[j];
    		}
    	}
    	public boolean hasNext(){
    		return i < N;
    	}
    	public Item next(){
    		if(!hasNext()) throw new NoSuchElementException();
    		int j = (int)(Math.random() * N);
    		Item item = tmp[j];
    		tmp[j] = tmp[N - (++i)];
    		tmp[N - i] = null;
    		return item;
    	}
    	public void remove(){
    		throw new UnsupportedOperationException();
    	}
    }
    public static void main(String[] args){
    	RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();
        randomQueue.enqueue(1);
 

    }   // unit testing
}
