package bootstrap.liftweb

import net.liftweb._
import common._
import http._
import sitemap._
import de.insurrance.rest.ApiResourceHelper


class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("de.insurrance")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index")

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries: _*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQueryArtifacts

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
    
// 
//    
//    LiftRules.dispatch.append(BugHelper) // stateful -- associated with a servlet container session
    LiftRules.statelessDispatchTable.append(ApiResourceHelper) // stateless -- no session created
  }
}
