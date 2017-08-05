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
import java.util.ArrayList;

public class PlayerManager {
	
	private Player user;

	private File dirUsers;
	
	private ArrayList<String> userlist;
	
	public void initPlayer(String playername){
			
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath(); 
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path+"users";
		dirUsers = new File(path);
		
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
	
	public PlayerManager(){
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		path = path+"users";
		dirUsers = new File(path);
		
		File[] filelist = dirUsers.listFiles();
		userlist = new ArrayList<String>();
		
		for (int i=0;i<filelist.length;i++){
			if (filelist[i].isFile()){
				String name = filelist[i].getName();
				String noext = name.substring(0, name.length()-4);
				userlist.add(noext);
			}
		}
	}
	
	public String[] getUserlist(){
		String[] list = new String[userlist.size()];
		for (int i=0;i<userlist.size();i++){
			list[i] = userlist.get(i);
		}
		return (list);
	}
	
	public boolean isUser(String user){
		int i=0;
		while (i<userlist.size()){
			System.out.println("Lista: "+userlist.get(i)+" Nuevo usuario: "+user);
			if (user.equals(userlist.get(i))) return true;
			else i++;
		}
		return false;
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
