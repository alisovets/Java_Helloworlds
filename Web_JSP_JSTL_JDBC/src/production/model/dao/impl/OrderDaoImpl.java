package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import log.Loger;
import model.Order;
import model.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
	private final static String TABLE_NAME = "orders";
	private Loger loger = Loger.getInstanse();
	
	@Override
	public int insert(Order order){
		final String sqlQuery = "INSERT INTO `"
				+ TABLE_NAME
				+ "` (consumer_id, subscript_year, create_date, paid_date) VALUES(?, ?, ?, ?);";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;

			try {
				prepStatement = connection.prepareStatement(sqlQuery,
						Statement.RETURN_GENERATED_KEYS);
				prepStatement.setInt(1, order.getConsumerId());
				prepStatement.setInt(2, order.getSubscriptYear());
				prepStatement.setDate(3, order.getCreateDate());
				prepStatement.setDate(4, order.getPaidDate());
				prepStatement.executeUpdate();
				int id = getLastId(prepStatement);
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "order with id: " + id + " was inserted");
				return id; 
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to insert an order", e);
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
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " order with id: " + id + " was deleted");
				return count; 
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to delete the order with id: " + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Order order){
		final String sqlQuery = "UPDATE `"
				+ TABLE_NAME
				+ "` SET consumer_id=?, subscript_year=?, create_date=?, paid_date=? WHERE id=?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, order.getConsumerId());
				prepStatement.setInt(2, order.getSubscriptYear());
				prepStatement.setDate(3, order.getCreateDate());
				prepStatement.setDate(4, order.getPaidDate());
				prepStatement.setInt(5, order.getId());
				int count = prepStatement.executeUpdate();
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " orders with id: " + order.getId() + " was updated");
				return count;
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to update the order with id: " + order.getId(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Order get(int id){
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
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read the order with id: " + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> getByConsumerId(int consumerId){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME
				+ "` WHERE consumer_id = ?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, consumerId);
				return executeQueryAndGetOrders(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read all orders", e);
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

	private List<Order> executeQueryAndGetOrders(PreparedStatement prepStatement)
			throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToOrders(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private Order executeQueryAndGetOrder(PreparedStatement prepStatement)
			throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToOrder(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private List<Order> resultSetToOrders(ResultSet resultSet)
			throws SQLException {
		List<Order> orderList = new ArrayList<Order>();
		Order order = resultSetToOrder(resultSet);
		while (order != null) {
			orderList.add(order);
			order = resultSetToOrder(resultSet);
		}
		return orderList;
	}

	private Order resultSetToOrder(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}

		Order order = new Order();
		order.setId(resultSet.getInt(1));
		order.setConsumerId(resultSet.getInt(2));
		order.setSubscriptYear(resultSet.getInt(3));
		order.setCreateDate(resultSet.getDate(4));
		order.setPaidDate(resultSet.getDate(5));
		return order;
	}

}
