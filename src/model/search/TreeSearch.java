package model.search;

import java.util.List;
import java.util.Queue;

import model.Action;
import model.Problem;

/**
 * Template class for TreeSearches
 *
 */
public abstract class TreeSearch implements SearchStrategy {

	private Queue<Node> frontier;

	@Override
	public List<Action> search(Problem problem) {
		Node node = new Node(problem.initialState());
		frontier.add(node);
		while (!frontier.isEmpty()) {
			node = frontier.poll();
			if (problem.isGoal(node.state())) {
				return node.solution();
			}
			expand(node, problem);
		}
		return null; // failure
	}

	protected void expand(Node node, Problem problem) {
		for (Action action : problem.actions(node.state())) {
			frontier.add(node.child(problem, action));
		}
	}
}
