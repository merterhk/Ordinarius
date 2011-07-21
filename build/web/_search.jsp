<%@page contentType="text/html" pageEncoding="UTF-8" import="DBLP.DBLPSearch"%>
<%
            DBLPSearch d = new DBLPSearch();
            String callback = request.getParameter("callback");
            int start = Integer.parseInt(request.getParameter("start"));
            int limit = Integer.parseInt(request.getParameter("limit"));

            out.print(callback+"("+d.search(request.getParameter("query"))+")");
%>
