package model.search.local;

import model.Action;
import model.Problem;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.SearchResult;

public class HillClimbing implements SearchStrategy{

	protected interface Evaluator  {
		public int eval(Node node);
	}
	
	protected Evaluator evaluator;
	
	public HillClimbing(Evaluator evaluator){
		this.evaluator = evaluator;
	}
	
	@Override
	public SearchResult search(Problem problem) {
		Node current = new Node(problem.initialState());
		while(true){
			Node best = successor(problem, current);
			if (evaluator.eval(best) >= evaluator.eval(current)){
				return current.solution();
			}
		}
	}

	protected Node successor(Problem problem, Node current) {
		Node best = current;
		for (Action action : problem.actions(current.state())) {
			Node child = current.child(problem, action);
			if (evaluator.eval(child) < evaluator.eval(best)){
				best = child;
			}
		}
		return best;
	}
}
