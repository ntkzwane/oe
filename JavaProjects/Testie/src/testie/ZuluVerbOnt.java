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
public class ZuluVerbOnt {
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
     * \TODO
     */
    public ZuluVerbOnt(String axiom_){
        String relation = axiom_.substring(0,axiom_.lastIndexOf("("));
        axiom = relation;
        String[] arguments = axiom.split("(");
        arguments[1] = arguments[1].substring(0, arguments[1].length());
        leftClass = df.getOWLClass("#"+arguments[0],prefixManager);
        rightCLass = df.getOWLClass("#"+arguments[1],prefixManager);
    }
    
    /** Generate the verbalisation of the given taxonomic subsumption, using the
     * algorithm developed by C. Maria Keet and Langa Khumalo: Basics for a grammar engine
     * to verbalize logical thories in isiZulu
     * @param 
     * @return 
     */
    public String verbaliseTaxonomicSubs(){
        String verbalisation;
        //OWLClass nounClass = getNounClass(leftClass);
        
        return null;
    }
    
    /**
     * Get the sublclass of the given class.
     * @return \TODO FILL THIS IN
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public OWLClass getSubclass(/* apparently an axiom */){
        return null;
    }
    
    /**
     * Get the superclass of the given class.
     * @return \TODO FILL THIS IN
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public OWLClass getSuperclass(/* apparently an axiom */){
        return null;
    }
    
    /**
     * Lookup the noun class of the given class. Performs a lookup
     * in the noun class table
     * @param clazz the class whos noun class will be returned
     * @return \TODO FILL THIS IN
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public String getNounClass(OWLClass clazz){
        return null;
    }
    
    /**
     * Lookup the plural version of the current word. Check which
     * noun class it belongs to and retrieve the prefix
     * @param clazz the current class wich you are checking
     * @param nounClass the current nounclass of the class clazz
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public String getPluralization(OWLClass clazz, OWLClass nounClass){
        return null;
    }
    
    /**
     * Check if the axiom has negation. Just check if the relation
     * contaions a 'not'.
     * @param axmiomHead the axiom head (relation) to be checked to be checked
     * @return \TODO FILL THIS IN
     * \TODO -- EASY
     */
    public boolean checkNegation(String axmiomHead){
        return false;
    }
    
    /**
     * Lookup the first character of the first word.
     * @param word
     * @return \TODO FILL THIS IN
     * \TODO -- EASY
     */
    public char getFirstChar(String word){
        return ' ';
    }
    
    /**
     * Lookup the negative subject concord for the given noun class.
     * @param nounClass the noun class of the current word
     * @return \TODO FILL THIS IN
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public String getNSC(OWLClass nounClass){
        return null;
    }
    
    /**
     * Lookup the pronomial for the given noun class.
     * 
     */
}
