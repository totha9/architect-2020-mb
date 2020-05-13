package employees;

import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.sql.DataSource;

@Named
@TransactionManagement(TransactionManagementType.BEAN)
public class DbMigrator {

    @Resource(mappedName = "java:/jdbc/BankDS")
    private DataSource dataSource;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        var flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
}
