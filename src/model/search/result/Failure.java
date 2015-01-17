package model.search.result;

import java.util.List;

import model.Action;

public class Failure implements SearchResult{

	@Override
	public List<Action> actions() {
		return null;
	}

	@Override
	public boolean isFailure() {
		return true;
	}

	@Override
	public boolean isCutoff() {
		return false;
	}

}
