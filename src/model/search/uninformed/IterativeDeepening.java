package model.search.uninformed;

import model.Problem;
import model.search.SearchStrategy;
import model.search.result.SearchResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Iterative-deepening tree search
 *
 */
public abstract class IterativeDeepening {

	static final IterativeDeepening factory = new IterativeDeepening() {
	};

	public SearchStrategy treeSearch() {
		throw new NotImplementedException();
		// return new TreeSearch();
	}

	public SearchStrategy graphSearch() {
		return new GraphSearch();
	}

	private class GraphSearch implements SearchStrategy {
		@Override
		public SearchResult search(Problem problem) {
			for (int depth = 0; true; ++depth) {
				SearchResult result = DepthLimited.graphSearch(depth).search(problem);
				if (!result.isCutoff()) {
					return result;
				}
			}
		}
	}

}
