package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import log.Loger;
import model.Issue;
import model.dao.IssueDao;

public class IssueDaoImpl implements IssueDao {

	private final static String TABLE_NAME = "issues";
	private Loger loger = Loger.getInstanse();

	@Override
	public int insert(Issue issue){
		final String sqlQuery = "INSERT INTO `" + TABLE_NAME
				+ "` (name, publisher, periodicity, price, actual, admin_id) VALUES(?, ?, ?, ?, ?, ?);";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery,
						Statement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, issue.getName());
				prepStatement.setString(2, issue.getPublisher());
				prepStatement.setString(3, issue.getPeriodicity().toString());
				prepStatement.setDouble(4, issue.getPrice());
				prepStatement.setBoolean(5, issue.isActual());
				prepStatement.setInt(6, issue.getAdminId());
				prepStatement.executeUpdate();
				int id = getLastId(prepStatement);
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "the issue with id " + id + "was inserted");
				return id;
				
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to insert a issue", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Issue issue){
		final String sqlQuery = "UPDATE `"
				+ TABLE_NAME
				+ "` SET name=?, publisher=?, periodicity=?, price=?, actual=?, admin_id=? WHERE id=?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setString(1, issue.getName());
				prepStatement.setString(2, issue.getPublisher());
				prepStatement.setString(3, issue.getPeriodicity().toString());
				prepStatement.setDouble(4, issue.getPrice());
				prepStatement.setBoolean(5, issue.isActual());
				prepStatement.setInt(6, issue.getAdminId());
				prepStatement.setInt(7, issue.getId());
				prepStatement.executeUpdate();
				int count =	prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " issue was updated" + issue.getId() );
				return count; 
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to update the issue with id: " + issue.getId() , e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int delete(int id){
		final String sqlQuery = "DELETE FROM `" + TABLE_NAME + "` WHERE id=?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, id);
				int count = prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " issue was deleted" + id );
				return count; 
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to delete the issue with id: " + id , e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Issue get(int id){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, id);
				return executeQueryAndGetIssue(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read the issue with id: " + id , e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Issue> getAll(){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME
				+ "`;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				return executeQueryAndGetIssues(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read all issues", e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Issue> getActuals(){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME
				+ "` WHERE actual = 1;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				return executeQueryAndGetIssues(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read all issues", e);
			throw new RuntimeException(e);
		}
	}
	
	
	private List<Issue> executeQueryAndGetIssues(
			PreparedStatement prepStatement) throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToIssues(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private List<Issue> resultSetToIssues(ResultSet resultSet)
			throws SQLException {
		List<Issue> issueList = new ArrayList<Issue>();
		Issue issue = resultSetToIssue(resultSet);
		while (issue != null) {
			issueList.add(issue);
			issue = resultSetToIssue(resultSet);
		}
		return issueList;
	}
	
	private Issue executeQueryAndGetIssue(PreparedStatement prepStatement)
			throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToIssue(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private Issue resultSetToIssue(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}
		Issue issue = new Issue();
		issue.setId(resultSet.getInt(1));
		issue.setName(resultSet.getString(2));
		issue.setPublisher(resultSet.getString(3));
		issue.setPeriodicity(Issue.Periodicity.valueOf(resultSet.getString(4)
				.toUpperCase()));
		issue.setPrice(resultSet.getDouble(5));
		issue.setActual(resultSet.getBoolean(6));
		issue.setAdminId(resultSet.getInt(7));
		return issue;
	}

	private int getLastId(Statement statement) throws SQLException {
		ResultSet generatedKeys = null;
		generatedKeys = statement.getGeneratedKeys();
		try {
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				return -1;
			}
		} finally {
			generatedKeys.close();
		}
	}

}
