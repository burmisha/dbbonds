package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;
import models.*;
import views.*;
import java.util.List;

public class Clients extends Controller {
	public static Result list() {
		String s = session("traderId");
		int traderId = Integer.parseInt(s);
		DataBaseHandler dbh = new DataBaseHandler();
		List<Client> cl = dbh.getClients(traderId);
		dbh.closeConnection();
		return ok(clientslist.render(cl));
	}

	public static Result one(int clientId) {
		DataBaseHandler dbh = new DataBaseHandler();
		int portfolioId = dbh.getPortfolioId(clientId);
		Portfolio p = dbh.getPortfolio(portfolioId);
		dbh.closeConnection();
		return ok(portfolio.render(p));
	}
}
