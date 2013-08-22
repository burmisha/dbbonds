// @SOURCE:D:/github/dbbonds/conf/routes
// @HASH:5c761875477986ff04bc34a41f132f1b7d24983c
// @DATE:Wed Aug 21 17:26:21 BST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
package controllers {

// @LINE:12
class ReverseBonds {
    

// @LINE:12
def list(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "bonds")
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
                  


// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
package controllers.javascript {

// @LINE:12
class ReverseBonds {
    

// @LINE:12
def list : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Bonds.list",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "bonds"})
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
        


// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
package controllers.ref {

// @LINE:12
class ReverseBonds {
    

// @LINE:12
def list(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Bonds.list(), HandlerDef(this, "controllers.Bonds", "list", Seq(), "GET", """""", _prefix + """bonds""")
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
                  
      