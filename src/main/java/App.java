import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("clients", newStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      model.put("stylist", newStylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client newclient = new Client (request.queryParams("client"),newStylist.getId());
      newclient.save();
      // System.out.println(newCuisine.getRestaurants().get(0).getName());
      model.put("clients", newStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      model.put("stylist", newStylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/clients", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Client> clients = Client.all();
      model.put("clients", clients);
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
