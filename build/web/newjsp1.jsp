<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
    <title>JSP Page</title>
</head>
<body>
    <%
                try {
                    String il = request.getParameter("il");
                    String durum = request.getParameter("emlakdurumu");
                    String max = request.getParameter("max");
                    String min = request.getParameter("min");
                    String arsatipi = request.getParameter("arsatipi");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emlak1", "root", "root");
                    Statement st = con.createStatement();
                    String sorgu1 = "SELECT * FROM arsa WHERE il='" + il + "'"
                            + "' AND emlakdurumu='" + durum + "' AND arsatipi='" + arsatipi + "' "
                            + "AND fiyat>'" + min + "' AND fiyat<'" + max + "' order by fiyat desc";
                    PreparedStatement ps = con.prepareStatement(sorgu1);
                    ResultSet rs1 = st.executeQuery(sorgu1);
    %>
    <table cellpadding="4" bgcolor="#ffdead" cellspacing="3" >
        <tr>
            <td width="100"><b>ilanno</b></td>
            <td width="100"><b>il</b></td>
            <td width="100"><b>arsatipi</b></td>
            <td width="100"><b>emlakdurumu</b></td>
            <td width="100"><b>metrekare</b></td>
            <td width="100"><b>fiyat</b></td>
            <td width="300"><b>adres</b></td>
            <td width="300"><b>resim</b></td>
        </tr>
        <%
            while (rs1.next()) {
        %>

        <tr>
            <td><%=rs1.getString("ilanno")%></td>
            <td><%=rs1.getString("il")%></td>
            <td><%=rs1.getString("arsatipi")%></td>
            <td><%=rs1.getString("emlakdurumu")%></td>
            <td><%=rs1.getString("metrekare")%></td>
            <td><%=rs1.getString("fiyat")%></td>
            <td><%=rs1.getString("adres")%></td>
            <td><img src="resim/<%=rs1.getString("resim")%>" width="100" height="100"></td>
        </tr>
        <%
                        }
                        rs1.close();
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        %>
    </table>
</body>
</html>