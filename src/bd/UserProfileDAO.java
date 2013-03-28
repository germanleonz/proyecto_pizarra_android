package bd;

import java.util.ArrayList;

import models.UserProfile;

public interface UserProfileDAO {
	public int crearUsuario(String nombre_usuario, String contrasena, String nombre,
			String apellido, String correo, String telefono);
	
	public boolean eliminar_usuario(String nombre_usuario);
	
	public ArrayList<UserProfile> buscarTodos();
	
	public ArrayList<UserProfile> buscarPorNombreUsuario(String nombre_usuario);
}
