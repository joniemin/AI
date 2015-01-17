package model.search.uninformed;

import java.util.List;

import model.Action;
import model.Problem;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.Cutoff;
import model.search.result.Failure;
import model.search.result.SearchResult;

/**
 *	Depth-limited tree search 
 *
 */
public class DepthLimitedTS implements SearchStrategy {

	private int limit;

	public DepthLimitedTS(int limit) {
		this.limit = limit;
	}

	@Override
	public SearchResult search(Problem problem) {
		Node node = new Node(problem.initialState());
		return search(node, problem, limit);
	}

	private SearchResult search(Node node, Problem problem, int limit) {
		boolean cutoffOccured = false;
		if (problem.isGoal(node.state())) {
			return node.solution();
		}
		if (limit <= 0) {
			return new Cutoff(); // cut_off
		} else {
			for (Action action : problem.actions(node.state())) {
				Node child = node.child(problem, action);
				SearchResult result = search(child, problem, limit - 1);
				if (result.isCutoff()) {
					cutoffOccured = true;
				} else if (!result.isFailure()) {
					return result;
				}
			}
			return cutoffOccured ? new Cutoff() : new Failure();
		}
	}

}
