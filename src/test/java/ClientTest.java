import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_InstantiatesCorrectly_true(){
    Client newClient = new Client("Brian", 1);
    assertTrue(newClient instanceof Client);
  }

  @Test
  public void getName_gettingTheName_String(){
    Client newClient = new Client("Brian", 1);
    assertEquals("Brian", newClient.getName());
  }


  @Test
  public void getClientId_gettingTheId_Int(){
    Client newClient = new Client("Brian", 1);
    assertEquals(1, newClient.getClientId());
  }

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_clientIsSavedClient(){
    Client newClient = new Client("Brian", 1);
    newClient.save();
    assertTrue(newClient.getId() == Client.all().get(0).getId());
  }

  @Test
  public void find_findsClientInDatabase_True() {
    Client newClient = new Client("Brian", 1);
    newClient.save();
    Client savedClient = Client.find(newClient.getId());
    assertTrue(newClient.equals(savedClient));
  }
  //
  // @Test
  // public void save_savesCuisineIdIntoDB_true() {
  //   Cuisine myCuisine = new Cuisine("Indian");
  //   myCuisine.save();
  //   Restaurant myRestaurant = new Restaurant("Luca's", "11-7", myCuisine.getId());
  //   myRestaurant.save();
  //   Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
  //   assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
  // }
}
