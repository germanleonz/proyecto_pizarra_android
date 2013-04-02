package bd;

import java.util.ArrayList;

import models.UserProfile;

public interface UserProfileDAO {
	public boolean crearUsuario(UserProfile nuevo_up);
	
	public boolean eliminar_usuario(String nombre_usuario);
	
	public ArrayList<UserProfile> buscarTodos();
	
	public ArrayList<UserProfile> buscarPorNombreUsuario(String nombre_usuario);
}
