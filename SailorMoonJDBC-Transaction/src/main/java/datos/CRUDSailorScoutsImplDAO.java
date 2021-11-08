package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Planetas;
import java.sql.*;
import dominio.SailorScouts;
import java.util.ArrayList;
import java.util.List;

public class CRUDSailorScoutsImplDAO implements iCRUDSailorScoutsDAO {
    
    // SELECT
    private static final String SELECT_JOIN = "SELECT s.id_sailor, s.nombre, s.apellido, p.planeta FROM sailors as s " +
                                              "INNER JOIN planetas as p ON s.id_sailor=p.id_sailor";
    private static final String SELECT_ALL_PLANETS = "SELECT DISTINCT planeta FROM planetas";
    
    // INSERT
    private static final String INSERT_SAILOR = "INSERT INTO sailors(nombre, apellido) VALUES(?, ?)";
    private static final String INSERT_PLANET = "INSERT INTO planetas(planeta, id_sailor) VALUES(?, ?)";
    
    // UPDATE
    private static final String UPDATE_SAILOR = "UPDATE sailors SET nombre = ?, apellido = ? WHERE id_sailor = ?";
    private static final String UPDATE_PLANET = "UPDATE planetas SET planeta = ? WHERE id_sailor = ?";
    
    // DELETE
    private static final String DELETE_SAILOR = "DELETE FROM sailors where id_sailor = ?";
    
    private static final String SQL_CHECK = "SELECT * FROM sailors WHERE id_sailor=?";
    
    
    @Override
    public List<SailorScouts> select() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt_sailors = null;
        ResultSet rs_sailors = null;
        SailorScouts sailor = null;
        List<SailorScouts> sailorScouts = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection(); 
            stmt_sailors = conn.prepareStatement(SELECT_JOIN);
            rs_sailors = stmt_sailors.executeQuery();
            
            while(rs_sailors.next()){
                String nombre = rs_sailors.getString("nombre");
                String apellido = rs_sailors.getString("apellido");
                int id_sailor = rs_sailors.getInt("id_sailor");
                String planeta = rs_sailors.getString("planeta");
                
                sailor = new SailorScouts(id_sailor, nombre, apellido, planeta);
                sailorScouts.add(sailor);
            }
            
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs_sailors); 
                Conexion.close(stmt_sailors);
                
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return sailorScouts;
    }
    
    @Override
    public List<Planetas> selectAllPlanets() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt_planets = null;
        ResultSet rs_planets = null;
        List<Planetas> planetas = new ArrayList<>(); // !!!!
        
        try {
            conn = Conexion.getConnection(); 
            stmt_planets = conn.prepareStatement(SELECT_ALL_PLANETS);
            rs_planets = stmt_planets.executeQuery();
            
            while(rs_planets.next()){
                String planeta = rs_planets.getString("planeta");
                Planetas planetNew = new Planetas(planeta);
                planetas.add(planetNew);
            }
            
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs_planets); 
                Conexion.close(stmt_planets);
                
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return planetas;
    }
    
    @Override
    public void insert(SailorScouts sailorInsert) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt_sailor = null, stmt_planet = null;
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection();

            stmt_sailor = conn.prepareStatement(INSERT_SAILOR, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt_sailor.setString(1, sailorInsert.getNombre());
            stmt_sailor.setString(2, sailorInsert.getApellido());
            stmt_sailor.executeUpdate(); 
            
            rs = stmt_sailor.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            
            stmt_planet = conn.prepareStatement(INSERT_PLANET);
            stmt_planet.setString(1, sailorInsert.getPlaneta());
            stmt_planet.setInt(2, id);
            stmt_planet.executeUpdate();

            conn.commit();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            conn.rollback();
        } finally{
            try {
                Conexion.close(stmt_sailor);
                Conexion.close(stmt_planet);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    @Override
    public void update(SailorScouts sailorUpdate) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt_sailor = null, stmt_planet = null;
        try {
            conn = getConnection();
            stmt_sailor = conn.prepareStatement(UPDATE_SAILOR);
            stmt_sailor.setString(1, sailorUpdate.getNombre());
            stmt_sailor.setString(2, sailorUpdate.getApellido());
            stmt_sailor.setInt(3, sailorUpdate.getId_sailor());
            stmt_sailor.executeUpdate();
            
            stmt_planet = conn.prepareStatement(UPDATE_PLANET);
            stmt_planet.setString(1, sailorUpdate.getPlaneta());
            stmt_planet.setInt(2, sailorUpdate.getId_sailor());
            stmt_planet.executeUpdate();
            
            conn.commit();
            
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt_sailor);
                close(stmt_planet);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    @Override
    public void delete(SailorScouts sailorDelete) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE_SAILOR);
            stmt.setInt(1, sailorDelete.getId_sailor());

            stmt.executeUpdate();
            
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    @Override
    public boolean checkID(SailorScouts sailor){
        Connection conn = null;
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        
        try {
            conn = Conexion.getConnection(); 
            stmt = conn.prepareStatement(SQL_CHECK);
            stmt.setString(1, sailor.getPlaneta());
            rs = stmt.executeQuery();
            
            //rs.next();
            
            if(rs.absolute(1)){
                return true;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally { 
            try {
                Conexion.close(rs); 
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return false;
    }
}
