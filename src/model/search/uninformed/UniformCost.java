package model.search.uninformed;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.Action;
import model.Problem;
import model.State;
import model.search.Frontier;
import model.search.GraphSearch;
import model.search.HashMapQueue;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.Failure;
import model.search.result.SearchResult;

/**
 * Uniform-cost graph search
 *
 */
public abstract class UniformCost extends GraphSearch {

	static final UniformCost factory = new UniformCost() {
	};

	public SearchStrategy treeSearch() {
		throw new NotImplementedException();
		// return new TreeSearch();
	}

	public SearchStrategy graphSearch() {
		return new GraphSearch();
	}

	private class GraphSearch extends model.search.GraphSearch {

		public GraphSearch() {
			this.frontier = new HashMapQueue(new Comparator<Node>() {
				@Override
				public int compare(Node node1, Node node2) {
					return node1.pathCost() < node2.pathCost() ? -1 : node1.pathCost() == node2.pathCost() ? 0 : 1;
				}
			});
		}

		@Override
		protected void expand(Node node, Problem problem) {
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
	}

}
