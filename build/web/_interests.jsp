<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DBLP.DBLPPerson"%>
<%
            DBLPPerson d = new DBLPPerson();
            String urlpt = request.getParameter("urlpt");
            for (String string : d.publicationAnalyzeForce(d.publications(urlpt))) {
                out.println(string);
            }

%>

