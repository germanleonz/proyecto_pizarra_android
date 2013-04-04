package bd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;

public class RESTDAOFactory extends DAOFactory {
	
	private static final String TAG = "RESTDAOFactory";
	//
	public static final String DJANGO_URL = "http://www.proyectopizarra.cloudns.org:8000";

	public static HttpURLConnection crearConexion(String myurl) throws IOException {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//	Configuracion de la conexion
			conn.setReadTimeout(10000 /*milisegundos*/);
			conn.setConnectTimeout(15000 /*milisegundos*/);
			
			return conn;
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
	
	public static String buildQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean primero = true;
		
		for (NameValuePair par : params) {
			if (primero)
				primero = false;
			else 
				result.append("&");
			
			result.append(URLEncoder.encode(par.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(par.getValue(), "UTF-8"));
		}
		return result.toString();
	}
	
	public static String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		
		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//	Connection setup
			conn.setReadTimeout(10000 /*milliseconds*/);
			conn.setConnectTimeout(15000 /*milliseconds*/);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			//	Starts the query
			conn.connect();
			int response = conn.getResponseCode();
			Log.d(TAG, "The response code is " + response);
			is = conn.getInputStream();
			
			//	Convert the InputStream into a String
			String contentAsString = RESTDAOFactory.readIt(is);
			Log.d(TAG, "Data leida: " + contentAsString);
			return contentAsString;
			
		//	Makes sure that the InputStream is closed after the app is
		//	finished using it
		} finally {	
			if (is != null) {
				is.close();
			}
		}
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
