<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%


            String urlpt = ""+request.getParameter("urlpt");
            PersonData.setUrlpt(urlpt);
            if (PersonData.loadPublications()) {
                out.print("{success:true,message:'Publications loaded.. (<a class=link onclick=\"showPublications();\">"+PersonData.getPublications().size()+" publications</a>)'}");
            } else {
                out.print("{success:false,message:'Publications load failed.'}");
            }

%>