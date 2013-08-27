// @SOURCE:D:/github/dbbonds/conf/routes
// @HASH:9594a0055f473497bdc05f5ef9d134fd2f750ef4
// @DATE:Fri Aug 23 17:15:49 BST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:9
package controllers {

// @LINE:15
// @LINE:13
class ReverseClients {
    

// @LINE:15
def one(id:Int): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "client/" + implicitly[PathBindable[Int]].unbind("id", id))
}
                                                

// @LINE:13
def list(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "clients")
}
                                                
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseApplication {
    

// @LINE:11
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:10
// @LINE:9
def login(): Call = {
   () match {
// @LINE:9
case () if true => Call("GET", _prefix)
                                                        
// @LINE:10
case () if true => Call("GET", _prefix + { _defaultPrefix } + "login")
                                                        
   }
}
                                                
    
}
                          
}
                  


// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:9
package controllers.javascript {

// @LINE:15
// @LINE:13
class ReverseClients {
    

// @LINE:15
def one : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Clients.one",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "client/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:13
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Clients.list",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clients"})
      }
   """
)
                        
    
}
              

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseApplication {
    

// @LINE:11
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:10
// @LINE:9
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
              
}
        


// @LINE:15
// @LINE:13
// @LINE:11
// @LINE:10
// @LINE:9
package controllers.ref {

// @LINE:15
// @LINE:13
class ReverseClients {
    

// @LINE:15
def one(id:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Clients.one(id), HandlerDef(this, "controllers.Clients", "one", Seq(classOf[Int]), "GET", """""", _prefix + """client/$id<[^/]+>""")
)
                      

// @LINE:13
def list(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Clients.list(), HandlerDef(this, "controllers.Clients", "list", Seq(), "GET", """ GET		/bonds						controllers.Bonds.list()""", _prefix + """clients""")
)
                      
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseApplication {
    

// @LINE:11
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:9
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """ Authentication""", _prefix + """""")
)
                      
    
}
                          
}
                  
      