package model.search;

import java.util.ArrayList;
import java.util.List;

import model.Action;
import model.Problem;

public class DLTreeSearch implements SearchStrategy {

	private int limit;
	
	public DLTreeSearch(int limit) {
		this.limit = limit;
	}

	@Override
	public List<Action> search(Problem problem) {
		Node node = new Node(problem.initialState());
		return search(node, problem, limit);
	}
	
	private List<Action> search(Node node, Problem problem, int limit) {
		boolean cutoffOccured = false;
		if (problem.isGoal(node.state())) {
			return node.solution();
		}
		if (limit <= 0) {
			return new ArrayList<Action>(); // cut_off
		} else {
			for (Action action : problem.actions(node.state())) {
				Node child = node.child(problem, action);
				List<Action> result = search(child, problem, limit-1);
				if (result != null) {
					if (result.isEmpty()){
						cutoffOccured = true;
					}else {
						return result;
					}
				}
			}
		}
		return null;
	}

}
