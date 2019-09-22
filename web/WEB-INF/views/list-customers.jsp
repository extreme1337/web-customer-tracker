<%--
  Created by IntelliJ IDEA.
  User: marko
  Date: 9/22/2019
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>List Customers</title>
<%--      reference to style sheet--%>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>

  </head>
  <body>
  <div id="wrapper">
    <div id="header">
      <h2>CRM - Customer Relationship Manager</h2>
    </div>
    <div id="container">
      <div id="content">
<%--          put new button: Add Customer--%>
          <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
                class="add-button"/>
<%--        add our html table here--%>
        <table>
          <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Action</th>
          </tr>
<%--          loop over and print our customers--%>
          <c:forEach var="tempCustomers" items="${customers}">
<%--            construct and "update" link with customer id--%>
              <c:url var="updateLink" value="/customer/showFormForUpdate">
                  <c:param name="customerId" value="${tempCustomers.id}"/>
              </c:url>
<%--              construct and "delete" link with customer id--%>
              <c:url var="deleteLink" value="/customer/delete">
                  <c:param name="customerId" value="${tempCustomers.id}"/>
              </c:url>
              <tr>
                <td>${tempCustomers.firstName}</td>
                <td>${tempCustomers.lastName}</td>
                <td>${tempCustomers.email}</td>
                <td>
<%--                    display the update link--%>
                    <a href="${updateLink}">Update</a>
                    &#124;
        <%--                    display delete link--%>
                    <a href="${deleteLink}"
                    onclick="if (!(confirm('Are you sure you want to delete this '))) return false">Delete</a>
                </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
  
  </body>
</html>
