package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RESTDAOFactory extends DAOFactory {
	
	/**
	 *
	 */
	public static final String DRIVER =
		"org.postgresql.Driver";
	
	/**
	 *
	 */
	public static final String DBURL =
		"jdbc:postgresql://localhost:5432/siradex";
	
	/**
	 *
	 */
	public static final String USUARIO = "siradex";
	
	/**
	 *
	 */
	public static final String CONTRASENA = "siradex";
	
	/**
	 *	Metodo para crear conexiones con PostgreSQL
	 * @return
	 * @throws SQLException
	 */
	public static Connection crearConexion() throws SQLException {
		// Usar el driver y el url para crear la conexion
		// Ver connection pool implemetation/ usage
		try {
			Connection conexion = null;
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(DBURL, USUARIO, CONTRASENA);
			return conexion;
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		return null;
	}

	// Metodo para conseguir un DAO de cada clase de la base de datos
	@Override
	public UserProfileDAO getUserProfileDAO() {
		return new RESTUserProfileDAO();
	}
	
	@Override
	public PizarraDAO getPizarraDAO() {
		return new RESTPizarraDAO();
	}
}
