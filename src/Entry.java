import java.util.Map;

/**
 * Created by Brandon on 4/4/2017.
 */
public class Entry {

    public Map<Language, String> languageStringMap;

    public Entry(Map<Language, String> languageStringMap){
      this.languageStringMap = languageStringMap;
    }

    /**
     * Adds a String for a Language to the languageStringMap for the Entry object.
     *
     * @param language: The given language that the String is interpreted in.
     * @param string: The String that is displayed in the given Language.
     */
    public void addString(Language language,String string){
        this.languageStringMap.put(language, string);
    }

    /**
     * Gets the String given a Language to return in.
     *
     * @param language: Specifies the Language that the String should be interpreted in.
     * @return: Returns the String in the specified Language.
     */
    public String getString(Language language){
        if(this.languageStringMap.containsKey(language)){
            return this.languageStringMap.get(language);
        } else {
            System.out.println("Language not found.");
            return "";
        }
    }
}
