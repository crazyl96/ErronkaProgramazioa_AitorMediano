public class Photographer {
    private int id;
    private String name;
    private boolean awarded;

    public Photographer(int id, String name, boolean awarded){
        this.id = id;
        this.name = name;
        this.awarded = awarded;
    }
    public int getId(){
        return  this.id;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
