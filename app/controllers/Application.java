package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import views.*;

// import controllers.*;

// https://github.com/heroku/devcenter-java-database/blob/master/devcenter-java-database-plain-jdbc/src/main/java/com/heroku/example/Main.java
// import java.net.URI; 
// import java.net.URISyntaxException;
// import java.sql.*;

// import java.util.Properties;

public class Application extends Controller {
  
	public static class Login {
		// @Required
	    public String email;
	    public String password;
	    public String validate() {
			if (!(email.equals("burmisha@gmail.com") && password.equals("password"))) {
	            return "Invalid email and password";
	        } else {
	        	return null;
	    	}
	    }    
    }

    // Login page.
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    // Handle login form submission.
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
        	//session("email", loginForm.get().email);
            return redirect(
                controllers.routes.Bonds.list()
            );
        }
    }


    public static Result index() {
    	try {
			// URI dbUri = new URI(System.getenv("DATABASE_URL")); // 
			// String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			
			// Properties props = new Properties();
			// props.setProperty("user", dbUri.getUserInfo().split(":")[0]);
			// props.setProperty("password", dbUri.getUserInfo().split(":")[1]);
			// props.setProperty("ssl", "true");
			// props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			// // String remote = "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

			// Connection connection = DriverManager.getConnection(dbUrl, props);

			// // http://alvinalexander.com/java/edu/pj/jdbc/jdbc0003
			// Statement stmt = connection.createStatement(); 
			// ResultSet rs = stmt.executeQuery("SELECT sum(value) FROM numbers;");

			// connection.close();
			// rs.next();
			// // http://www.javamex.com/tutorials/database/jdbc_result_set.shtml#.UhONtVlOWSo
			// return ok(index.render("Your new <b>application</b> is ready. Hey! " + rs.getString(1)));
			return ok(bondslist.render(""));
        } catch (Exception e) {
            return ok(bondslist.render("Feeling bad! " + e.getMessage()));
        }
    }

    public static Result hello() {
    	return ok(hello.render());
    }
  
}
