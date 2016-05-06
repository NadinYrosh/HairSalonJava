import java.util.List;
import org.sql2o.*;
import java.util.Arrays;

public class Stylist {
  private String name;
  private int id;



  public Stylist(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }


  public static List<Stylist> all(){
    String sql = "SELECT id,name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return newStylist.getName().equals(this.getName()) &&
             newStylist.getId() == (this.getId());
    }
  }

  public void save(){
    String sql = "INSERT INTO stylists (name) VALUES (:name)";
    try (Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist =  con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients(){
    try (Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id";
      return con.createQuery(sql).addParameter("stylist_id", this.id).executeAndFetch(Client.class);
    }
  }
}
