package tx;

import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<Account, Long> {

  Account findById(Long id);

  Account save(Account account);

}