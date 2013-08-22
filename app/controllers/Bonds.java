package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;
import models.*;
import views.*;

public class Bonds extends Controller {
  
    public static Result list() {
		return ok(bondslist.render(session("connected")));
    }  
}
