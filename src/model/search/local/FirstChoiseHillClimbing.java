package model.search.local;

import java.util.List;
import java.util.Random;

import model.Action;
import model.Problem;
import model.search.Node;
import model.search.SearchStrategy;

public class FirstChoiseHillClimbing extends HillClimbing {

	static final FirstChoiseHillClimbing factory = new FirstChoiseHillClimbing() {
	};

	public static SearchStrategy graphSearch(Evaluator evaluator) {
		return factory.new GraphSearch(evaluator);
	}

	private class GraphSearch extends HillClimbing.GraphSearch {

		public GraphSearch(Evaluator evaluator) {
			super(evaluator);
		}

		@Override
		protected Node expand(Problem problem, Node current) {
			List<Action> actions = problem.actions(current.state());
			Random randomGenerator = new Random();
			while (!actions.isEmpty()) {
				Action action = actions.remove(randomGenerator.nextInt(actions.size()));
				Node child = current.child(problem, action);
				if (evaluator.eval(child) < evaluator.eval(current)) {
					return child;
				}
			}
			return current;
		}
	}
}
