package tx;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Account {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String owner;

  @Min(0)
  @Max(100)
  private int balance;

  public Account() {

  }

  public Account(String owner, int balance) {
    this.owner = owner;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return String.format("id=%d owner=%s balance=%d", id, owner, balance);
  }
}