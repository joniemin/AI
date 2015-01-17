package model.search.informed;

import model.search.Node;

public class AStarGS extends BestFirstGS{

	public AStarGS(final Heuristic heuristic) {
		super(new Evaluator(){
			@Override
			public int eval(Node node) {
				return heuristic.value(node) + node.pathCost();
			}
		});
	}
	
}
