/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

/**
 *
 * @author Chuck
 */
public class Ishtie {
    public static final String owlfile = "ncs.owl";
    public static final IRI ncs_iri = IRI.create("http://www.meteck.org/files/ontologies/ncs/ncs.owl");
    public static final PrefixManager prefixManager = new DefaultPrefixManager(null,null,ncs_iri.getNamespace());
    public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
    
    String axiom;// <~ from js file. alternatively OWLClassAxiom axiom;
    OWLClass leftClass;
    OWLClass rightCLass;
    
    /**
     * Constructor for the class. Sets the quantities needed to perform the 
     * verbalization
     * @param axiom_ binary axiom assumed to be of the form relation(class_1, class_2)
     * | not relation(class_1,class_2)
     */
    public Ishtie(String axiom_){
        String relation = axiom_.substring(0,axiom_.lastIndexOf("("));
        axiom = relation;
        String[] arguments = axiom.split("(");
        arguments[1] = arguments[1].substring(0, arguments[1].length());
        leftClass = df.getOWLClass("#"+arguments[0],prefixManager);
        rightCLass = df.getOWLClass("#"+arguments[1],prefixManager);
    }
    
    /**
     * Get the sublclass of the given class.
     * @param 
     */
    public OWLClass getSubclass(/* apparently an axiom */){
        return null;
    }
    
    /**
     * Get the superclass of the given class.
     * @param 
     */
    public OWLClass getSuperclass(/* apparently an axiom */){
        return null;
    }
    
    /**
     * Get the noun class of the given class. Performs a lookup
     * in the noun class table
     * @param clazz the class whos noun class will be returned
     */
    public String getNounClass(OWLClass clazz){
        return null;
    }
    
    /**
     * Get the plural version of the current word. Check which
     * noun class it belongs to and retrieve the prefix
     * @param clazz the current class wich you are checking
     * @param nounClass the current nounclass of the class clazz
     */
    public String getPluralization(OWLClass clazz, OWLClass nounClass){
        return null;
    }
}
