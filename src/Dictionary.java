import java.util.HashMap;

/**
 * Created by Brandon on 4/4/2017.
 */
public class Dictionary {

    HashMap<String, Entry> stringEntryMap;

  /**
   * Creates a new Dictionary object.
   *
   * @param stringEntryMap: EntryMap stores a map of possible strings in the application and entry
   * objects that will pair the strings with the given language.
   */
  public Dictionary(HashMap<String, Entry> stringEntryMap) {
    this.stringEntryMap = stringEntryMap;
  }

  /**
   * Adds a new HashMap to the Dictionary object.
   * @param string: Contains the string of the new HashMap.
   * @param entry: Contains the entry of the new HashMap.
   */
  public void addEntry(String string, Entry entry){
    this.stringEntryMap.put(string, entry);
  }

  /**
   * Gets a string from the given key. Returns an empty string if the key does not exist.
   * @param key: The key given to fetch the corresponding String.
   * @return: Returns the String associated with the key.
   */
    public String getString(String key){
      Entry info = stringEntryMap.get(key);
      if (info == null) {
        return "";
      }
      return info.getString(CentralController.currSession.currLang);
    }
}
