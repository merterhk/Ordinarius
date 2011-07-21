<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
            if (PersonData.loadCategories()) {
                out.print("{success:true,message:'Category analyze completed..  (<a class=link onclick=\"showCategories();\">"+PersonData.getCategories().size()+" category</a>)'}");
            } else {
                out.print("{success:false,message:'Category analyze failed.'}");
            }

%>