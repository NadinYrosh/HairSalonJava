import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sql2o.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void StylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add stylist"));
    fill("#name").with("Bobby");
    submit(".btn");
    assertThat(pageSource()).contains("You have added a new stylist.");
  }

  @Test
  public void StylistIsDisplayed() {
    Stylist newStylist = new Stylist("Bobby");
    newStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists");
    goTo(stylistPath);
    assertThat(pageSource()).contains("Bobby");
  }

  @Test
  public void stylistsPageDisplaysNames() {
    Stylist newStylist = new Stylist("Bobby");
    newStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists");
    goTo(stylistPath);
    assertThat(pageSource()).contains("Add stylist");
  }

  @Test
    public void clientIsAddedAndDisplayed() {
      goTo("http://localhost:4567/stylist/new");
      fill("#name").with("Bobby");
      submit(".btn");
      click("a", withText("View stylists list"));
      click("a", withText("Bobby"));
      fill("#client").with("Lucka");
      submit("#add_client");
      assertThat(pageSource()).contains("Lucka");
    }

  @Test
    public void getAllClients() {
      goTo("http://localhost:4567/");
      click("a", withText("View clients list"));
      submit(".btn");
      assertThat(pageSource()).contains("Here is a list of clients:");
    }

}
