package tulli.jm.model;

public class User {

  private Integer id;
  private String login;
  private String password;
  private String cep;
  private String street;
  private String city;
  private String filename;

  public User() {}

  public User(Integer id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

  public User(Integer id, String login, String password, String cep, String street, String city) {
    this(id, login, password);
    this.cep = cep;
    this.street = street;
    this.city = city;
  }

  public User(Integer id, String login, String password, String cep, String street, String city, String filename) {
    this(id, login, password, cep, street, city);
    this.filename = filename;
  }

  public boolean isValidUser(String login, String password) {
    return (this.login != null && this.password != null && this.login.equals(login) && this.password.equals(password));
  }

  public Integer getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public String getCep() {
    return cep;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getFilename() {
    return filename;
  }
}
