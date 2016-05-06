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
    assertEquals(0, newClient.getClientId());
  }

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void save_clientIsSavedClient(){
    Client newClient = new Client("Brian", 1);
    newClient.save();
    assertTrue(newClient.getClientId() == Client.all().get(0).getClientId());
  }

  @Test
  public void find_findsClientInDatabase_True() {
    Client newClient = new Client("Brian", 1);
    newClient.save();
    Client savedClient = Client.find(newClient.getClientId());
    assertTrue(newClient.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist  newStylist = new Stylist("Brian");
    newStylist.save();
    Client myClient = new Client("Nikol", newStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getClientId());
    assertEquals(savedClient.getStylistId(), newStylist.getId());
  }

  @Test
  public void update_updateClientNme_true(){
    Client newClient = new Client("Brian", 1);
    newClient.save();
    newClient.update("Brian Smith");
    assertEquals("Brian Smith", Client.find(newClient.getClientId()).getName());
  }
}
