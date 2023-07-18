import com.javarush.rantsev.entity.Answer;
import com.javarush.rantsev.entity.Question;
import com.javarush.rantsev.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionsTest {

    QuestionRepository questionManager = new QuestionRepository();

    @Test
    @DisplayName("Проверяем заполнены ли вопросы")
    public void getByIdTest() {
        int questionId = 1;
        Question question = questionManager.getById(questionId);
        Assertions.assertEquals(questionId, question.getId());
    }

    @Test
    @DisplayName("Проверяем все ли вопросы содержат ответы среди которых 1 верный. А так же неверный ответы содержат текст")
    public void allTestHaveAnswersAndOnlyOneIsRight() {
        boolean result = true;
        for (Question question : QuestionRepository.questionList.values()) {
            if (question != null && question.getAnswerList().size() > 0) {
                int rightAnswerCount = 0;
                for(Answer answer : question.getAnswerList()) {
                    if (!answer.isWrongAnswer()) {
                        rightAnswerCount++;
                    } else {
                        if (answer.getWrongAnswerEndText() == null) {
                            result = false;
                            break;
                        }
                    }
                }
                if (rightAnswerCount > 1) {
                    result = false;
                    break;
                }
            } else {
                result = false;
                break;
            }
        }
        Assertions.assertTrue(result);
    }

}
