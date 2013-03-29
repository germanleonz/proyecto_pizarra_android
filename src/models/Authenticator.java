package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticator {

	private static final String mUrl = "http://192.168.1.103:8000/usuarios/login/";
	
	public boolean authenticate(String nombre_usuario, String clave) {
		String data_usuario;
		try {
			StringBuffer sb = new StringBuffer(mUrl);
			sb.append(nombre_usuario + "/");
			sb.append(clave + "/.json");
			
			data_usuario = downloadUrl(sb.toString());
			
			//	Parseamos la data JSON
			JSONObject mJSONObject = new JSONObject(data_usuario);
			String nombre_recibido = mJSONObject.getString("username");
			return nombre_recibido != null;
			
			//	Agregar datos de la sesion a los SharedPreferences con el SessionManager
		} catch (IOException ioe) {
			System.out.println("Error leyendo data " + ioe.getMessage());
		} catch (JSONException je) {
			System.out.println("Credenciales invalidas " + je.getMessage());
			return false;
		}
		return false;
	}
	
	private String downloadUrl(String myurl) throws IOException {
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
			System.out.println("HOLAAAAA");
			int response = conn.getResponseCode();
			System.out.println("The response code is " + response);
			is = conn.getInputStream();
			
			//	Convert the InputStream into a String
			String contentAsString = readIt(is);
			System.out.println("Data leida: " + contentAsString);
			return contentAsString;
			
		//	Makes sure that the InputStream is closed after the app is
		//	finished using it
		} finally {	
			if (is != null) {
				is.close();
			}
		}
	}
	
	public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		StringBuffer sb = new StringBuffer();
		int c;
		while ((c = reader.read()) != -1) sb.append((char) c);
		reader.close();
		return sb.toString();
	}
}
