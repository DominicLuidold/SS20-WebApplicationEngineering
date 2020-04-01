<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>TableGenerator</title>
    </head>
    <body>
        <h1>Table Generator</h1>
        <form name="tableGenerator" action="index.jsp">
            Rows: <input type="number" name="rows" min="0" max="16" value="0" required>
            Columns: <input type="number" name="columns" min="0" max="16" value="0" required>
            <button type="submit">Submit</button>
            <button type="reseft">Reset</button>
        </form>
        <hr>
        <%
            // Source: https://www.developer.com/lang/other/article.php/724201/JSP-Creating-Dynamic-Tables.htm
            String[] colorArray = {"00","11","22","33","44","55","66","77","88","99","AA","BB","CC","DD","EE","FF"};

            String rowsInput = request.getParameter("rows");
            String columnsInput = request.getParameter("columns");

            if (rowsInput != null && columnsInput != null) {
                int rows = Integer.parseInt(request.getParameter("rows"));
                int columns = Integer.parseInt(request.getParameter("columns"));

                if (rows > 0 && columns > 0) {
        %>
        <table style="border: 1px solid;">
            <% for (int i = 0; i < rows; i++) { %>
            <tr>
                <% for (int j = 0; j < columns; j++) { %>
                    <!-- Source: https://www.developer.com/lang/other/article.php/724201/JSP-Creating-Dynamic-Tables.htm -->
                    <td style="background-color: #AA<%= colorArray[i] + colorArray[j] %>"><%= i+1 %>-<%= j+1 %></td>
                <% } %>
            </tr>
            <% } %>
        </table>
        <% }} %>
    </body>
</html>
