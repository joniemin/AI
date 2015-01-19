package model.search.informed;

import model.search.Node;
import model.search.SearchStrategy;

public class AStar extends BestFirst{

	public SearchStrategy graphSearch(final Heuristic heuristic) {
		return super.graphSearch(new Evaluator(){
			@Override
			public int eval(Node node) {
				return heuristic.value(node) + node.pathCost();
			}
		});
	}
	
}
