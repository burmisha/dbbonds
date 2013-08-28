package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;
import views.*;
import java.util.*;

// https://github.com/heroku/devcenter-java-database/blob/master/devcenter-java-database-plain-jdbc/src/main/java/com/heroku/example/Main.java
import java.net.URI; 
import java.net.URISyntaxException;
import java.sql.*;

import java.util.Properties;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
 
	public static class Login {
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
		}	
	}

	public static class Search {
		public String 	lowRating, 			highRating, 				
						lowCoupon, 			highCoupon, 
						lowCurrentYield, 	highCurrentYield, 	
						lowMaturityYield, 	highMaturityYield, 
						lowDate, 			highDate, 					
						lowParValue, 		highParValue, 
						lowPrice, 			highPrice;
		// public List<Bond> execute() {
		// 	DataBaseHandler dbh = new DataBaseHandler();
		// 	List<String> lBound = new ArrayList<String>();
		// 	lBound.add("highRating);
		// 	lBound.add("highCoupon);
		// 	lBound.add("highCurrentYield);
		// 	lBound.add("highMaturityYield);
		// 	lBound.add("highDate);
		// 	lBound.add("highParValue);
		// 	lBound.add("highPrice);
		// 	List<String> hBound = new ArrayList<String>();
		// 	hBound.add("highRating);
		// 	hBound.add("highCoupon);
		// 	hBound.add("highCurrentYield);
		// 	hBound.add("highMaturityYield);
		// 	hBound.add("highDate);
		// 	hBound.add("highParValue);
		// 	hBound.add("highPrice);
		// 	List<Bond> bondsList = dbh.searchBonds(lBound, hBound);
		// 	dbh.closeConnection();
		// 	return bondsList;
		// }
		// public String validate() {
		// 	return null;
		// }
	}

	public static Result login() { // Login page
		return ok(login.render(form(Login.class))); 
	}

	public static Result search() { // Empty search page
		return ok(search.render(form(Search.class))); 
	}

	public static Result filter() { // SERP
		Form<Search> searchForm = form(Search.class).bindFromRequest();
		if (searchForm.hasErrors()) {
			return badRequest("bad"); //;search.render(searchForm));
		} else {
			List<String> lBound = new ArrayList<String>();
			lBound.add(searchForm.field("lowRating").value());
			lBound.add(searchForm.field("lowCoupon").value());
			lBound.add(searchForm.field("lowCurrentYield").value());
			lBound.add(searchForm.field("lowMaturityYield").value());
			lBound.add(searchForm.field("lowDate").value());
			lBound.add(searchForm.field("lowParValue").value());
			lBound.add(searchForm.field("lowPrice").value());
			List<String> hBound = new ArrayList<String>();
			hBound.add(searchForm.field("highRating").value());
			hBound.add(searchForm.field("highCoupon").value());
			hBound.add(searchForm.field("highCurrentYield").value());
			hBound.add(searchForm.field("highMaturityYield").value());
			hBound.add(searchForm.field("highDate").value());
			hBound.add(searchForm.field("highParValue").value());
			hBound.add(searchForm.field("highPrice").value());
			DataBaseHandler dbh = new DataBaseHandler();
			List<Bond> SERP = dbh.searchBonds(lBound, hBound);
			dbh.closeConnection();

			// return ok("" + SERP.size());
			return ok(filter.render(SERP));
			// return ok("SERP");
		}
	}
	
	public static Result authenticate() {	 // Handle login form submission.
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			return redirect(controllers.routes.Clients.list());
		}
	}
 
}
