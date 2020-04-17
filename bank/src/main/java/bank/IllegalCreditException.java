package bank;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class IllegalCreditException extends RuntimeException {
}
