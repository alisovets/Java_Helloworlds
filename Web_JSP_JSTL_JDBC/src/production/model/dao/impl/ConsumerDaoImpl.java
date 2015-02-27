package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import log.Loger;
import model.Consumer;
import model.dao.ConsumerDao;

import org.apache.commons.lang.StringUtils;

public class ConsumerDaoImpl implements ConsumerDao {
	private final static String TABLE_NAME = "consumers";
	private Loger loger = Loger.getInstanse();
	
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
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), count + " consumer was deleted with id" + id);
				return count;
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to delete a consumer with id:" + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insert(Consumer consumer){
		validateConsumerParameter(consumer);

		final String sqlQuery = "INSERT INTO `" + TABLE_NAME
				+ "` (login, password, name, address) VALUES(?, ?, ?, ?);";

		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery,
						Statement.RETURN_GENERATED_KEYS);
				prepStatement.setString(1, consumer.getLogin());
				prepStatement.setString(2, consumer.getPassword());
				prepStatement.setString(3, consumer.getName());
				prepStatement.setString(4, consumer.getAddress());
				prepStatement.executeUpdate();
				int id = getLastId(prepStatement);
				loger.log(Loger.Event.DB_UPDATE, getClass().getName(), "inserted a consumer with id" + id);
				return id; 

			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_UPDATE_ERROR, getClass().getName(), "failed to insert a consumer", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Consumer get(int id){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setInt(1, id);
				return executeQueryAndGetConsumer(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to get a consumer with id:" + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Consumer get(String login){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME + "` WHERE login = ?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setString(1, login);
				return executeQueryAndGetConsumer(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to get a consumer with login:" + login, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Consumer get(String login, String password){
		String sqlQuery = "SELECT * FROM `" + TABLE_NAME
				+ "` WHERE login = ? AND password = ?;";
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(sqlQuery);
				prepStatement.setString(1, login);
				prepStatement.setString(2, password);
				return executeQueryAndGetConsumer(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to get a consumer with login:" + login, e);
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

	private Consumer executeQueryAndGetConsumer(PreparedStatement prepStatement)
			throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToConsumer(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private Consumer resultSetToConsumer(ResultSet resultSet)
			throws SQLException {
		if (!resultSet.next()) {
			return null;
		}

		Consumer consumer = new Consumer();

		consumer.setId(resultSet.getInt(1));
		consumer.setLogin(resultSet.getString(2));
		consumer.setAddress(resultSet.getString(4));
		consumer.setName(resultSet.getString(5));
		return consumer;
	}

	private void validateConsumerParameter(Consumer consumer) {
		validateLogin(consumer.getLogin());
		validatePassword(consumer.getPassword());
	}

	private void validateLogin(String login) {
		if (StringUtils.isEmpty(login)) {
			throw new IllegalArgumentException("login is empty.");
		}
		if (login.length() < 5) {
			throw new IllegalArgumentException("login is too short.");
		}
	}

	private void validatePassword(String password) {
		if (StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException("password is empty.");
		}
		if (password.length() < 5) {
			throw new IllegalArgumentException("password is too short.");
		}
	}

}
