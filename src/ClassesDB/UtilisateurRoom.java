package ClassesDB;

/**
 * Classe UtilisateurRoom
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

public class UtilisateurRoom
{
	
	protected int idUtilisateurRoom, idRoom;
	protected String pseudo;
	
	public UtilisateurRoom(){
	}
	
	public UtilisateurRoom(int idUtilisateurRoom, int idRoom, String pseudo){
		this.idUtilisateurRoom=idUtilisateurRoom;
		this.idRoom=idRoom;
		this.pseudo=pseudo;
	}
	
	public String getPseudo(){
		return this.pseudo;
	}
	
	public int getIdUtilisateurRoom(){
		return this.idUtilisateurRoom;
	}
	
	public int getIdRoom(){
		return this.idRoom;
	}
	
	public void setPseudo(String pseudo){
		this.pseudo=pseudo;
	}
	
	public void setIdUtilisateurRoom(int idUtilisateurRoom){
		this.idUtilisateurRoom=idUtilisateurRoom;
	}
	
	public void setIdRoom(int idRoom){
		this.idRoom=idRoom;
	}

}
