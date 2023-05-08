import java.util.Date;

public class Picture {
    private int id;
    private String title;
    private Date date;
    private String fitxategia;
    private int visitas;
    private int photographerId;

    public Picture(int id, String title, Date date, String fitxategia, int visitas, int photographerId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.fitxategia = fitxategia;
        this.visitas = visitas;
        this.photographerId = photographerId;
    }

    public int getId() {
        return id;
    }

    public String getFitxategia() {
        return fitxategia;
    }

    public int getVisitas() {
        return visitas;
    }
    public void incrementVisitas(){
        visitas++;
        //DBkonexion.updateVisitas(this);
    }
    @Override
    public String toString(){
        return this.title;
    }
}
