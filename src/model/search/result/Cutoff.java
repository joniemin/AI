package model.search.result;

import java.util.List;

import model.Action;

public class Cutoff implements SearchResult{

	@Override
	public List<Action> actions() {
		return null;
	}

	@Override
	public boolean isFailure() {
		return false;
	}

	@Override
	public boolean isCutoff() {
		return true;
	}

}
