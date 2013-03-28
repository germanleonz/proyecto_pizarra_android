package bd;

public class SQLiteDAOFactory extends DAOFactory {

	@Override
	public UserProfileDAO getUserProfileDAO() {
		// TODO Auto-generated method stub
		return new SQLiteUserProfileDAO();
	}
	
	@Override
	public PizarraDAO getPizarraDAO() {
		// TODO Auto-generated method stub
		return new SQLitePizarraDAO();
	}
}
