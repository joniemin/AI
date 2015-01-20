package model.search.local;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.Action;
import model.Problem;
import model.State;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.SearchResult;

public abstract class LocalBeamSearch {

	protected interface Evaluator {
		public int eval(Node node);
	}

	static final LocalBeamSearch factory = new LocalBeamSearch() {
	};

	public static SearchStrategy graphSearch(Evaluator evaluator, int k) {
		return factory.new GraphSearch(evaluator, k);
	}
	
	private class GraphSearch implements SearchStrategy {

		private Evaluator evaluator;
		private int k;
		private Comparator<Node> comparator;

		public GraphSearch(Evaluator e, int k) {
			this.evaluator = e;
			this.k = k;
			this.comparator = new Comparator<Node>() {
				@Override
				public int compare(Node node1, Node node2) {
					return evaluator.eval(node1) < evaluator.eval(node2) ? -1 : evaluator.eval(node1) == evaluator
							.eval(node2) ? node1.equals(node2) ? 0 : -1 : 1;
				}};
		}

		@Override
		public SearchResult search(Problem problem) {
			SortedSet<Node> currentNodes = new TreeSet<Node>(comparator);
			for (State state : problem.initialStates()) {
				Node node = new Node(state);
				if (problem.isGoal(node.state())) {
					return node.solution();
				}
				currentNodes.add(node);
			}
			while (true) {
				SortedSet<Node> nextNodes = new TreeSet<Node>(currentNodes);
				boolean newNodesFound = false;
				for (Node current : currentNodes) {
					for (Action action : problem.actions(current.state())) {
						Node child = current.child(problem, action);
						if (problem.isGoal(child.state())) {
							return child.solution();
						}
						nextNodes.add(child);
						if (nextNodes.size() > k) {
							nextNodes.remove(nextNodes.last());
							newNodesFound = true;
						}		
					}
				}
				if (!newNodesFound){
					return currentNodes.first().solution();
				}
				currentNodes = nextNodes;
			}
		}
	}
}
