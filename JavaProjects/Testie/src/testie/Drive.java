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
        //driver.setUpverbalizer("Conjunction","Class9.inja","Class1.intaba");
        System.out.println(driver.setUpverbalizer("SubClassOf","Class9.ingulube","Class7.isilwane"));
        System.out.println(driver.concat(driver.setUpverbalizer("Conjunction", "Class11.ubisi", "Class7.ipapa"),driver.setUpverbalizer("Conjunction", "Class7.inyama", "Class7.isinkwa")));
        //System.out.println(driver.verbalise());
    }
}
