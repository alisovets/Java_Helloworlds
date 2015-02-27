package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import log.Loger;
import model.OrderItem;
import model.dao.OrderItemDao;

public class OrderItemDaoImpl implements OrderItemDao {
	private final static String TABLE_NAME = "order_items";
	private Loger loger = Loger.getInstanse();

	@Override
	public int insert(OrderItem item) {
		final String sqlQuery = "INSERT INTO `" + TABLE_NAME + "` (order_id, issue_id, quantity) VALUES(?, ?, ? );";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				prepStatement.setInt(1, item.getOrderId());
				prepStatement.setInt(2, item.getIssueId());
				prepStatement.setInt(3, item.getQuantity());
				prepStatement.executeUpdate();
				int id = getLastId(prepStatement);
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "the order item with id: " + id + " was inserted");
				return id;

			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to insert an order item", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(OrderItem item) {
		final String sqlQuery = "UPDATE `" + TABLE_NAME + "` SET order_id=?, issue_id=?, quantity=? WHERE id=?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, item.getOrderId());
				prepStatement.setInt(2, item.getIssueId());
				prepStatement.setInt(3, item.getQuantity());
				prepStatement.setInt(4, item.getId());
				int count = prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " order item with id: " + item.getId() + " was updated");
				return count;
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to update the order item with id: " + item.getId(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int delete(int id) {
		final String sqlQuery = "DELETE FROM `" + TABLE_NAME + "` WHERE id=?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, id);
				int count = prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " order item with id: " + id + " was updated");
				return count;
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to delete the order item with id: " + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteByOrderId(int orderId) {
		final String sqlQuery = "DELETE FROM `" + TABLE_NAME + "` WHERE order_id=?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, orderId);
				int count = prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " order items with orderId: " + orderId + " where updated");
				return count;
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to delete the order item with orderId: " + orderId, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public OrderItem get(int id) {
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?;";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, id);
				return executeQueryAndGetOrder(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read the order item with id: " + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderItem> getByOrderId(int orderId) {
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME + "` WHERE order_id = ?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, orderId);
				return executeQueryAndGetOrderItems(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to read all order items with orderId: " + orderId, e);
			throw new RuntimeException(e);
		}
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

	private List<OrderItem> executeQueryAndGetOrderItems(PreparedStatement prepStatement) throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToOrderItems(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private List<OrderItem> resultSetToOrderItems(ResultSet resultSet) throws SQLException {
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		OrderItem order = resultSetToOrderItem(resultSet);
		while (order != null) {
			itemList.add(order);
			order = resultSetToOrderItem(resultSet);
		}
		return itemList;
	}

	private OrderItem executeQueryAndGetOrder(PreparedStatement prepStatement) throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToOrderItem(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private OrderItem resultSetToOrderItem(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}
		OrderItem orderItem = new OrderItem();
		orderItem.setId(resultSet.getInt(1));
		orderItem.setOrderId(resultSet.getInt(2));
		orderItem.setIssueId(resultSet.getInt(3));
		orderItem.setQuantity(resultSet.getInt(4));
		return orderItem;
	}
}
