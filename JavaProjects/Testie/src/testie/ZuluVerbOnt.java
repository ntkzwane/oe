/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;

import java.util.Arrays;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
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
    String axType;
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
        setCurrentAxiom(type, class_1_parts[0], class_2_parts[1]);
        try{
            ont = owlman.loadOntologyFromOntologyDocument(iri_relative);
        }catch(Exception e){System.err.println("Could not load ontology file: "+iri_relative);}
    }
    
    /**
     * Preform the verbalisation. The type will depend on the type of axiom given
     */
    public String verbalise(){
        switch(axType.toLowerCase()){
            case "subclassof":
                return verbaliseTaxonomicSubs();
            case "conjunction":
                return verbaliseTaxonomicConj();
            case "notsubclassof":
                return verbaliseTaxonomicSubs();
            default:
                System.err.println("Unknown axiom type: "+axType);
                return "nah";
        }
    }
    
    /** 
     * Generate the verbalisation of the given taxonomic subsumption, using
     * 'Algorithm 1' created by C. Maria Keet and Langa Khumalo: Basics for a grammar engine
     * to verbalize logical theories in isiZulu
     * @return the subsumption verbalisation in isiZulu
     */
    public String verbaliseTaxonomicSubs(){
        String verbalisation = "";
        // check if there the axiom represents a negation / disjointness
        if(axType.contains("not")){
            OWLClass lefNClass = df.getOWLClass("#"+getNounClass(convertLiteralToString(leftInstance)),pm);
            System.out.println(" -- lefC -- "+leftClass);
            System.out.println(" -- lefNC -- "+leftClass);
            OWLClass lefPlurNC = getPluralNC(lefNClass);
            String pluralPre = getPluralPrefix(lefPlurNC);
            System.out.println(pluralPre);
            String quatConc = getQuantCord(lefPlurNC)[0];
            System.out.println(quatConc);
            String negSubConc = getNegSubConcord(lefPlurNC);
            System.out.println(negSubConc);
            String proomial = getPronomial(lefPlurNC);
            System.out.println(proomial);
            return quatConc+
                   " " +pluralPre+ convertLiteralToString(leftInstance).substring(1,convertLiteralToString(leftInstance).length())+
                   " " +negSubConc+proomial +
                   " " +convertLiteralToString(rightInstance);
        }else{
            String l_class = convertLiteralToString(leftInstance);
            char augment = getFirstChar(l_class);
            switch(Character.toLowerCase(augment)){
                case 'i':
                    verbalisation = convertLiteralToString(leftInstance) + " y" + convertLiteralToString(rightInstance);
                    break;
                case 'a':
                    verbalisation = convertLiteralToString(leftInstance) + " ng" + convertLiteralToString(rightInstance);
                    break;
                case 'o':
                    verbalisation = convertLiteralToString(leftInstance) + " ng" + convertLiteralToString(rightInstance);
                    break;
                case 'u':
                    verbalisation = convertLiteralToString(leftInstance) + " ng" + convertLiteralToString(rightInstance);
                    break;
                default:
                    System.err.print("This isiZulu nown is not well-formed.");
            }
        }
        return verbalisation;
    }
    
    /** 
     * Generate the verbalisation of conjucntion in an axiom, using 'Algorithm 2'
     * created by C. Maria Keet and Langa Khumalo: Basics for a grammar engine
     * to verbalize logical theories in isiZulu
     * @return the conjuction verbalisation in isiZulu
     */
    public String verbaliseTaxonomicConj(){
        String verbalisation = "";
        String suffix = "";
        if(false){
            // \TODO -- FIX THIS, ASK PAUL WHAT IT MEANS
        }else{
            if(true){// \TODO -- FIX THIS TOO, WHAT MEAN
                String r_class = convertLiteralToString(rightInstance);
                char augment = getFirstChar(r_class);
                switch(Character.toLowerCase(augment)){
                    case 'i':
                        r_class = r_class.substring(1,r_class.length());
                        suffix = "ne"+r_class;
                        break;
                    case 'u':
                        r_class = r_class.substring(1,r_class.length());
                        suffix = "no"+r_class;
                        break;
                    case 'a':
                        r_class = r_class.substring(1,r_class.length());
                        suffix = "na"+r_class;
                        break;
                    default:
                        System.err.println("This isiZulu noun is not well-formed.");
                        break;
                }
            }else{System.err.println("This isiZulu axiom is not well-formed.");}
        }
        verbalisation = convertLiteralToString(leftInstance) + " " + suffix;
        return verbalisation;
    }
    
    public String verbaliseTaxonomicExist(){
        return null;
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
        
        switch(type.toLowerCase().replace(" ", "")){ // assumes standard type given
            case "subclassof":
                axiom = df.getOWLSubClassOfAxiom(leftClass, rightClass);
                axType = "subclassof";
                break;
            case "notsubclassof":
                axType = "notsubclassof";
                break;
            case "conjunction":
                axType = "conjunction";
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
     * @param owlClass the class whos noun class will be returned
     * @return \TODO FILL THIS IN
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public String getNounClass(String string) {
        String augmentedPrefix = string.substring(0,4);
        if (augmentedPrefix.contains("umu") || augmentedPrefix.contains("um")) return "Class1";
        else if (augmentedPrefix.contains("2")) return "aba";
        else if (augmentedPrefix.contains("u")) return "Class1a";
        else if (augmentedPrefix.contains ("o")) return "Class2a";
        else if (augmentedPrefix.contains("imi")) return "Class4";
        else if (augmentedPrefix.contains("ili") || (augmentedPrefix.contains("i"))) return "Class5";
        else if (augmentedPrefix.contains("ama")) return "Class6";
        else if (augmentedPrefix.contains("isi")) return "Class7";
        else if (augmentedPrefix.contains("izi")) return "Class8";
        else if (augmentedPrefix.contains("in")) return "Class9";
        //else if (classID.equals("i")) return "Class9a";
        else if (augmentedPrefix.contains("izin") || (augmentedPrefix.contains("izi"))) return "Class10";
        else if (augmentedPrefix.contains("ulu")) return "Class11";
        else if (augmentedPrefix.contains("ubu")) return "Class14";
        else if (augmentedPrefix.contains("uku")) return "Class15";
        else if (augmentedPrefix.contains("ku")) return "CLass16";
        else return "Class00";
    }
    
    /**
     * Lookup the plural version of the current word. Check which
     * noun class it belongs to and retrieve the prefix
     * @param clazz the current class wich you are checking
     * @param nounClass the current nounclass of the class clazz
     * \TODO -- THIS WILL BE ONE OF THE LAST THINGS WE DO
     */
    public OWLClass getPluralNC(OWLClass nounClass){
        String className = "";
        int i = 0; // this will be used to skip the first iteration (this is the root node)
        for(OWLAxiom curAxiom : ont.getAxioms()){
            OWLObjectProperty compareTo = df.getOWLObjectProperty("#hasPlural",pm);
            // check for only axioms that are of type: SubClassOf
            boolean ax_isSubClass = curAxiom.getAxiomType().equals(AxiomType.SUBCLASS_OF);
            // check that those axioms have at least one object property
            boolean ax_hasObjProp = curAxiom.getObjectPropertiesInSignature().size() > 0;
            if(ax_isSubClass && ax_hasObjProp){
                // check that that object property is named hasPlural
                String[] origAxiom = curAxiom.getObjectPropertiesInSignature().iterator().next().toString().split("#");
                String[] comprTo = compareTo.toString().split("#");
                boolean ax_namedHasPl = false;
                try{
                    ax_namedHasPl = (origAxiom[1]).equals(comprTo[1]);
                }catch(Exception e){}
                if(ax_namedHasPl){
                    // check class containment in the signature
                    className = convertClassToString(nounClass);
                    if(curAxiom.getSignature().toString().contains(className)){
                        // extract the class name that pluralises the input class
                        String objectRange = curAxiom.getSignature().toArray()[2].toString();
                        className = objectRange.substring(
                                objectRange.indexOf("#")+1, // start substring from the index just after the #
                                objectRange.length()-1);      // and end at the end of the class name, before >
                        break;
                    }
                }
            }
            i++;
        }
        return df.getOWLClass("#"+className,pm);
    }
    
    /**
     * Check if the axiom has negation. Just check if the relation
     * contaions a 'not'.
     * @param axiom the axiom given
     * @return \TODO FILL THIS IN
     * \TODO -- EASY
     */
    public boolean checkNegation(OWLAxiom axmiom){
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
     * Retrieve the string representation of the owl class
     * @param inClass the owl class
     * @return the representation of the class
     */
    public String convertClassToString(OWLClass inClass){
        return inClass.toString().substring(inClass.toString().lastIndexOf("#")+1, inClass.toString().lastIndexOf(">"));
    }
    
    /**
     * Retrieve the string representation of the owl literal
     * @param literal the owl literal
     * @return the representation of the literal
     */
    public String convertLiteralToString(OWLLiteral literal){
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


    /**
     * Gets the concatenation of the augment and the prefix
     * @param nounClass     the noun class of the word
     * @result augmentedPrefix the concatenation of the augment and the prefix
     */
    public String getAugmentedPrefix (OWLClass inputClass) {
        String classID = inputClass.toString().split("#")[1].substring(0,inputClass.toString().split("#")[1].length());
        
        // construct the augmented prefix
        if (classID.equals("1") || classID.equals("3")) return "umu";
        else if (classID.equals("2") ) return "aba";
        else if (classID.equals("1a") || classID.equals("3a") ) return "u";
        else if (classID.equals ("2a")) return "o";
        else if (classID.equals("4")) return "imi";
        else if (classID.equals("5")) return "ili";
        else if (classID.equals("6")) return "ama";
        else if (classID.equals("7")) return "isi";
        else if (classID.equals("8")) return "izi";
        else if (classID.equals("9")) return "in";
        else if (classID.equals("9a")) return "i";
        else if (classID.equals("10")) return "izin";
        else if (classID.equals("11")) return "ulu";
        else if (classID.equals("14")) return "ubu";
        else if (classID.equals("15")) return "uku";
        else if (classID.equals("17")) return "ku";
        else return "";
    }
    
    /**
     * Look up the quantitative concord parts for the given noun class
     * @param nounClass the current noun class
     * @return the first element is the oral+onke prefix
     * @return the second element is the nke prefix 
     */
    public String[] getQuantCord(OWLClass nounClass){
        String className = convertClassToString(nounClass);
        System.out.println(" -- in qc -- "+nounClass);
        switch(className){
            case "Class1":
                return new String[]{"wonke","wo"};
            case "Class2":
                return new String[]{"bonke","bo"};
            case "Class1a":
                return new String[]{"wonke","wo"};
            case "Class2a":
                return new String[]{"bonke","bo"};
            case "Class3a":
                return new String[]{"wonke","bqo"};
            case "Class3":
                return new String[]{"wonke","wo"};
            case "Class4":
                return new String[]{"yonke","yo"};
            case "Class5":
                return new String[]{"lonke","lo"};
            case "Class6":
                return new String[]{"onke","o"};
            case "Class7":
                return new String[]{"sonke","so"};
            case "Class8":
                return new String[]{"zonke","zo"};
            case "Class9a":
                return new String[]{"yonke","yo"};
            case "Class9":
                return new String[]{"yonke","yo"};
            case "Class10":
                return new String[]{"zonke","zo"};
            case "Class11":
                return new String[]{"lonke","lo"};
            case "Class14":
                return new String[]{"bonke","bo"};
            case "Class15":
                return new String[]{"bonke","ko"};
            default:
                return new String[]{"",""};
        }
    }
    //\TODO COMMENT
    public String getNegSubConcord(OWLClass nounClass){
        String className = convertClassToString(nounClass);
        switch(className){
            case "Class1":
                return "aka";
            case "Class2":
                return "aba";
            case "Class1a":
                return "aka";
            case "Class2a":
                return "aba";
            case "Class3a":
                return "aka";
            case "Class3":
                return "awu";
            case "Class4":
                return "ayi;";
            case "Class5":
                return "ali";
            case "Class6":
                return "awa";
            case "Class7":
                return "asi";
            case "Class8":
                return "azi";
            case "Class9a":
                return "ayi";
            case "Class9":
                return "ayi";
            case "Class10":
                return "azi";
            case "Class11":
                return "alu";
            case "Class14":
                return "abu";
            case "Class15":
                return "aku";
            default:
                return "";
        }
    }
    //\TODO COMMENT
    public String getPronomial(OWLClass nounClass){
        String className = convertClassToString(nounClass);
        switch(className){
            case "Class1":
                return "yena";
            case "Class2":
                return "bona";
            case "Class1a":
                return "yena";
            case "Class2a":
                return "bona";
            case "Class3a":
                return "wona";
            case "Class3":
                return "wona";
            case "Class4":
                return "yona;";
            case "Class5":
                return "lona";
            case "Class6":
                return "wona";
            case "Class7":
                return "sona";
            case "Class8":
                return "zona";
            case "Class9a":
                return "yona";
            case "Class9":
                return "yona";
            case "Class10":
                return "zona";
            case "Class11":
                return "lona";
            case "Class14":
                return "bona";
            case "Class15":
                return "khona";
            default:
                return "";
        }
    }
    
    //\TODO -- this is a helper method for above method
    public String getPluralPrefix(OWLClass nounClass){
        String className = convertClassToString(nounClass);
        switch(className){
            case "Class2":
                return "aba";
            case "Class4":
                return "imi;";
            case "Class6":
                return "ama";
            case "Class8":
                return "ziz";
            default:
                return "";
        }
    }
}

