import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_InstantiatesCorrectly_true(){
    Stylist newStylist = new Stylist("Brian");
    assertTrue(newStylist instanceof Stylist);
  }

  @Test
  public void Stylist_returnNameCorrectly_name(){
    Stylist newStylist = new Stylist("Brian");
    assertEquals ("Brian", newStylist.getName());
  }

  @Test
  public void all_emptyAtFirst(){
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_stylistIsSavedCorrectly(){
    Stylist newStylist = new Stylist("Brian");
    newStylist.save();
    assertTrue(newStylist.equals(Stylist.all().get(0)));
  }

  @Test
  public void find_StylistInDataBase_true(){
    Stylist newStylist = new Stylist("Brian");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(newStylist.equals(savedStylist));
  }

  // @Test
  // public void getClients_retrieveAllClientsInStylistFromDatabase_true(){
  //   Stylist newStylist = new Stylist("Brian Broom");
  //   Client firstClient = new Client ("Dien Miller", newStylist.getId());
  //   firstClient.save();
  //   Client secondClient = new Client ("Lulu Promp", newStylist.getId());
  //   secondClient.save();
  //   Client[] clients = new Client[] {firstClient,secondClient};
  //   assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
  // }
}
