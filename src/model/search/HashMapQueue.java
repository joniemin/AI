package model.search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import model.State;

public class HashMapQueue implements Frontier {

	private Map<State, Node> map;
	private Queue<Node> queue;
	
	public HashMapQueue(Comparator<Node> comparator){
		this.map = new HashMap<State, Node>();
		this.queue = new PriorityQueue<Node>(11, comparator);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();			
	}

	@Override
	public boolean add(Node node) {
		map.put(node.state(), node);	
		return queue.add(node);			
	}

	@Override
	public Node poll() {
		Node node = queue.poll();		
		map.remove(node.state());		
		return node;
	}
	
	@Override
	public boolean remove(Node node) {
		map.remove(node.state());		
		return queue.remove(node);		
	}

	@Override
	public Node get(State state) {
		return map.get(state);			
	}

	@Override
	public boolean contains(Node node) {
		return map.containsKey(node.state());
	}
}