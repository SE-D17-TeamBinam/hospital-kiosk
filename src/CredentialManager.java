/**
 * Created by Tom on 4/3/2017.
 */
public class CredentialManager {


  private String adminToken;

  public CredentialManager(String adminToken){

    this.adminToken = adminToken;
  }

  /**
   * Check if the hash is the same as the verified user
   * TODO(tom): this is vulnerable to a timing attack
   * @param hash: the hash the challenger is presenting
   * @return boolean if the challenger is authed
   */
  boolean userIsAdmin(String hash){
    return hash.equals(adminToken);
  }

}
