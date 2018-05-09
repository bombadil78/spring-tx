package tx;

public class Payment {

  private final Long fromAccountId;
  private final Long toAccountId;
  private final int amount;

  public Payment(Long fromAccountId, Long toAccountId, int amount) {
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
  }

  public Long getFromAccountId() {
    return fromAccountId;
  }

  public Long getToAccountId() {
    return toAccountId;
  }

  public int getAmount() {
    return amount;
  }
}