package tx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  private static final Logger LOG = LogManager.getLogger(PaymentService.class);

  private final AccountRepository accountRepository;

  public PaymentService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void executePayment(Payment payment) {
    Account fromAccount = accountRepository.findById(payment.getFromAccountId());
    LOG.info(String.format("From Account: %s", fromAccount));
  }
}