package model.search;

import java.util.ArrayList;

import model.Action;
import model.Problem;
import model.State;
import model.search.result.Solution;

public class Node{

	private State state;
	private Node parent;
	private Action action;
	private int pathCost;
	private int depth;

	public Node(State state) {
		this(state, null, null, 0, 0);
	}

	public Node(State state, Node parent, Action action, int pathCost, int depth) {
		super();
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.pathCost = pathCost;
		this.depth = depth;
	}

	public State state() {
		return state;
	}
	
	public int pathCost() {
		return pathCost;
	}

	public Solution solution() {
		if (parent == null) {
			return new Solution(new ArrayList<Action>());
		}
		Solution solution = parent.solution();
		solution.actions().add(action);
		return solution;
	}

	public Node child(Problem problem, Action action) {
		return new Node(problem.result(state, action), this, action, pathCost + problem.stepCost(state, action),
				depth + 1);
	}

	public boolean pathContains(Node node) {
		return (this.equals(node)) ? true : (parent == null) ? false : parent.pathContains(node);
	}

	
}
