package com.javarush.rantsev.repository;

import com.javarush.rantsev.entity.Answer;
import com.javarush.rantsev.entity.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {

    public final static Map<Integer, Question> questionList = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        int id = 0;
        questionList.put(++id, new Question(id, "Ты потерял память и проснулся у страшного здания цирка.\nЗайдешь туда?", "/img/background-one-question.jpg", List.of(
                new Answer("Зайти в здание цирка"),
                new Answer("Не заходить в здание цирка", "Тебя поджидал за углом страшный человек в маске клоуна с молотом. У тебя просто не было шанса.", "/img/background-lose-one-question.jpg"))));

        questionList.put(++id, new Question(id, "Ты вошел в цирк и встретил мрачного фокусника, который предложил тебе помощь.", "/img/background-two-question.png", List.of(
                new Answer("Отказаться от помощи"),
                new Answer("Принять помощь", "Фокусник подло обманул тебя. К сожалению, ты не покинешь этот цирк.", "/img/background-lose-two-question.jpg"))));

        questionList.put(++id ,new Question(id, "После всего пережитого ты видишь порванное место в шатре.\nПопытаешься залезть туда?", "/img/background-three-question.jpg", List.of(
                new Answer("Попробовать искать другой выход", "Ты потерял свой последний и единсвтенный шанс сбежать из этого места.", "/img/background-lose-final.jpg"),
                new Answer("Залезь в дыру"))));
    }

    public Question getById(int id) {
        return questionList.get(id);
    }
}
