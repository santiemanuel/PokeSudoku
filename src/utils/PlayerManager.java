package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PlayerManager {
	
	private Player user;

	private File dirUsers;
	
	public PlayerManager(String playername){
		
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); 
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path+"users";
		dirUsers = new File(path);;
		
		System.out.println("Directorio usuarios: "+dirUsers.toString());
		if (!dirUsers.exists()){
			dirUsers.mkdir();
		}
		
		File fileUser = new File(dirUsers+File.separator+playername+".ser");
		
		System.out.println("Archivo usuario: "+fileUser);
		
		if (fileUser.exists()){
			user = loadUser(fileUser);
			System.out.println("Usuario cargado con exito");
		}
		else {
			System.out.println("Creando nuevo usuario");
			user = new Player(playername, 20);
		}
	}
	
	public Player loadUser(File fileuser){
		try{
			System.out.println("Abriendo archivo de usuario");
			FileInputStream inputstream = new FileInputStream(fileuser);
			ObjectInputStream objectstream = new ObjectInputStream(inputstream);
			
			Player user = (Player) objectstream.readObject();
			
			objectstream.close();
			inputstream.close();
			
			return user;
		} catch (IOException e){
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void saveUser(){
		System.out.println("Guardando estado usuario");
		String name = user.getName();
		File userfile = new File(dirUsers+File.separator+name+".ser");
		System.out.println("Directorio guardado: "+userfile.toString());
		try {
			FileOutputStream userstream = new FileOutputStream(userfile, false);
			OutputStream buffer = new BufferedOutputStream(userstream);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(user);
			output.flush();
			output.close();
			System.out.println("Usuario guardado.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player getUser() {
		return user;
	}

	public void setUser(Player user) {
		this.user = user;
	}
}
