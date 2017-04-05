import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Brandon on 4/4/2017.
 */
public class DictionaryTest {

  private Dictionary dictionary = new Dictionary(new HashMap<String, Entry>());
  private Entry entry1 = new Entry(new HashMap<Language, String>());
  private Entry entry2 = new Entry(new HashMap<Language, String>());


  @Test
  public void canGetStringTest() {

    CentralController.currSession.currLang = Language.ENGLISH;

    entry1.addString(Language.SPANISH, "Spanish word for n.");
    entry1.addString(Language.ENGLISH, "English word for n.");
    entry2.addString(Language.SPANISH, "Spanish word for x.");
    entry2.addString(Language.ENGLISH, "English word for x.");
    dictionary.addEntry("n",entry1);
    dictionary.addEntry("x",entry2);

    assertEquals("English word for n.",dictionary.getString("n"));
  }




}
