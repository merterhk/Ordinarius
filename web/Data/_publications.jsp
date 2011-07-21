<%@page import="OBeans.Publication"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DBLP.DBLPPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
            try {
                int start = Integer.parseInt(request.getParameter("start"));
                int limit = Integer.parseInt(request.getParameter("limit"));

                ArrayList<Publication> pubs = PersonData.getPublications();

                out.print("{count:" + pubs.size() + " ,pubs:[");
                boolean virgul = false;

                for (int i = start; i < start + limit; i++) {

                    if (i < pubs.size()) {
                        Publication s = pubs.get(i);
                        //String pj = d.pubJSON(s);
                        if (s.getTitle() != null) {
                            if (virgul) {
                                out.print(",");
                            }
                            out.println("{title:'" + s.getTitle() + "',year:'" + s.getYear() + "',booktitle:'" + s.getBooktitle() + "',journal:'" + s.getJournal() + "',authors:'" + s.getCoauthorsAsLink() + "'}" + "\n");
                            virgul = true;
                        }

                    }
                }

                out.print("]}");
            } catch (Exception e) {
                out.print("Hata oluştu. " + e.getLocalizedMessage());
            }
%>