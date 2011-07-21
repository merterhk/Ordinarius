<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="DBLP.DBLPPerson"%>
<%

            try {

                String urlpt = request.getParameter("urlpt");
                int start = Integer.parseInt(request.getParameter("start"));
                int limit = Integer.parseInt(request.getParameter("limit"));

                DBLPPerson d = new DBLPPerson();
                ArrayList<String> pubs = d.publications(urlpt);

                out.print("{pubs:[");
                boolean virgul = false;

                for (int i = start; i < start + limit; i++) {

                    if (i < pubs.size()) {



                        String s = pubs.get(i);
                        String pj = d.pubJSON(s);
                        if (pj.length() > 2) {
                            if (virgul) {
                                out.print(",");
                            }
                            out.println(pj + "\n");
                            virgul = true;
                        }

                    }
                }

                out.print("],count:" + pubs.size() + "}");
            } catch (Exception e) {
                out.print("Hata oluştu." + e.getLocalizedMessage());
            }
%>

