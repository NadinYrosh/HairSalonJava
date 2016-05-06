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

  // @Test
  // public void find_StylistInDataBase_true(){
  //   Stylist newStylist = new Stylist("Brian");
  //   newStylist.save();
  //   Stylist savedStylist = Stylist.find(newStylist.getId());
  //   assertTrue(newStylist.equals(savedStylist));
  // }

  // @Test
  // public void getRestaurants_retrieveAllRestaurantsInCuisineFromDatabase_true(){
  //   Cuisine myCuisine = new Cuisine("Indian");
  //   Restaurant firstRestaurant = new Restaurant ("Luca's", "11-7", myCuisine.getId());
  //   firstRestaurant.save();
  //   Restaurant secondRestaurant = new Restaurant ("Mark's", "11-7", myCuisine.getId());
  //   secondRestaurant.save();
  //   Restaurant[] restaurants = new Restaurant[] {firstRestaurant,secondRestaurant};
  //   assertTrue(myCuisine.getRestaurants().containsAll(Arrays.asList(restaurants)));
  // }
}
