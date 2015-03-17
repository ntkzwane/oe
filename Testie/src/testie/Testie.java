/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;
import org.semanticweb.owlapi.model.IRI;
import java.io.File;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObject;

public class Testie {
    static File owlFile = new File("C:/Users/Ntokozo/Google Drive/School Isht/CS Honours/OE/MiniProj/ontologies/ncs.owl");
    public static final IRI ncs_iri = IRI.create(owlFile);
    //OWLDataFactory df = OWLManager.getOWLDataFactory();


    public static void main(String[] args){
        OWLClassExpression root;
        try{
            OWLObject l;
            Set<OWLClass> classes;
            classes = ncs_iri.getClassesInSignature();
            System.out.println(classes.toArray()[0]);
            //root = (OWLClassExpression) ncs_iri.getNestedClassExpressions();
            //System.out.println("Casted yo.");
            //System.out.println(root.getClassExpressionType().toString());
            System.out.println(ncs_iri.getScheme());
        }catch(Exception e){System.out.println("Not casted yo");}
    }
    
}
