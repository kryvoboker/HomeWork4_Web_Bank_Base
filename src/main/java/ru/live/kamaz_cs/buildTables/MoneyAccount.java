package ru.live.kamaz_cs.buildTables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "MoneyAccounts")
@NamedQueries({ //создание часто используемых запросов
        @NamedQuery(name="MoneyAccount.findAll", query = "SELECT c FROM MoneyAccount c"),
        @NamedQuery(name="MoneyAccount.findByName", query = "SELECT c FROM MoneyAccount c WHERE c.nameOfUser = :nameOfUser")})

public class MoneyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nameOfUser = "none";
    private double cashInUSD = 0;
    private double cashInEUR = 0;
    private double cashInUAH = 0;

    @ManyToMany(mappedBy = "moneyAccounts", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<Users> users = new ArrayList<>();

    public MoneyAccount() {
    }

    public MoneyAccount(String nameOfUser, double cashInUSD, double cashInEUR, double cashInUAH) {
        this.nameOfUser = nameOfUser;
        this.cashInUSD = cashInUSD;
        this.cashInEUR = cashInEUR;
        this.cashInUAH = cashInUAH;
    }

    public void addUser(Users users) {
        if ( ! this.users.contains(users))
            this.users.add(users);
        if ( ! users.moneyAccounts.contains(this))
            users.moneyAccounts.add(this);
    }

    public List<Users> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCashInUAH() {
        return cashInUAH;
    }

    public double setCashInUAH(double cashInUAH) {
        this.cashInUAH = cashInUAH;
        return cashInUAH;
    }

    public double getCashInUSD() {
        return cashInUSD;
    }

    public double setCashInUSD(double cashInUSD) {
        this.cashInUSD = cashInUSD;
        return cashInUSD;
    }

    public double getCashInEUR() {
        return cashInEUR;
    }

    public double setCashInEUR(double cashInEUR) {
        this.cashInEUR = cashInEUR;
        return cashInEUR;
    }

    @Override
    public String toString() {
        return "MoneyAccount{" +
                "id=" + id +
                ", nameOfUser='" + nameOfUser + '\'' +
                ", cashInUSD=" + cashInUSD +
                ", cashInEUR=" + cashInEUR +
                ", cashInUAH=" + cashInUAH +
                '}';
    }
}
