package ClassesDB;

/**
 * Classe Utilisateur
 * @author Amandine Vandevoir & Aurélien Vandaele
 */
public class Utilisateur 
{
	/**
 	* Dans l'ordre (de gauche à droite) : pseudo(identifiant unique), mot de passe, nom, prénom, numéro de gsm de l'utilisateur
 	*/
	protected String pseudo, motdepasse, nom, prenom, numgsm;
    
	/**
	 * constructeur par défaut
	*/
    public Utilisateur()
    {
        
    }
    
   /**
    *	constructeur paramétré
 	* @param pseudo pseudo de l'utilisateur
 	* @param motdepasse mot de passe de l'utilisateur
 	* @param nom nom de l'utilisateur
 	* @param prenom prénom de l'utilisateur
 	* @param numgsm numéro de gsm de l'utilisateur
 	*/
    public Utilisateur(String pseudo, String motdepasse, String nom, String prenom, String numgsm)
    {
        this.pseudo = pseudo;
        this.motdepasse=motdepasse;
        this.nom = nom;
        this.prenom = prenom;
        this.numgsm=numgsm;
    }

    /**
     * getter pseudo
     * @return pseudo de l'utilisateur
     */
    public String getPseudo()
    {
        return pseudo;
    }
    
    /**
     * setter pseudo
     * @param id_client identifiant du client
     */
    public void setPseudo(String pseudo)
    {
    	this.pseudo=pseudo;
    }
    /**
    * getter motdepasse
    * @return mot de passe de l'utilisateur
    */
    public String getMotdepasse()
    {
        return motdepasse;
    }
   
   /**
    * setter motdepasse
    * @param mot de passe de l'utilisateur
    */
    public void setMotdepasse(String motdepasse)
    {
    	this.motdepasse=motdepasse;
    }

    /**
     * getter nom
     * @return nom de l'utilisateur
     */
    public String getNom()
    {
    	return nom;
    }

    /**
     * setter nom de l'utilisateur
     * @param nom nom de l'utilisateur
     */
    public void setNom(String nom)
    {
    	this.nom = nom;
    }

    /**
     * getter prénom de l'utilisateur
     * @return prénom de l'utilisateur
     */
    public String getPrenom()
    {
    	return prenom;
    }

    /**
     * setter prenom de l'utilisateur
     * @param prenom prénom de l'utilisateur
     */
    public void setPrenom(String prenom)
    {
    	this.prenom = prenom;
    }

    /**
     * getter numgsm numgsm de l'utilisateur
     * @return numgsm numéro de gsm de l'utilisateur
     */
    public String getNumgsm()
    {
    	return numgsm;
    }

    /**
     * setter numgsm numgsm de l'utilisateur
     * @param numgsm numéro de gsm de l'utilisateur
     */
    public void setNumgsm(String numgsm)
    {
    	this.numgsm=numgsm;
    }

   /**
    * méthode toString
    * @return informations complètes de l'utilisateur
    */
    @Override
    public String toString()
    {
    	return "Utilisateur [pseudo=" + pseudo + ", motdepasse="
    			+ motdepasse + ", nom=" + nom + ", prenom=" + prenom
    			+ ", numgsm=" + numgsm + "]";
	} 
}
