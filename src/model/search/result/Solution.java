package model.search.result;

import java.util.List;

import model.Action;

public class Solution implements SearchResult{

	private List<Action> actions;

	public Solution(List<Action> actions) {
		this.actions = actions;
	}
	
	@Override
	public List<Action> actions() {
		return actions;
	}
	
	@Override
	public boolean isFailure() {
		return false;
	}

	@Override
	public boolean isCutoff() {
		return false;
	}

}
