import com.javarush.rantsev.controllers.GameServlet;
import com.javarush.rantsev.entity.Answer;
import com.javarush.rantsev.entity.Question;
import com.javarush.rantsev.repository.QuestionRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

public class ServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    HttpSession session = Mockito.mock(HttpSession.class);

    Question question1 = new Question(1, "Ты потерял память.\nПринять вызов НЛО?", "/img/background-index.jpeg", List.of(
            new Answer("Принять вызов"),
            new Answer("Отклонить вызов", "Ты отклонил вызов. Поражение", "/img/background-index.jpg")));

    Question question2 = new Question(2, "Ты принял вызов. Поднимаешься на мостик к капитану?", "/img/background-index.jpeg", List.of(
            new Answer("Подняться на мостик"),
            new Answer("Откзаться подниматься на мостик", "Ты не пошел на переговоры. Поражение.", "/img/background-index.jpg")));

    @Test
    @DisplayName("Проверка 'Плохого ответа' и завершение игры")
    public void doGetGameServletTestWrongAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("answerId")).thenReturn("1");
        Mockito.doNothing().when(session).setAttribute(Mockito.anyString(), Mockito.any());
        Mockito.when(session.getAttribute("question")).thenReturn(question1);
        QuestionRepository questionManager = Mockito.spy(new QuestionRepository());
        Mockito.when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        Mockito.verify(session).setAttribute(Mockito.eq("wrongAnswer"), Mockito.any(Answer.class));
    }

    @Test
    @DisplayName("Проверка 'Хорошего ответа' и продолжение игры")
    public void doGetGameServletTestRightAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("answerId")).thenReturn("0");
        Mockito.doNothing().when(session).setAttribute(Mockito.anyString(), Mockito.any());
        Mockito.when(session.getAttribute("question")).thenReturn(question1);
        QuestionRepository questionManager = Mockito.spy(new QuestionRepository());
        Mockito.when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        Mockito.verify(session).setAttribute(Mockito.eq("question"), Mockito.any(Question.class));
        Mockito.verify(response).sendRedirect("/game.jsp");
    }
}