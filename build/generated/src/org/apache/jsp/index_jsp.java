package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("         <link rel=\"shortcut icon\" type=\"image/ico\" href=\"favicon.ico\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"ext/resources/css/ext-all.css\" />\n");
      out.write("    <link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\" />\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\"  src=\"ext/adapter/ext/ext-base.js\"> </script>\n");
      out.write("    <script type=\"text/javascript\" src=\"ext/ext-all.js\"> </script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"Load/search.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"Load/fastLoad.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"Load/publications.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"Load/categories.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"Load/conferences.js\"></script>\n");
      out.write("\n");
      out.write("    <title>Ordinarius - Academic Social Network Engine</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div id=\"tlinks\"><a href=\"about.jsp\">About</a></div>\n");
      out.write("<div id=\"logo\">&nbsp;</div>\n");
      out.write("<center>\n");
      out.write("    <div id=\"searchfield\"></div>\n");
      out.write("    <div id=\"content\"></div>\n");
      out.write("    <div id=\"clear\"></div>\n");
      out.write("    <div id=\"clear\"><span style=\"color:#999; font-size: 12px; font-weight: bold;\">2011 &copy; <a href=\"javascript:void(0);\" onclick=\"showTeam();\">Software Engineering Group A</a> @ F.U.C.E. </span></div>\n");
      out.write("</center>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
