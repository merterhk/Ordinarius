<%@page import="Tools.WordCount.Word"%>
<%@page import="OBeans.Publication"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DBLP.DBLPPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
            try {
                int start = Integer.parseInt(request.getParameter("start"));
                int limit = Integer.parseInt(request.getParameter("limit"));

                ArrayList<Word> pubs = PersonData.getCategories();

                out.print("{count:" + pubs.size() + " ,cats:[");
                boolean virgul = false;

                for (int i = start; i < start + limit; i++) {

                    if (i < pubs.size()) {
                        String s = pubs.get(i).word;
                        //String pj = d.pubJSON(s);
                        if (s != null) {
                            if (virgul) {
                                out.print(",");
                            }
                            out.println("{title:'" + s + "'}" + "\n");
                            virgul = true;
                        }

                    }
                }

                out.print("]}");
            } catch (Exception e) {
                out.print("Hata oluştu. " + e.getLocalizedMessage());
            }
%>