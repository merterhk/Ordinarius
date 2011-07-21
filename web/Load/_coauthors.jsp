<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%

            if (PersonData.loadCoauthors()) {
                out.print("{success:true,message:'Coauthors loaded.. ("+PersonData.getCoauthors().size()+" person)'}");
            } else {
                out.print("{success:false,message:'Coauthors loading failed. ("+PersonData.getError()+")'}");
            }

%>