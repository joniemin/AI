package model.search.local;

import java.util.List;
import java.util.Random;

import model.Action;
import model.Problem;
import model.search.Node;
import model.search.SearchStrategy;
import model.search.result.SearchResult;

public class SimulatedAnnealing implements SearchStrategy {

	public interface Schedule {
		public int get(int t);
	}

	public interface Evaluator {
		public int eval(Node node);
	}

	private Schedule schedule;
	private Evaluator evaluator;

	public SimulatedAnnealing(Schedule schedule, Evaluator evaluator) {
		this.schedule = schedule;
		this.evaluator = evaluator;
	}

	@Override
	public SearchResult search(Problem problem) {
		Node current = new Node(problem.initialState());
		for (int time = 1; true; ++time) {
			int temperature = schedule.get(time);
			if (temperature == 0) {
				return current.solution();
			}
			Node next = successor(problem, current);
			int delta = evaluator.eval(next) - evaluator.eval(current);
			if (delta > 0 || new Random().nextDouble() < Math.exp(delta / temperature)) {
				current = next;
			}
		}
	}

	private Node successor(Problem problem, Node current) {
		List<Action> actions = problem.actions(current.state());
		Action action = actions.remove(new Random().nextInt(actions.size()));
		return current.child(problem, action);
	}

}
