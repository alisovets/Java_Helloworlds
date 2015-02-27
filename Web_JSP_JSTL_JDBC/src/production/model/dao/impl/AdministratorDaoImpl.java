package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import log.Loger;
import model.Administrator;
import model.dao.AdministratorDao;

public class AdministratorDaoImpl implements AdministratorDao {
	private final static String TABLE_NAME = "administrators";
	private final static String GET_BY_ID_SQL = "SELECT * FROM `" + TABLE_NAME + "` WHERE id = ?;";
	private final static String GET_BY_LOGIN_SQL = "SELECT * FROM `" + TABLE_NAME + "` WHERE login = ?;";
	private final static String GET_BY_LOGIN_PASSWORD_SQL = "SELECT * FROM `" + TABLE_NAME + "` WHERE login = ? AND password = ?;";

	private Loger loger = Loger.getInstanse();

	@Override
	public Administrator get(int id) {
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(GET_BY_ID_SQL);
				prepStatement.setInt(1, id);
				return executeQueryAndGetAdministrator(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read an administrator with id:" + id, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Administrator get(String login) {
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(GET_BY_LOGIN_SQL);
				prepStatement.setString(1, login);
				return executeQueryAndGetAdministrator(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read an administrator with login:" + login, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Administrator get(String login, String password) {
		try {
			Connection connection = DBInit.getConnection();
			PreparedStatement prepStatement = null;
			try {
				prepStatement = connection.prepareStatement(GET_BY_LOGIN_PASSWORD_SQL);
				prepStatement.setString(1, login);
				prepStatement.setString(2, password);
				return executeQueryAndGetAdministrator(prepStatement);
			} finally {
				if (prepStatement != null)
					prepStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			loger.log(Loger.Event.DB_READ_ERROR, getClass().getName(), "failed to read an administrator with login:" + login, e);
			throw new RuntimeException(e);
		}
	}

	private Administrator executeQueryAndGetAdministrator(PreparedStatement prepStatement) throws SQLException {
		ResultSet resultSet = null;
		resultSet = prepStatement.executeQuery();
		try {
			return resultSetToAdministrator(resultSet);
		} finally {
			resultSet.close();
		}
	}

	private Administrator resultSetToAdministrator(ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}

		Administrator administrator = new Administrator();
		administrator.setId(resultSet.getInt(1));
		administrator.setLogin(resultSet.getString(2));
		administrator.setName(resultSet.getString(4));
		return administrator;
	}

}
