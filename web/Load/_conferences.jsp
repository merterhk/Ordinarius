<%@page import="wikiCFP.conferencesFeed"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            conferencesFeed cf = new conferencesFeed();
            out.print(cf.conferenceJSON(request.getParameter("conf")));
%>

