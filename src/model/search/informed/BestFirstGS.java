package model.search.informed;

import java.util.Comparator;

import model.Action;
import model.Problem;
import model.search.GraphSearch;
import model.search.HashMapQueue;
import model.search.Node;


public class BestFirstGS extends GraphSearch{
	
	protected interface Evaluator  {
		public int eval(Node node);
	}

	private Evaluator evaluator;
	

	public BestFirstGS(Evaluator e) {
		this.evaluator = e;
		this.frontier = new HashMapQueue(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return evaluator.eval(node1) < evaluator.eval(node2) ? -1
						: evaluator.eval(node1) == evaluator.eval(node2) ? 0 : 1;
			}
		});
	}
	
	@Override
	protected void expand(Node node, Problem problem) {
		for (Action action : problem.actions(node.state())) {
			Node child = node.child(problem, action);
			Node previousChild = frontier.get(child.state());
			if (previousChild != null) {
				if (evaluator.eval(previousChild) > evaluator.eval(child)) {
					frontier.remove(previousChild);
					frontier.add(child);
				}
			} else if (!explored.contains(child)) {
				frontier.add(child);
			}
		}
	}
}
