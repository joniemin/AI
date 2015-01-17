package model.search.local;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.Action;
import model.Problem;
import model.search.Node;

public class StochasticHillClimbing extends HillClimbing {

	public StochasticHillClimbing(Evaluator evaluator) {
		super(evaluator);
	}

	@Override
	protected Node successor(Problem problem, Node current) {
		List<Node> better = Arrays.asList(current);
		for (Action action : problem.actions(current.state())) {
			Node child = current.child(problem, action);
			if (evaluator.eval(child) < evaluator.eval(current)){
				better.add(child);
			}
		}
		return better.get(new Random().nextInt(better.size()));
	}
}
