package model.search.uninformed;

import model.Problem;
import model.search.SearchStrategy;
import model.search.result.SearchResult;

/**
 *	Iterative-deepening tree search 
 *
 */
public class IterativeDeepeningTS implements SearchStrategy {

	@Override
	public SearchResult search(Problem problem) {
		for (int depth = 0; true; ++depth) {
			SearchResult result = new DepthLimitedTS(depth).search(problem);
			if (!result.isCutoff()){
				return result;
			}
		}
	}

}
