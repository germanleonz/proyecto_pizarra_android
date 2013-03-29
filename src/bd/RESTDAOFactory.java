package bd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTDAOFactory extends DAOFactory {

	public static HttpURLConnection crearConexion(String myurl) throws IOException {
		InputStream is = null;
		
		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//	Connection setup
			conn.setReadTimeout(10000 /*milliseconds*/);
			conn.setConnectTimeout(15000 /*milliseconds*/);
			conn.setDoInput(true);
			
			//	Starts the query
			return conn;
		//	Makes sure that the InputStream is closed after the app is
		//	finished using it
		} finally {	
			if (is != null) {
				is.close();
			}
		}
	}
	
	public static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		StringBuffer sb = new StringBuffer();
		int c;
		while ((c = reader.read()) != -1) sb.append((char) c);
		reader.close();
		return sb.toString();
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
