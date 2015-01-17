package model.search;

import model.State;

public interface Frontier {

	public boolean isEmpty();			//O(1)

	public boolean contains(Node node);	//O(1)
	
	public boolean add(Node node);		//O(log(n))

	public Node poll();					//O(1)

	public boolean remove(Node node);	//O(log(n))

	public Node get(State state);		//O(1)
	
}
