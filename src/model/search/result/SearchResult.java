package model.search.result;

import java.util.List;

import model.Action;

public interface SearchResult {

	public List<Action> actions();

	public boolean isFailure();

	public boolean isCutoff();

}
