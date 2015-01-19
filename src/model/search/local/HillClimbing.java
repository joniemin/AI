package model.search.local;

import model.Action;
import model.Problem;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.SearchResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class HillClimbing {

	protected interface Evaluator {
		public int eval(Node node);
	}

	static final HillClimbing factory = new HillClimbing() {
	};

	public static SearchStrategy treeSearch(Evaluator evaluator) {
		throw new NotImplementedException();
		// return new TreeSearch();
	}

	public static SearchStrategy graphSearch(Evaluator evaluator) {
		return factory.new GraphSearch(evaluator);
	}

	protected class GraphSearch implements SearchStrategy {

		protected Evaluator evaluator;

		public GraphSearch(Evaluator evaluator) {
			this.evaluator = evaluator;
		}

		@Override
		public SearchResult search(Problem problem) {
			Node current = new Node(problem.initialState());
			while (true) {
				Node best = expand(problem, current);
				if (evaluator.eval(best) >= evaluator.eval(current)) {
					return current.solution();
				}
			}
		}

		protected Node expand(Problem problem, Node current) {
			Node best = current;
			for (Action action : problem.actions(current.state())) {
				Node child = current.child(problem, action);
				if (evaluator.eval(child) < evaluator.eval(best)) {
					best = child;
				}
			}
			return best;
		}
	}
}
