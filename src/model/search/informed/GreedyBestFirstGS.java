package model.search.informed;

import model.search.Node;

/**
 * Greedy best-first graph search
 *
 */
public class GreedyBestFirstGS extends BestFirstGS {
	
	public GreedyBestFirstGS(final Heuristic heuristic) {
		super(new Evaluator(){
			@Override
			public int eval(Node node) {
				return heuristic.value(node);
			}
		});
	}
	

}
