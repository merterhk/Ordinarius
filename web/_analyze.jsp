<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DBLP.DBLPPerson"%>
<%
            DBLPPerson d = new DBLPPerson();
            String urlpt = request.getParameter("urlpt");
            ArrayList<String> pubs = d.publicationAnalyzeForce(d.publications(urlpt));

            out.print("{analyze:[");
            boolean virgul = false;
            for (String s : pubs) {
                if (virgul) {
                    out.print(",");
                }
                out.println("{a:\"" + s + "\"}" + "\n");
                virgul = true;
            }
            out.print("],count:" + pubs.size() + "}");

%>

