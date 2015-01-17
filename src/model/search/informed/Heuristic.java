package model.search.informed;

import model.search.Node;

public interface Heuristic {
	public int value(Node node);
}