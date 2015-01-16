package model.search;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import model.Action;
import model.Problem;
import model.State;

public class UCGraphSearch implements SearchStrategy {

	private UCQueue frontier;
	private Set<Node> explored;

	public UCGraphSearch() {
		this.frontier = new UCQueue();
	}

	@Override
	public List<Action> search(Problem problem) {
		Node node = new Node(problem.initialState());
		frontier.add(node);
		explored = new HashSet<Node>();

		while (!frontier.isEmpty()) {
			node = frontier.poll();
			if (problem.isGoal(node.state())) {
				return node.solution();
			}
			explored.add(node);
			for (Action action : problem.actions(node.state())) {
				Node child = node.child(problem, action);
				Node previousChild = frontier.get(child.state());
				if (previousChild != null) {
					if (previousChild.pathCost() > child.pathCost()) {
						frontier.remove(previousChild);
						frontier.add(child);
					}
				} else if (!explored.contains(child)) {
					frontier.add(child);
				}
			}
		}
		return null; // failure
	}

	private class UCQueue extends PriorityQueue<Node> {

		private Map<State, Node> map;
		private Queue<Node> queue;

		public UCQueue() {
			this.map = new HashMap<State, Node>();
			this.queue = new PriorityQueue<Node>(11, new Comparator<Node>() {
				@Override
				public int compare(Node node1, Node node2) {
					return node1.pathCost() < node2.pathCost() ? -1 : node1.pathCost() == node2.pathCost() ? 0 : 1;
				}
			});
		}
		
		public UCQueue(Comparator comparator){
			
		}

		@Override
		public boolean isEmpty() {
			return map.isEmpty();			//O(1)
		}

		@Override
		public boolean add(Node node) {
			map.put(node.state(), node);	//O(1)
			return queue.add(node);			//O(log(n))
		}

		@Override
		public Node poll() {
			Node node = queue.poll();		//O(1)
			map.remove(node.state());		//O(1)
			return node;
		}

		public boolean remove(Node node) {
			map.remove(node.state());		//O(1)
			return queue.remove(node);		//O(log(n))
		}

		public Node get(State state) {
			return map.get(state);			//O(1)
		}
	}

}
