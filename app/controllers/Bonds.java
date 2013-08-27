package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;
import models.*;
import views.*;
import java.util.List;

public class Bonds extends Controller {
	// 	return ok(bondslist.render(session("connected")));
	public static Result list(int clientId) {
		// DataBaseHandler dbh = new DataBaseHandler();
		// List<Bond> bl = dbh.getBonds(clientId);
		// dbh.closeConnection();
		// return ok(bondslist.render(bl));
		return ok("");
	}
}
