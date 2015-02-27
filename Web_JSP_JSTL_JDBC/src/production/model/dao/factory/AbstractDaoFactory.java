package model.dao.factory;

public class AbstractDaoFactory {
	enum DataType {
		MYSQL
	};

	public static DaoFactory getDaoFactory(String data) {
		DataType dataType = DataType.valueOf(data.toUpperCase());

		switch (dataType) {
		case MYSQL:
			return new DaoFactoryDB();
		default:
			throw new EnumConstantNotPresentException(DataType.class,
					dataType.name());
		}
	}
}
