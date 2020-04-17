package bank;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountBean {

    @Inject
    private AccountRepository accountRepository;

    @Transactional
    public void credit(long accountId, long amount) throws IllegalCreditException {
        var account = accountRepository.findBy(accountId);

        Long newbalance = account.getBalance() + amount;
        if (newbalance < 0) {
            throw new IllegalCreditException();
        }

        account.setBalance(newbalance);
        accountRepository.save(account);
    }

    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }
}
