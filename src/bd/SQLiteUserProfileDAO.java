package bd;

import java.util.ArrayList;

import models.UserProfile;

public class SQLiteUserProfileDAO implements UserProfileDAO {

	@Override
	public boolean crearUsuario(String nombre_usuario, String contrasena,
			String nombre, String apellido, String correo, String telefono) {
		// TODO Auto-generated method stub
		return false;
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
