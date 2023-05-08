import java.awt.*;
import java.net.CookieHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBkonexion {
    private static final String username = "root";
    private static final String password = "zubiri";
    private static final String url = "jdbc:mysql://localhost:3306/programazioa";
    private static Connection conexion;


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

    public static void updateVisits(Picture picture){
        String sql = "update Pictures set Visits = "+ picture.getVisitas() + "where PictureId = "+picture.getId();
        try(Statement statement = getConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);){
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("Errore bat egon da bisitak eguneratzerakoan: "+ ex);
        }
    }
}
