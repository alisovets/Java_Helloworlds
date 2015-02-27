package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import log.Loger;
import model.CascadeOrderItem;
import model.Issue;
import model.dao.CascadeOrderItemDao;


public class CascadeOrderItemDaoImpl implements CascadeOrderItemDao{
	private Loger loger = Loger.getInstanse();
	
	public List<CascadeOrderItem> getByOrderId(int orderId){
		String sqlQuery = "SELECT order_items.id, order_id, quantity, issue_id, name, publisher, periodicity, price FROM order_items, issues WHERE issue_id=issues.id and order_id=?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, orderId);
				return executeQueryAndGetCascadeOrderItems(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read a cascade order with id:" + orderId, e);
			throw new RuntimeException(e);
		}
	}
	
	private List<CascadeOrderItem> executeQueryAndGetCascadeOrderItems(
			PreparedStatement prepStatement) throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToCascadeOrderItems(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private List<CascadeOrderItem> resultSetToCascadeOrderItems(ResultSet resultSet)
			throws SQLException {
		List<CascadeOrderItem> itemList = new ArrayList<CascadeOrderItem>();
		CascadeOrderItem orderItem = resultSetToCascadeOrderItem(resultSet);
		while (orderItem != null) {
			itemList.add(orderItem);
			orderItem = resultSetToCascadeOrderItem(resultSet);
		}
		return itemList;
	}

	private CascadeOrderItem resultSetToCascadeOrderItem(ResultSet resultSet)
			throws SQLException {
		if (!resultSet.next()) {
			return null;
		}
		CascadeOrderItem orderItem = new CascadeOrderItem();
		orderItem.setId(resultSet.getInt(1));
		orderItem.setQuantity(resultSet.getInt(3));
		Issue issue = new Issue();
		issue.setId(resultSet.getInt(4));
		issue.setName(resultSet.getString(5));
		issue.setPublisher(resultSet.getString(6));
		issue.setPeriodicity(Issue.Periodicity.valueOf(resultSet.getString(7).toUpperCase()));
		issue.setPrice(resultSet.getDouble(8));
		orderItem.setIssue(issue);
		return orderItem;
	}
}
