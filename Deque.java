import java.util.NoSuchElementException;
import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {
	private int N;
	private Node front, end;
	private class Node{
		private Item item;
		private Node next;
		private Node prev;
	}
	public Deque(){
		N = 0;
		front = null;
		end = null;
	}                           // construct an empty deque
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}                        // return the number of items on the deque
    public void addFirst(Item item){
    	if(item == null) throw new NullPointerException();
    	Node node = front;
    	front = new Node();
    	front.prev = null;
    	front.item = item;
    	if(isEmpty()){
    		front.next = null;
    		end = front;
    	}else{
    		front.next = node;
    		node.prev = front;
    	}
    	N++;
    }          // add the item to the front
    public void addLast(Item item){
    	if(item == null) throw new NullPointerException();
    	Node node = end;
    	end = new Node();
    	end.next = null;
    	end.item = item;
    	if(isEmpty()){
    		end.prev = null;
    		front = end;
    	}else{
    		node.next = end;
    		end.prev = node;
    	}
    	N++;
    }           // add the item to the end
    public Item removeFirst(){
    	if(isEmpty()) throw new NoSuchElementException();
    	Item item = front.item;
    	if(N == 1){
    		front = null;
    		end = null;
    	}else{
    		front = front.next;
    		front.prev = null;
    	}
    	N--;
    	return item;
    }                // remove and return the item from the front
    public Item removeLast(){
    	if(isEmpty()) throw new NoSuchElementException();
    	Item item = end.item;
    	if(N == 1){
    		front = null;
    		end = null;
    	}else{
    		end = end.prev;
    		end.next = null;
    	}
    	N--;
    	return item;
    }                 
    public Iterator<Item> iterator(){
    	return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
    	private Node cur;
    	public boolean hasNext(){
    		return cur != null;
    	}
    	public Item next(){
    		if(!hasNext()) throw new NoSuchElementException();
    		Item item = cur.item;
    		cur = cur.next;
    		return item;
    	}
    	public void remove(){
    		throw new UnsupportedOperationException();
    	}
    }
    public static void main(String[] args){
    	Deque<String> deque = new Deque<String>();
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        deque.addLast("You");
        
        System.out.println("output:");
        for (String x : deque) {
            System.out.print(x + ' ');
        }
    }
}