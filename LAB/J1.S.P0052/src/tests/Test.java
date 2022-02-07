/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

/**
 *
 * @author ADMIN
 */
public class Test implements Update{

    private boolean b;

    public boolean isB() {
        return b;
    }

    @Override
    public void setB(boolean newVal) {
        this.b = newVal;
    }

}
