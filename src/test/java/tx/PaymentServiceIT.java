package tx;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class PaymentServiceIT {

  private static final Logger LOG = LogManager.getLogger(PaymentServiceIT.class);

  @Autowired
  private PaymentService paymentService;

  @Autowired
  private AccountRepository accountRepository;

  @Spy
  private PlatformTransactionManager platformTransactionManager;

  @Test
  public void executePayment_WhenPaulsAccountExeeds_ShouldRollback() {
    Account petersAccount = new Account("Peter", 50);
    Account paulsAccount = new Account("Paul", 100);
    accountRepository.save(petersAccount);
    accountRepository.save(paulsAccount);

    try {
      Payment p = new Payment(petersAccount.getId(), paulsAccount.getId(), 1);
      paymentService.executePayment(p);
      assertTrue(false);
    } catch (Exception ex) {
      assertTrue(ex instanceof TransactionException);
    }

    int petersAmount = accountRepository.findById(petersAccount.getId()).getBalance();
    int paulsAmount = accountRepository.findById(paulsAccount.getId()).getBalance();
    assertThat(petersAmount).isEqualTo(50);
    assertThat(paulsAmount).isEqualTo(100);
  }
}