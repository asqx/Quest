<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest Game</title>
    <link href="css/game.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="https://code.jquery.com/jquery-3.6.0.min.js"/>"></script>
</head>
<style>
    body {
        background-image: url('<c:url value="${question.backgroundImage}"/>');
    }

    body.wrong-answer {
        background-image: url('<c:url value="${wrongAnswer.backgroundImage}"/>');
    }

    .image-overlay {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 30%;
        height: 30%;
        display: flex;
        justify-content: center;
        align-items: flex-end;
        z-index: 1;
        background-image: url('<c:url value="/img/magician.webp"/>');
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
    }
    .victory-page {
        background-image: url('<c:url value="/img/final.jpg"/>');
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
    }
</style>

<body class="${wrongAnswer != null ? 'wrong-answer' : ''} ${question == null ? 'victory-page' : ''}">

<h1>${question.getText()}</h1>

<c:if test="${question != null}">
    <table>
        <c:forEach var="i" begin="0" end="${question.getAnswerList().size() - 1}">
            <tr>
                <td>
                    <button onclick="window.location='/game?answerId=${i}'">
                        <c:out value="${question.getAnswerList().get(i).getText()}"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${wrongAnswer != null}">
    <div class="image-overlay"></div>
</c:if>

<c:if test="${question == null}">
    <c:if test="${wrongAnswer != null}">
        <h1>${wrongAnswer.getWrongAnswerEndText()}</h1>
    </c:if>
    <c:if test="${wrongAnswer == null}">
        <h2>Ты вышел на улицу, обернулся, а там оказался обычный цирк. Неужели померещилось?</h2>
    </c:if>
    <button onclick="window.location='/restart'" class="button restart">Начать заново</button>
</c:if>

</body>
</html>