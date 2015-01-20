package model.search.undeterministic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Action;
import model.Problem;
import model.State;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.Failure;
import model.search.result.SearchResult;

public abstract class AndOr {

	
	static final AndOr factory = new AndOr() {
	};

	public static SearchStrategy graphSearch() {
		return factory.new GraphSearch();
	}

	private class GraphSearch implements SearchStrategy {

		@Override
		public SearchResult search(Problem problem) {
			return orSearch(problem, problem.initialState(), new HashMap<State, Action>());
		}

		private SearchResult orSearch(Problem problem, State state, HashMap<State, Action> solution) {
			if (problem.isGoal(state)){
				return solution;
			}
			for (Action action : problem.actions(node.state())) {
				
				
				for(State state: problem.results(node.state(), action)){
					
				
				
				
				}
				
				
				if (!node.pathContains(child)) {
					SearchResult result = andSearch(problem, children);
					if (!result.isFailure()){
						return result;
					}
				}
			}
			return new Failure();
		}

		private SearchResult andSearch(Problem problem, List<Node> node) {
			
			return null;
		}

	}

}
