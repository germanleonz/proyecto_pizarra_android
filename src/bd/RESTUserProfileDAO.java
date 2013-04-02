package bd;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import models.UserProfile;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class RESTUserProfileDAO implements UserProfileDAO {

	private static final String TAG = "RESTUserProfileDAO";
	
	@Override
	public boolean crearUsuario(UserProfile nuevo_up) {
		// Elaboramos el URL que enviaremos al servidor
		Log.d(TAG, "Comenzando creacion de nuevo usuario...");
		InputStream is = null;
		String response = null;
		
		StringBuffer sb = new StringBuffer(RESTDAOFactory.DJANGO_URL);
		sb.append("/usuarios/crear_usuario_rest/");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nuevo_nombre_usuario", nuevo_up.getNombre_usuario()));
		params.add(new BasicNameValuePair("nueva_password", nuevo_up.getContrasena()));
		params.add(new BasicNameValuePair("nuevo_nombre", nuevo_up.getNombre()));
		params.add(new BasicNameValuePair("nuevo_apellido", nuevo_up.getApellido()));
		params.add(new BasicNameValuePair("nuevo_correo", nuevo_up.getCorreo()));
		params.add(new BasicNameValuePair("nuevo_telefono", nuevo_up.getTelefono()));
		
		try {
			HttpURLConnection conn = RESTDAOFactory.crearConexion(sb.toString());
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);	
			conn.setDoInput(true);
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(os, "UTF-8"));
			writer.write(RESTDAOFactory.buildQuery(params));
			writer.close();
			conn.connect();

			//	Leemos la respuesta del servidor a nuestro POST request
			is = conn.getInputStream();
			response = RESTDAOFactory.readIt(is);
			
		} catch (IOException ioe) {
			System.out.println("Error leyendo data " + ioe.getMessage());
		}
		Log.d(TAG, "Usuario creado");
		System.out.println("Respuesta de crear usuario: " + response);
		return response != null;
	}

	@Override
	public boolean eliminar_usuario(String nombre_usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<UserProfile> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserProfile> buscarPorNombreUsuario(String nombre_usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
