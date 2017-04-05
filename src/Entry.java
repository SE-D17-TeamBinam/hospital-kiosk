import java.util.Map;

/**
 * Created by Brandon on 4/4/2017.
 */
public class Entry {

    public Map<Language, String> languageStringMap;

    public Entry(Map<Language, String> languageStringMap){
      this.languageStringMap = languageStringMap;
    }

    public void addString(Language language,String string){
      this.languageStringMap.put(language, string);
    }

    public String getString(Language language){
        if(this.languageStringMap.containsKey(language)){
            return this.languageStringMap.get(language);
        } else {
            System.out.println("Language not found.");
            return "";
        }
    }
}
