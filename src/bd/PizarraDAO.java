package bd;

import java.util.ArrayList;

import models.Pizarra;

public interface PizarraDAO {
	public int crearPizara(String nombre_usuario, String contrasena, String nombre,
			String apellido, String correo, String telefono);
	
	public boolean eliminar_pizarra(String nombre_usuario);
	
	public ArrayList<Pizarra> buscarTodas();
	
	public ArrayList<Pizarra> buscarPorIdPizarra(String id_pizarra);

}
