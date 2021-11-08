package cliente;

import datos.CRUDSailorScoutsImplDAO;
import dominio.Planetas;
import dominio.SailorScouts;
import java.sql.SQLException;
import java.util.List;

public class Prueba {
    public static void main(String[] args) throws SQLException {

        CRUDSailorScoutsImplDAO dao = new CRUDSailorScoutsImplDAO();
        
        
        SailorScouts sailorJupiter = new SailorScouts();
        sailorJupiter.setNombre("Lita");
        sailorJupiter.setApellido("Kino");
        sailorJupiter.setPlaneta("Jupiter");
        
        //dao.insert(sailorJupiter);
        
        SailorScouts sailorDelete = new SailorScouts();
        sailorDelete.setId_sailor(5);
        
        //dao.delete(sailorDelete);
        
        SailorScouts sailorUpdate = new SailorScouts();
        sailorUpdate.setId_sailor(4);
        sailorUpdate.setNombre("Serenita");
        sailorUpdate.setApellido("Tsuki");
        sailorUpdate.setPlaneta("Lunis");
        
        //dao.update(sailorUpdate);

        List<SailorScouts> sailors = dao.select();
        for (SailorScouts sailor : sailors) {
            System.out.println("ID: " + sailor.getId_sailor() + 
                    "\n Nombre: " + sailor.getNombre() +
                    "\n Apellido: " + sailor.getApellido() + 
                    "\n Planeta: " + sailor.getPlaneta());
        }

    }
}
