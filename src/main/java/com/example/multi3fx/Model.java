package com.example.multi3fx;

import javafx.beans.property.SimpleIntegerProperty;

public class Model {
    SimpleIntegerProperty a;

    public Model(int a) {
        this.a = new SimpleIntegerProperty(a);
    }

    public void add(int x)
    {
       x += a.get();
       a.set(x);
    }
}
