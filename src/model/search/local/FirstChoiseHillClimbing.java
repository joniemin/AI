package model.search.local;

import java.util.List;
import java.util.Random;

import model.Action;
import model.Problem;
import model.search.Node;

public class FirstChoiseHillClimbing extends HillClimbing{

	public FirstChoiseHillClimbing(Evaluator evaluator) {
		super(evaluator);
	}

	@Override
	protected Node successor(Problem problem, Node current) {
		List<Action> actions = problem.actions(current.state());
		Random randomGenerator = new Random();
		while (!actions.isEmpty()){
			Action action = actions.remove(randomGenerator.nextInt(actions.size()));
			Node child = current.child(problem, action);
			if (evaluator.eval(child) < evaluator.eval(current)) {
				return child;
			}
		}
		return current;
	}
}
