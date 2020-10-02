package ru.live.kamaz_cs.buildTables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "CurrencyExchanges")

public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private double uahForBuyinUSD = 27.98;
    private double usdForSaleinUAH = 28.21;
    private double uahForBuyEUR = 32.94;
    private double eurForSaleinUAH = 33.44;
    private double eurForBuyinUSD = 0.84;
    private double usdForBuyinEUR = 1.18;

    @ManyToMany(mappedBy = "currencyExchanges", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<Users> users = new ArrayList<>();

    public CurrencyExchange() {
    }

    public CurrencyExchange(double uahForBuyinUSD, double usdForSaleinUAH, double uahForBuyEUR, double eurForSaleinUAH, double eurForBuyinUSD, double usdForBuyinEUR) {
        this.uahForBuyinUSD = uahForBuyinUSD;
        this.usdForSaleinUAH = usdForSaleinUAH;
        this.uahForBuyEUR = uahForBuyEUR;
        this.eurForSaleinUAH = eurForSaleinUAH;
        this.eurForBuyinUSD = eurForBuyinUSD;
        this.usdForBuyinEUR = usdForBuyinEUR;
    }

        public void addUser(Users users) {
        if ( ! this.users.contains(users))
            this.users.add(users);
        if ( ! users.currencyExchanges.contains(this))
            users.currencyExchanges.add(this);
    }


    public List<Users> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public long getId() {
        return id;
    }

    public double getUahForBuyinUSD() {
        return uahForBuyinUSD;
    }

    public double getUsdForSaleinUAH() {
        return usdForSaleinUAH;
    }

    public double getUahForBuyEUR() {
        return uahForBuyEUR;
    }

    public double getEurForSaleinUAH() {
        return eurForSaleinUAH;
    }

    public double getEurForBuyinUSD() {
        return eurForBuyinUSD;
    }

    public void setEurForBuyinUSD(double eurForBuyinUSD) {
        this.eurForBuyinUSD = eurForBuyinUSD;
    }

    public double getUsdForBuyinEUR() {
        return usdForBuyinEUR;
    }

    public void setUsdForBuyinEUR(double usdForBuyinEUR) {
        this.usdForBuyinEUR = usdForBuyinEUR;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "id=" + id +
                ", uahForBuyinUSD=" + uahForBuyinUSD +
                ", usdForSaleinUAH=" + usdForSaleinUAH +
                ", uahForBuyEUR=" + uahForBuyEUR +
                ", eurForSaleinUAH=" + eurForSaleinUAH +
                ", eurForBuyinUSD=" + eurForBuyinUSD +
                ", usdForBuyinEUR=" + usdForBuyinEUR +
                '}';
    }
}
