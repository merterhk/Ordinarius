<%@page import="OBeans.PersonData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="PersonData" class="OBeans.PersonData" scope="session"/>
<%
            int i = Integer.parseInt("" + request.getParameter("load"));
            if (i < PersonData.getMorecoauthors().size()) {
                PersonData pd = PersonData.getMorecoauthors().get(i);
                pd.loadPerson();
                if (pd.loadCategories()) {
                    out.print("{success:true,message:'Category analyze completed.. ',count:" + pd.getCategories().size() + "}");
                    System.gc();
                } else {
                    out.print("{success:false,message:'Category analyze failed.'}");
                }
            } else {
                out.print("{success:false,message:'Category analyze failed.'}");
            }


            /*           if (PersonData.loadCategories()) {
            out.print("{success:true,message:'Category analyze completed..  (<a class=link onclick=\"showCategories();\">"+PersonData.getCategories().size()+" category</a>)'}");
            } else {
            out.print("{success:false,message:'Category analyze failed.'}");
            }
             */
%>