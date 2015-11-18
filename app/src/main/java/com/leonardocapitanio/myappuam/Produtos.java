package com.leonardocapitanio.myappuam;

/**
 * Created by Leonardo on 15/11/2015.
 */
public class Produtos {
    private int _id;
    private String _nomeproduto;

    public Produtos(){}

    public Produtos(String nomeproduto) {
        this._nomeproduto = nomeproduto;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_nomeproduto(String nomeproduto) {
        this._nomeproduto = nomeproduto;
    }

    public int get_id() {
        return _id;
    }

    public String get_nomeproduto() {
        return _nomeproduto;
    }
}
