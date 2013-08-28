// @SOURCE:D:/github/dbbonds/conf/routes
// @HASH:36715262aa3a17a455430174a57f121c56dda168
// @DATE:Wed Aug 28 14:34:07 BST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:8
// @LINE:7
package controllers {

// @LINE:13
// @LINE:11
class ReverseClients {
    

// @LINE:13
def one(id:Int): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "client/" + implicitly[PathBindable[Int]].unbind("id", id))
}
                                                

// @LINE:11
def list(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "clients")
}
                                                
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:15
def search(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "search")
}
                                                

// @LINE:16
def filter(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "search")
}
                                                

// @LINE:8
// @LINE:7
def login(): Call = {
   () match {
// @LINE:7
case () if true => Call("GET", _prefix)
                                                        
// @LINE:8
case () if true => Call("GET", _prefix + { _defaultPrefix } + "login")
                                                        
   }
}
                                                
    
}
                          

// @LINE:20
class ReverseAssets {
    

// @LINE:20
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:8
// @LINE:7
package controllers.javascript {

// @LINE:13
// @LINE:11
class ReverseClients {
    

// @LINE:13
def one : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Clients.one",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "client/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:11
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Clients.list",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clients"})
      }
   """
)
                        
    
}
              

// @LINE:16
// @LINE:15
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:15
def search : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.search",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "search"})
      }
   """
)
                        

// @LINE:16
def filter : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.filter",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "search"})
      }
   """
)
                        

// @LINE:8
// @LINE:7
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
      }
   """
)
                        
    
}
              

// @LINE:20
class ReverseAssets {
    

// @LINE:20
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:9
// @LINE:8
// @LINE:7
package controllers.ref {

// @LINE:13
// @LINE:11
class ReverseClients {
    

// @LINE:13
def one(id:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Clients.one(id), HandlerDef(this, "controllers.Clients", "one", Seq(classOf[Int]), "GET", """""", _prefix + """client/$id<[^/]+>""")
)
                      

// @LINE:11
def list(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Clients.list(), HandlerDef(this, "controllers.Clients", "list", Seq(), "GET", """ GET		/bonds						controllers.Bonds.list()""", _prefix + """clients""")
)
                      
    
}
                          

// @LINE:16
// @LINE:15
// @LINE:9
// @LINE:8
// @LINE:7
class ReverseApplication {
    

// @LINE:9
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:15
def search(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.search(), HandlerDef(this, "controllers.Application", "search", Seq(), "GET", """""", _prefix + """search""")
)
                      

// @LINE:16
def filter(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.filter(), HandlerDef(this, "controllers.Application", "filter", Seq(), "POST", """""", _prefix + """search""")
)
                      

// @LINE:7
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """""", _prefix + """""")
)
                      
    
}
                          

// @LINE:20
class ReverseAssets {
    

// @LINE:20
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      