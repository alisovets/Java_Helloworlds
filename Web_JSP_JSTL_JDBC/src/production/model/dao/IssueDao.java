package model.dao;

import java.util.List;

import model.Issue;

public interface IssueDao {

	int insert(Issue issue);

	int update(Issue issue);

	int delete(int id);

	Issue get(int id);
	
	List<Issue> getAll();

	List<Issue> getActuals();

}
