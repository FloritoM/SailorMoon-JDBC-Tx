package datos;

import dominio.Planetas;
import dominio.SailorScouts;
import java.sql.SQLException;
import java.util.List;

public interface iCRUDSailorScoutsDAO {
    
    public List<SailorScouts> select() throws SQLException;
    
    public List<Planetas> selectAllPlanets() throws SQLException;
    
    public void insert(SailorScouts sailorInsert) throws SQLException;
    
    public void update(SailorScouts sailorUpdate) throws SQLException;
    
    public void delete(SailorScouts sailorDelete) throws SQLException;
    
    public boolean checkID(SailorScouts sailor) throws SQLException;
}
