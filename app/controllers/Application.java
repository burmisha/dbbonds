package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

// https://github.com/heroku/devcenter-java-database/blob/master/devcenter-java-database-plain-jdbc/src/main/java/com/heroku/example/Main.java
import java.net.URI; 
import java.net.URISyntaxException;
import java.sql.*;

public class Application extends Controller {
  
    public static Result index() {
    	try {
			URI dbUri = new URI(System.getenv("DATABASE_URL")); // 
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

			Connection connection = DriverManager.getConnection(dbUrl, username, password);

			// http://alvinalexander.com/java/edu/pj/jdbc/jdbc0003
				Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT sum(value) FROM numbers;");

			connection.close();
			// http://www.javamex.com/tutorials/database/jdbc_result_set.shtml#.UhONtVlOWSo
			return ok(index.render("Your new application is ready. Hey! " + rs.getString(1)));
        } catch (Exception e) {
            return ok(index.render("Feeling bad! " + e.getMessage()));
        }
    }
  
}
