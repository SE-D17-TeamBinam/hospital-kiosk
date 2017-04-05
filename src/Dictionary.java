import java.util.HashMap;

/**
 * Created by Brandon on 4/4/2017.
 */
public class Dictionary {

    HashMap<String, Entry> stringEntryMap;

    public Dictionary(HashMap<String, Entry> stringEntryMap){
      this.stringEntryMap = stringEntryMap;
    }

    public void addEntry(String string, Entry entry){
      this.stringEntryMap.put(string, entry);
    }


    public String getString(String key){
      Entry info = stringEntryMap.get(key);
      return info.getString(CentralController.currSession.currLang);
    }
}
