package configuration;

public interface AccessType {
    JdbcAccessConfiguration JDBC = new JdbcAccessConfiguration();
    JpaAccessConfiguration JPA = new JpaAccessConfiguration();
    JooqAccessConfiguration JOOQ = new JooqAccessConfiguration();

}
