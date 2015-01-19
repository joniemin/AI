package model.search.informed;

import java.util.Comparator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.Action;
import model.Problem;
import model.search.GraphSearch;
import model.search.HashMapQueue;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.uninformed.BreadthFirst;

public abstract class BestFirst extends GraphSearch {

	protected interface Evaluator {
		public int eval(Node node);
	}

	static final BestFirst factory = new BestFirst() {
	};

	public static SearchStrategy treeSearch(Evaluator evaluator) {
		throw new NotImplementedException();
		// return new TreeSearch();
	}

	public static SearchStrategy graphSearch(Evaluator evaluator) {
		return factory.new GraphSearch(evaluator);
	}

	private class GraphSearch extends model.search.GraphSearch {

		private Evaluator evaluator;

		public GraphSearch(Evaluator e) {
			this.evaluator = e;
			this.frontier = new HashMapQueue(new Comparator<Node>() {
				@Override
				public int compare(Node node1, Node node2) {
					return evaluator.eval(node1) < evaluator.eval(node2) ? -1
							: evaluator.eval(node1) == evaluator.eval(node2) ? 0 : 1;
				}
			});
		}

		@Override
		protected void expand(Node node, Problem problem) {
			for (Action action : problem.actions(node.state())) {
				Node child = node.child(problem, action);
				Node previousChild = frontier.get(child.state());
				if (previousChild != null) {
					if (evaluator.eval(previousChild) > evaluator.eval(child)) {
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
