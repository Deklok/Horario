package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.MyBatisUtils;
import modelo.pojos.Bloque;
import modelo.pojos.EE;
import modelo.pojos.Relacion;
import org.apache.ibatis.session.SqlSession;
 /**
  *  Interfaz para las consultas que se realizaran a la Base de Datos
*/
public class EEDAO {
    /**
    *  Obtiene todas las Experiencias Educativas guardadas
    *  @return List de EE
    */
    public static List<EE> getAllEE(){
        List<EE> lista = new ArrayList<EE>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("EE.getAllEE");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return lista;
    }
    
    /**
    *   Obtiene el nombre de la Experiencia educativa especifica con base a la id
    *   @param idEE - Id de la experiencia educativa
    *   @return String con el nombre de la experiencia educativa
    */
    public static String getEE(Integer idEE)
    {
        SqlSession conn = null;
        String nombre = "";
        try{
            conn = MyBatisUtils.getSession();
            nombre = conn.selectOne("EE.getEE",idEE);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return nombre;
    }
    
    /**
    *   Obtiene todos los Bloques (relacion dia-hora-ee-salon) guardados
    *   @return List de Bloques
    */
    public static List<Bloque> getAllBloques(){
        List<Bloque> lista = new ArrayList<Bloque>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("EE.getAllBloques");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return lista;
    }
    
    /**
    *   Obtiene todos los Salones de una Experiencia Educativa especifica
    *   @param idEE - Id de la Experiencia Educativa
    *   @return List de Strings
    */
    public static List<String> getAllSalones(Integer idEE){
        List<String> lista = new ArrayList<String>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("EE.getAllSalonesFromEE",idEE);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return lista;
    }
    
    /**
    *   Obtiene todos los Salones guardados
    *   @return List de Strings con los nombres de los salones
    */
    public static List<String> getAllSalones(){
        List<String> lista = new ArrayList<String>();
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            lista = conn.selectList("EE.getAllSalones");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return lista;
    }
    
    /**
    *  Obtiene el nombre del Salon con base en una ID proporcionada
    *  @param idSalon - id del salon a buscar
    *  @return String con el nombre del salon
    */
    public static String getSalon(Integer idSalon)
    {
        SqlSession conn = null;
        String nombre = "";
        try{
            conn = MyBatisUtils.getSession();
            nombre = conn.selectOne("EE.getSalon",idSalon);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return nombre;
    }
    
    /**
     * Obtiene la ID de la Experiencia Educativa con base a su nombre
     * @param nombre de la experiencia educativa
     * @return ID de la experiencia educativa
     */
    public static Integer obtenerIdEE(String nombre){
        Integer id = 0;
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            id = conn.selectOne("EE.obtenerIdEE",nombre);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return id;
    }
    
    /**
     * Obtiene ID del Salon con base a su nombre
     * @param salon
     * @return id del salon
     */
    public static Integer obtenerIdSalon(String salon){
        Integer id = 0;
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            id = conn.selectOne("EE.obtenerIdSalon",salon);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return id;
    }
    
    /**
     * Especifica si existe un salon con el mismo nombre guardado
     * @param salon
     * @return true si este esta guardado, false si no
     */
    public static boolean existeSalon(String salon){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            int num = conn.selectOne("EE.existeSalon",salon);
            if(num>0){
                return true;   
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Registra una Experiencia educativa
     * @param e - EE a registrar
     * @return true si la consulta se realizo correctamente
     */
    public static boolean registrarEE(EE e){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            conn.insert("EE.registrarEE",e);
            conn.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Registra un salon
     * @param salon
     * @return true si la consulta se realizo correctamente
     */
    public static boolean registrarSalon(String salon){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            conn.insert("EE.registrarSalon",salon);
            conn.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Registra las relaciones de las Experiencias educativas con los salones en donde se pueden impartir
     * @param r - relacion ee con salon
     * @return true si la consulta se realizo correctamente
     */
    public static boolean registrarRelacionEE_Salon(Relacion r){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            conn.insert("EE.registrarRelacionEE_Salon",r);
            conn.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null)
            {
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Registra un bloque que especifica la hora y dia que se imparte una experiencia educatvia en determinado salon
     * @param b - Bloque con hora, dia, salon y ee
     * @return true si la consulta se realizo correctamente
     */
    public static boolean registrarBloque(Bloque b)
    {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.insert("EE.registrarBloque",b);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn!=null)
            {
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Elimina una Experiencia educativa guardada
     * @param idEE - id de la experiencia educativa
     * @return true si la consulta se realizo correctamente
     */
    public static boolean eliminarEE(Integer idEE)
    {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.delete("EE.eliminarBloques",idEE);
            conn.delete("EE.eliminarSalones",idEE);
            conn.delete("EE.eliminarEE",idEE);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn!=null)
            {
                conn.close();
            }
        }
        return false;
    }
    
    /**
     * Elimina los bloques de una determinada Experiencia educativa
     * @param idEE - id de la experiencia educativa
     * @return true si la consulta se realizo correctamente
     */
    public static boolean eliminarBloques(Integer idEE)
    {
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            conn.delete("EE.eliminarBloques",idEE);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn!=null)
            {
                conn.close();
            }
        }
        return false;
    }
     
    /**
     * Elimina un bloque en especifico con base a su hora y dia
     * @param b - Dia y hora
     * @return true si al consulta se realizo correctamente
     */
    public static boolean eliminar(Relacion b){
        SqlSession conn = null;
        try{
            conn = MyBatisUtils.getSession();
            conn.delete("eliminar",b);
            conn.commit();
            System.out.println("eliminacion correcta");
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
}
