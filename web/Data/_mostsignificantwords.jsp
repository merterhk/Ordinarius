<%@page import="Tools.WordCount.Word"%>
<%@page import="OBeans.Publication"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DBLP.DBLPPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
            try {
                ArrayList<Word> words = PersonData.getMostUsedWords();
                int i =20;
                out.print("[");
                boolean virgul = false;
                for (Word w : words) {
                    if (virgul) {
                        out.print(",");
                    }
                    out.println("{word:'" + w.word + "',count:" + w.count + "}" + "\n");

                    i--;
                    if (i < 0) {
                        break;
                    }

                    virgul = true;
                }
                out.print("]");
            } catch (Exception e) {
                out.print("Hata oluştu. " + e.getLocalizedMessage());
            }
%>