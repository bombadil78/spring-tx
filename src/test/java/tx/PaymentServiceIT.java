package tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@TestPropertySource(locations = "classpath:test.properties")
public class PaymentServiceIT {

  @Autowired
  private PaymentService paymentService;

  @Test
  public void executePayment() {
    Payment p = new Payment(1L, 2L, 100);
    paymentService.executePayment(p);
  }

}