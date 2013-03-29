package bd;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import models.UserProfile;

public class RESTUserProfileDAO implements UserProfileDAO {

	private static final String usuariosAPIURL = "http://192.168.1.103:8000/usuarios/login/";
	
	@Override
	public boolean crearUsuario(String nombre_usuario, String contrasena,
			String nombre, String apellido, String correo, String telefono) {
		// Elaboramos el URL que enviaremos al servidor
		InputStream is = null;
		String JSONResponse = null;
		
		StringBuffer sb = new StringBuffer(usuariosAPIURL);
		sb.append(nombre_usuario + "/");
		sb.append(contrasena + "/");
		sb.append(nombre + "/");
		sb.append(apellido + "/");
		sb.append(correo + "/");
		sb.append(telefono + "/");
		
		try {
			HttpURLConnection conn = RESTDAOFactory.crearConexion(sb.toString());
			conn.setRequestMethod("POST");

			conn.connect();

			is = conn.getInputStream();
			JSONResponse = RESTDAOFactory.readIt(is);
		} catch (IOException ioe) {
			System.out.println("Error leyendo data " + ioe.getMessage());
		}
		return JSONResponse != null;
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
