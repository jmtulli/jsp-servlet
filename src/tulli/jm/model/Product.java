package tulli.jm.model;

public class Product {

  private Integer id;
  private String name;
  private Integer amount;
  private Double price;
  private Integer categoryId;

  public Product(Integer id, String name, Integer amount, Double price, Integer categoryId) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.price = price;
    this.categoryId = categoryId;
  }

  public Product() {}

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAmount() {
    return amount;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getCategoryId() {
    return categoryId;
  }
}
