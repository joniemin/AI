package model.search.informed;

import model.search.Node;
import model.search.SearchStrategy;

/**
 * Greedy best-first graph search
 *
 */
public abstract class GreedyBestFirst extends BestFirst {
	
	public SearchStrategy graphSearch(final Heuristic heuristic) {
		return super.graphSearch(new Evaluator(){
			@Override
			public int eval(Node node) {
				return heuristic.value(node);
			}
		});
	}
	

}
