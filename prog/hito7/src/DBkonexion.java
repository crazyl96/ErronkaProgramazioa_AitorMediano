import javax.swing.*;
import java.awt.*;
import java.net.CookieHandler;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class DBkonexion{
    private static final String username = "root";
    private static final String password = "zubiri";
    private static final String url = "jdbc:mysql://localhost:3306/programazioa";
    private static Connection conexion;

    private Map<Integer,Integer> hashMap;

//emen metodo bat non gonexio bat egten degu
    public static Connection getConexion(){
        if(conexion == null){
            try{
                conexion = DriverManager.getConnection(url,username,password);
                System.out.println("Datu basera konektatu da.");
            } catch (SQLException ex){
                System.out.println("Errore bat egon da konexioarekin: "+ ex);
            }
        }
        return conexion;
    }
//emen metodo hay sortzen degu, Array list bat bueltatzeko
    public static List<Photographer> cargarFotografos(){
        String sql = "SELECT * FROM Photographers";
        List<Photographer> fotografos = new ArrayList<>();
        try(Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);){
            while (resultSet.next()){
                int id = resultSet.getInt("PhotographerId");
                String name = resultSet.getString("Izena");
                boolean awarded = resultSet.getBoolean("Awarded");
                Photographer fotografo = new Photographer(id,name,awarded);
                fotografos.add(fotografo);

            }
        }catch (SQLException ex){
            System.out.println("zerbait gaizki dago fotografiekin: "+ex);
        }
        return fotografos;
    }
//emen Array list bat bueltatzen degu eta sartzen diren datuak izan behar dira Int eta Date
    public static List<Picture> cargarImagen(int photographerId, java.util.Date date){
        String sql;
        //daten jarri vearko dugu java.sql.Date artzeko gure datubasearen formatoa
        if(date != null){
            sql = "SELECT * FROM Pictures WHERE Photographe_id = "+ photographerId+ " AND Fecha >= '"+new java.sql.Date(date.getTime())+"'";

        }else {
            sql = "SELECT * FROM Pictures WHERE Photographe_id = "+ photographerId;
        }

        List<Picture> argazkiak = new ArrayList<>();
        try (Statement statement = getConexion().createStatement();
             ResultSet resultSet = statement.executeQuery(sql);){

            while (resultSet.next()){
                int id = resultSet.getInt("Pictureld");
                String title = resultSet.getString("Title");
                Date dateValue = resultSet.getDate("Fecha");
                String file = resultSet.getString("Fitxategia");
                int visits = resultSet.getInt("Visits");
                Picture picture = new Picture(id,title,dateValue,file,visits,photographerId);
                argazkiak.add(picture);
            }
        }catch (SQLException ex){
            System.out.println("Errore bat egon da argazkiak kargatzeko: "+ ex);
        }
        return argazkiak;
    }
//emen metodo hau egiten duguna gure zerbitzarian Update bat non bisitak igoko dira Picture objetu bat sartzerakoan
    public static void updateVisits(Picture picture){
        String sql = "update Pictures set Visits = "+ picture.getVisitas() + "where PictureId = "+picture.getId();
        try(Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);){
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Errore bat egon da bisitak eguneratzerakoan: "+ ex);
        }
    }
//emen sortzen degu hashmap bat
    public HashMap<Integer,Integer> createVisitsMap(){
        hashMap = new HashMap<>();
        String sql = "SELECT Photographe_id, sum(visits) as total_visits from pictures group by Photographe_id";
        try(Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                int idPhotographer = resultSet.getInt("Photographe_id");
                int visits = resultSet.getInt("total_visits");

                hashMap.put(idPhotographer,visits);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (HashMap<Integer, Integer>) hashMap;
    }
//emen update bat egiten degu award kolumnan
    public void awardUpdate(int visits){
      String sql = "update photographers set photographers.awarded= photographers.awarded+1 where PhotographerId= "+visits;
       try(Statement statement = getConexion().createStatement();
           ResultSet resultSet = statement.executeQuery(sql)){
           Iterator<Map.Entry<Integer,Integer>> entry = hashMap.entrySet().iterator();

           while (entry.hasNext()){
               Map.Entry<Integer,Integer> entry1 = entry.next();
               if(entry1.getValue()>visits){
                    try (Statement statement1 = getConexion().createStatement();
                         ResultSet resultSet1 = statement1.executeQuery(sql)){

                        JOptionPane.showMessageDialog(null, "Aktualizatu dira bisitak");
                    }
               }
           }
           JOptionPane.showMessageDialog(null, "Sari guztiak eman dira");
       }catch (SQLException ex){
           throw new RuntimeException(ex);
       }
    }
//emen metodo hay botoia remove presionatzerakoan deituko dio metodo oni borratzeko datu basean
    public void ezabatu(){
        String sql = "SELECT * FROM Pictures INNER JOIN Photographers ON Pictures.Photographe_id = Photographers.PhotographerId WHERE Pictures.visits = 0 and Photographers.awarded = 0";

        try(Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            if(resultSet.next()){
                while (resultSet.next()){
                    String fotografo = resultSet.getString("Izena");
                    String title = resultSet.getString("Title");
                    String pictureId = resultSet.getString("Pictureld");

                    int jop = JOptionPane.showConfirmDialog(null, "Ezabatu nahi dezu"+fotografo+ " onen pictures "+title);

                    if(jop == JOptionPane.YES_OPTION){

                        String delete = "delete from Pictures WHERE Pictureld = "+pictureId;

                        try(Statement statement1 = getConexion().createStatement();
                            ResultSet resultSet1 = statement1.executeQuery(delete)){

                            JOptionPane.showMessageDialog(null, fotografo+" ren argazkia borratu egin da.");
                        }
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null, "ez dira kondizioak betetzen");
            }

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
