package com.javarush.rantsev.controllers;

import com.javarush.rantsev.exception.GameException;
import com.javarush.rantsev.entity.Answer;
import com.javarush.rantsev.entity.Question;
import com.javarush.rantsev.repository.QuestionRepository;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession currentSession = req.getSession();
        QuestionRepository questionManager = new QuestionRepository();
        Question currentQuestion;
        try {
            currentQuestion = getCurrentQuestion(req);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
        Question nextQuestion = null;
        if (currentQuestion == null) {
            nextQuestion = questionManager.getById(1);
        } else {
            Answer currentAnswer;
            try {
                currentAnswer = getCurrentAnswer(currentQuestion, req);
            } catch (GameException e) {
                throw new RuntimeException(e);
            }
            if (currentAnswer != null && !currentAnswer.isWrongAnswer()) {
                nextQuestion = questionManager.getById(currentQuestion.getId() + 1);
            } else {
                currentSession.setAttribute("wrongAnswer", currentAnswer);
            }
        }
        currentSession.setAttribute("question", nextQuestion);

        Answer wrongAnswer = (Answer) currentSession.getAttribute("wrongAnswer");
        if (wrongAnswer != null && wrongAnswer.getBackgroundImage() != null) {
            currentSession.setAttribute("background", wrongAnswer.getBackgroundImage());
        } else {
            currentSession.removeAttribute("background");
        }

        resp.sendRedirect("/game.jsp");
    }

    private Question getCurrentQuestion(HttpServletRequest req) throws GameException {
        try {
            return (Question) req.getSession().getAttribute("question");
        } catch (Exception e) {
            throw new GameException("Error while getting question");
        }
    }

    private Answer getCurrentAnswer(Question currentQuestion, HttpServletRequest req) throws GameException {
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            return currentQuestion.getAnswerList().get(answerId);
        } catch (Exception e) {
            throw new GameException("Error while getting answer");
        }
    }
}
