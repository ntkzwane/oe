/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

/**
 *
 * @author Chuck
 */
public class ZuluVerbOnt {
    public static final String owlfile = "ncs.owl";
    public static final IRI iri_relative = IRI.create(ZuluVerbOnt.class.getResource(owlfile));
    public static final IRI iri_final = IRI.create("http://www.meteck.org/files/ontologies/ncs/ncs.owl");
    public static OWLOntologyManager owlman = OWLManager.createOWLOntologyManager();
    public static OWLDataFactory dataFact = OWLManager.getOWLDataFactory();
    public static final PrefixManager prefixManager = new DefaultPrefixManager(null,null,iri_relative.getNamespace());
    public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
    public static final PrefixManager pm = new DefaultPrefixManager(null,null,iri_final.getNamespace());
    public static OWLOntology ont;
    
    OWLAxiom axiom;
    OWLClass leftClass;
    OWLClass rightClass;
    OWLLiteral leftInstance;
    OWLLiteral rightInstance;
    
    /**
     * Default constructor. Initializes all the values to their respective null
     * cases
     */
    public ZuluVerbOnt(){
        axiom = null;
        leftInstance = null;
        rightInstance = null;
    }
    /**
     * Sets the quantities needed to perform the verbalization
     * @param type the type of axiom: SubClassOf, has existential property etc etc \TODO agree on format for types
     * @param leftC the first class in the axiom following the format Class#.instance
     * @param rightC the second class in the axiom following the format Class#.instance
     */
    public void setUpverbalizer(String type, String leftC, String rightC){
        String[] class_1_parts = leftC.split("\\.");
        String[] class_2_parts = rightC.split("\\.");
        // create the owl literals from the input given
        leftInstance = df.getOWLLiteral(class_1_parts[1]);
        rightInstance = df.getOWLLiteral(class_2_parts[1]);
        // create an owl axiom from the class types of the two literals
        setCurrentAxiom(type, class_1_parts[0], class_2_parts[0]);              System.out.println(" -- Axiom "+axiom);
        try{
            ont = owlman.loadOntologyFromOntologyDocument(iri_relative);
        }catch(Exception e){System.err.println("Could not load ontology file: "+iri_relative);}
    }
    
    /** Generate the verbalisation of the given taxonomic subsumption, using the
     * algorithm developed by C. Maria Keet and Langa Khumalo: Basics for a grammar engine
     * to verbalize logical thories in isiZulu
     * @param 
     * @return 
     */
    public String verbaliseTaxonomicSubs(){
        String verbalisation = "";
        // check if there the axiom represents a negation / disjointness
        if(checkNegation(axiom)){
            // \TODO do negation isht
        }else{
            String l_class = converLiteralToString(leftInstance);
            char augment = getFirstChar(l_class);
            switch(Character.toLowerCase(augment)){
                case 'i':
                    verbalisation = converLiteralToString(leftInstance) + " y" + converLiteralToString(rightInstance);
                    break;
                case 'a':
                    verbalisation = converLiteralToString(leftInstance) + " ng" + converLiteralToString(rightInstance);
                    break;
                case 'o':
                    verbalisation = converLiteralToString(leftInstance) + " ng" + converLiteralToString(rightInstance);
                    break;
                case 'u':
                    verbalisation = converLiteralToString(leftInstance) + " ng" + converLiteralToString(rightInstance);
                    break;
                default:
                    System.err.print("This isiZulu nown is not well-formed.");
            }
        }
        return verbalisation;
    }
    
    /**
     * Construct an owl axiom from the given classes and an axiom type
     * @param type the type of the axiom
     * @param leftC the first class in the axiom
     * @param rightC the second class in the axiom
     * @modifier final so that the method cannot be ovverided (since it is
     * called in the constructor)
     */
    public final void setCurrentAxiom(String type, String leftC, String rightC){
        leftClass = df.getOWLClass("#"+leftC,pm);
        rightClass = df.getOWLClass("#"+rightC, pm);
        
        switch(type.toLowerCase()){ // assumes standard type given
            case "subclassof":
                axiom = df.getOWLSubClassOfAxiom(leftClass, rightClass);
                break;
            case "union":
                break;
            case "existential union":
                break;
            default:
                System.err.println("Unknown axiom type: "+type);
                break;
        }
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
     * @param axiom the axiom given
     * @return \TODO FILL THIS IN
     * \TODO -- EASY
     */
    public boolean checkNegation(OWLAxiom axmiom){
        // there exists a get negation method getNNF();
        return false;
    }
    
    /**
     * Lookup the first character of the given string.
     * @param inClass
     * @return the first character of the string
     * \TODO -- EASY
     */
    public char getFirstChar(String inClass){
        return inClass.charAt(0);
    }
    
    /**
     * Retrieve the string representation of the owl entity
     * @param entity owl entity
     * @return the representation of entity
     */
    public String converClassToSring(OWLClass inClass){
        return inClass.toString().substring(inClass.toString().lastIndexOf("#")+1, inClass.toString().lastIndexOf(">"));
    }
    
    public String converLiteralToString(OWLLiteral literal){
        return literal.getLiteral();
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
