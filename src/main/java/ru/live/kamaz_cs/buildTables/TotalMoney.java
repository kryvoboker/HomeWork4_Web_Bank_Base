package ru.live.kamaz_cs.buildTables;

import javax.persistence.*;

@Entity
@Table
public class TotalMoney {

    @Id
    private long id;

    private String name;
    private double money;

    public TotalMoney(long id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public TotalMoney() {
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
