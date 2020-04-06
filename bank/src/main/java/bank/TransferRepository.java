package bank;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface TransferRepository extends EntityRepository<Transfer, Long> {
}
