package controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "testBean")
@ViewScoped
public class TestBean implements Serializable {

    private int num1;
    private int num2;
    private int sum;
    
    public TestBean() {
    }
    
    public void sumar() {
        sum = num1 + num2;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
}
