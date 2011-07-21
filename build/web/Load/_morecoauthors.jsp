<%@page import="OBeans.PersonData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
  
            if (PersonData.loadMoreCoauthors()) {
                out.print("{success:true,message:'More person loaded.. ("+(PersonData.getCountMoreCoauthors()>100?"100+":PersonData.getCountMoreCoauthors())+" person)'}");
            } else {
                out.print("{success:false,message:'More person loading failed. (" + PersonData.getError() + ")'}");
            }

%>