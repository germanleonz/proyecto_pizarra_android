package bd;

public abstract class DAOFactory {

	public static final int REST = 1;
	public static final int SQLite = 2;
	
	public abstract PizarraDAO getPizarraDAO();
	public abstract UserProfileDAO getUserProfileDAO();
	
	public static DAOFactory getDAOFactory(int idFactory) {
		switch (idFactory) {
			case REST: 
				return new RESTDAOFactory();
			case SQLite:
				return new SQLiteDAOFactory();
			default:
				return null;
		}
	}
}
