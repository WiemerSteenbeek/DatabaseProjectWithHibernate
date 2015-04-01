
package DAO;
import domein_klassen.POJO_Interface;
public interface DAOInterface {

    void create(POJO_Interface obj) throws Exception;
    void update(POJO_Interface obj)throws Exception;
    POJO_Interface read(int id) throws Exception;
    void delete(Object object) throws Exception;
    
    
}
