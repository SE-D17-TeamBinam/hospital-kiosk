import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Tom on 4/3/2017.
 */
public class CredManagerTest {


  //Setup
  private CredentialManager manager = new CredentialManager("aBcDef");

  @Test
  public void isAdmin_normalCase(){
    assertEquals(true, manager.userIsAdmin("aBcDef"));
  }

  @Test
  public void isAdmin_tooShort(){
    assertEquals(false, manager.userIsAdmin("aa"));
  }

  @Test
  public void isAdmin_wrongCase(){
    assertEquals(false, manager.userIsAdmin("AbCdEF"));
  }

  @Test
  public void isAdmin_tooLong(){
    assertEquals(false, manager.userIsAdmin("aaaaaaaaaaaaaaaaaaaaaaaa"));
  }

}
