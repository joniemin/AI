package model.search;

import java.util.List;

import model.*;
import model.search.result.SearchResult;

public interface SearchStrategy {
	
	public SearchResult search(Problem problem);
 
}
