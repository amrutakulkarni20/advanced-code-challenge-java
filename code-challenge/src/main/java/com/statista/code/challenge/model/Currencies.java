package com.statista.code.challenge.model;

import java.util.HashSet;
import java.util.Set;

public class Currencies {
    private Set<String> currencySet = new HashSet<>();

    public Currencies(Set<String> currencySet) {
        this.currencySet = currencySet;
    }

    public Currencies() {}

    public Set<String> getCurrencySet() {
        return currencySet;
    }

    public void setCurrencySet(Set<String> currencySet) {
        this.currencySet = currencySet;
    }
}
