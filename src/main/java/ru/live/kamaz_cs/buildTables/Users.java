package ru.live.kamaz_cs.buildTables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Users")
@NamedQueries({ //создание часто используемых запросов
        @NamedQuery(name="User.findAll", query = "SELECT c FROM Users c"),
        @NamedQuery(name="User.findByName", query = "SELECT c FROM Users c WHERE c.name = :name")})

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "UserTransaction",
            joinColumns = {@JoinColumn(name = "us_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "trans_id", referencedColumnName = "id")})
    List<Transaction> transactions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "UserMoneyAccount",
            joinColumns = {@JoinColumn(name = "us_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "monnAcc_id", referencedColumnName = "id")})
    List<MoneyAccount> moneyAccounts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "UserCurrencyExchange",
            joinColumns = {@JoinColumn(name = "us_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "currEx_id", referencedColumnName = "id")})
    List<CurrencyExchange> currencyExchanges = new ArrayList<>();

    public Users() {
    }

    public Users(String name) {
        this.name = name;
    }

    public void addTransaction(Transaction transaction) {
        if ( ! transactions.contains(transaction))
            transactions.add(transaction);
        if ( ! transaction.users.contains(this))
            transaction.users.add(this);
    }

    public void addMoneyAccount(MoneyAccount moneyAccount) {
        if ( ! moneyAccounts.contains(moneyAccount))
            moneyAccounts.add(moneyAccount);
        if ( ! moneyAccount.users.contains(this))
            moneyAccount.users.add(this);
    }

    public void addCurrencyExchange(CurrencyExchange currencyExchange) {
        if ( ! currencyExchanges.contains(currencyExchange))
            currencyExchanges.add(currencyExchange);
        if ( ! currencyExchange.users.contains(this))
            currencyExchange.users.add(this);
    }

    public List<CurrencyExchange> getCurrencyExchanges() {
        return Collections.unmodifiableList(currencyExchanges);
    }

    public List<MoneyAccount> getMoneyAccounts() {
        return Collections.unmodifiableList(moneyAccounts);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
