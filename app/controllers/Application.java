package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import views.*;
import java.util.*;

// import controllers.*;

// https://github.com/heroku/devcenter-java-database/blob/master/devcenter-java-database-plain-jdbc/src/main/java/com/heroku/example/Main.java
import java.net.URI; 
import java.net.URISyntaxException;
import java.sql.*;

import java.util.Properties;

public class Application extends Controller {
  
	public static class Login {
		// @Required
	    public String email;
	    public String password;
	    public String validate() {
	    	DataBaseHandler dbh = new DataBaseHandler();
			int traderId = dbh.checkLogin(email, password);
			dbh.closeConnection();
			if (traderId > 0) {
				String s = Integer.toString(traderId);
				session("traderId", s);
	        	return null;
	        } else {
	        	return "Invalid email and password";
	    	}
			// if (!(email.equals("burmisha@gmail.com") && password.equals("password"))) {
	  //           return "Invalid email and password";
	  //       } else {
	  //       	session("connected", email);
	  //       	return null;
	  //   	}
	    }    
    }

    public static Result login() {
        return ok(
            login.render(form(Login.class))  // Login page.
        );
    }
    
    // Handle login form submission.
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
        	//session("email", loginForm.get().email);
        	DataBaseHandler dbh = new DataBaseHandler();
            return redirect(
                controllers.routes.Clients.list()
            );
        }
    }


   //  public static Result index() {
   //  	try {
			// // URI dbUri = new URI(System.getenv("DATABASE_URL")); // 
			// // String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			
			// // Properties props = new Properties();
			// // props.setProperty("user", dbUri.getUserInfo().split(":")[0]);
			// // props.setProperty("password", dbUri.getUserInfo().split(":")[1]);
			// // props.setProperty("ssl", "true");
			// // props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			// // // String remote = "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

			// // Connection connection = DriverManager.getConnection(dbUrl, props);

			// // // http://alvinalexander.com/java/edu/pj/jdbc/jdbc0003
			// // Statement stmt = connection.createStatement(); 
			// // ResultSet rs = stmt.executeQuery("SELECT sum(value) FROM numbers;");

			// // connection.close();
			// // rs.next();
			// // // http://www.javamex.com/tutorials/database/jdbc_result_set.shtml#.UhONtVlOWSo
			// // return ok(index.render("Your new <b>application</b> is ready. Hey! " + rs.getString(1)));
			// DataBaseHandler dbh = new DataBaseHandler();
			// List<Bond> bl = dbh.getBonds();
			// return ok(bondslist.render(bl));
   //      } catch (Exception e) {
   //          return ok("bad!");
   //      }
   //  }

    public static Result hello() {
    	return ok(hello.render());
    }
  
}
