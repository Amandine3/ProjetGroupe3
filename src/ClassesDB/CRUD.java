package ClassesDB;


/**
 * Interface CRUD
 * @author Amandine Vandevoir & Aurélien Vandaele
 */

public interface CRUD 
{
  public void create()throws Exception;
  public void read() throws Exception;
  public void update() throws Exception;
  public void delete() throws Exception;
}