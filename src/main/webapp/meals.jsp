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
        <th>ID</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>

    <form class="form1" action="meals" method="post">
        <b>Введите порядковый номер для редактирования блюда:</b><br>
        <input type="text" name="Description" value="">
        <input type="text" name="Date" value="new Date">
        <input type="text" name="Calories" value="200">
        <input type="submit" name = "add_upd" value = "Добавить/Изменить">
    </form>
    <form class="form1" action="remove" method="post">
        <b>Введите порядковый номер для редактирования блюда:</b><br>
        <input type="text" name="ID" value = "new ID">
        <input type="submit" name = "delete" value = "Удалить">
    </form>

    <br>

    <c:forEach var="map" items="${requestScope.mealMap}">
        <c:set var="cleanedDateTime" value="${fn:replace(map.value.getDate(), 'T', ' ')}"/>
        <c:if test="${map.value.isExceed()}">
            <tr class="red_record">
                <td><c:out value="${map.key}"/></td>
                <td><c:out value="${cleanedDateTime}"/></td>
                <td><c:out value="${map.value.getDescription()}"/></td>
                <td><c:out value="${map.value.getCalories()}"/></td>
            </tr>
        </c:if>
        <c:if test="${!map.value.isExceed()}">
            <tr class="green_record">
                <td><c:out value="${map.key}"/></td>
                <td><c:out value="${cleanedDateTime}"/></td>
                <td><c:out value="${map.value.getDescription()}"/></td>
                <td><c:out value="${map.value.getCalories()}"/></td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>