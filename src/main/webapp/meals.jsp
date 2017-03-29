<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<table border="2" width="500">
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>

    <form class = "form1" action="meals" method="post" >
        <b>Введите порядковый номер для редактирования блюда:</b><br>
            <%--<input type="text" name="ID" value = "new ID">--%>
            <input type="text" name="Description" value = "new Description">
            <input type="text" name="Date" value = "new Date">
            <input type="text" name="Calories" value = "200">
            <input type="submit" >
    </form>

    <br>

    <c:forEach var="list" items="${requestScope.mealList}">
        <c:set var="cleanedDateTime" value="${fn:replace(list.getDate(), 'T', ' ')}"/>
        <c:if test="${list.isExceed()}">
            <tr>
                <td class="red_record"><c:out value="${cleanedDateTime}"/></td>
                <td class="red_record"><c:out value="${list.getDescription()}"/></td>
                <td class="red_record"><c:out value="${list.getCalories()}"/></td>
            </tr>
        </c:if>
        <c:if test="${!list.isExceed()}">
                <tr>
                    <td class="blue_record"><c:out value="${cleanedDateTime}"/></td>
                    <td class="blue_record"><c:out value="${list.getDescription()}"/></td>
                    <td class="blue_record"><c:out value="${list.getCalories()}"/></td>
                </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>