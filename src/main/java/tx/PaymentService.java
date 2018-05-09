package tx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

  private static final Logger LOG = LogManager.getLogger(PaymentService.class);

  private final AccountRepository accountRepository;

  public PaymentService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional
  public void executePayment(Payment payment) {
    LOG.info("Executing payment: " + payment);

    Account fromAccount = accountRepository.findById(payment.getFromAccountId());
    Account toAccount = accountRepository.findById(payment.getToAccountId());

    int amount = payment.getAmount();
    fromAccount.setBalance(fromAccount.getBalance() - amount);
    toAccount.setBalance(toAccount.getBalance() + amount);

    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);
  }
}