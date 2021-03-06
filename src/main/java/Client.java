import org.sql2o.*;
import java.util.Arrays;
import java.util.List;



public class Client {
  private int id;
  private int stylist_id;
  private String name;

  public Client(String name, int stylist_id){
    this.name = name;
    this.stylist_id = stylist_id;

  }

  public String getName(){
    return name;
  }

  public int getStylistId(){
    return stylist_id;
  }


  public int getClientId(){
    return id;
  }



  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)){
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return newClient.getName().equals(this.getName()) &&
             newClient.getClientId() == (this.getClientId())&&
             newClient.getStylistId() == (this.getStylistId());
    }
  }

  public static List<Client> all(){
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }


  public void save(){
    String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :stylist_id)";
    try (Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static Client find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client =  con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name){
    try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  // public List<Client> all(){
  //   try (Connection con = DB.sql2o.open()){
  //     String sql = "SELECT * FROM clients";
  //     return con.createQuery(sql).executeAndFetch(Client.class);
  //   }
  // }
}
