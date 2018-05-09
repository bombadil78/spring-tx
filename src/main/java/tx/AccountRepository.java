package tx;

import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<Account, Long> {

  Account save(Account account);

}