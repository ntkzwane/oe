/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;

/**
 *
 * @author Chuck
 */
public class Drive {
    public static void main(String[] args){
        ZuluVerbOnt driver = new ZuluVerbOnt();
        driver.setUpverbalizer("Conjunction","Class9.inja","Class1.intaba");
        System.out.println(driver.verbalise());
    }
}
