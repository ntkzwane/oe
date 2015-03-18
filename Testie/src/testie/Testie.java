/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;

public class Testie{
    public static final String owlfile = "res.owl";
    public static final IRI ncs_iri = IRI.create("http://www.meteck.org/files/ontologies/ncs.owl");
    
    OWLDataFactory df = OWLManager.getOWLDataFactory();
    
    /*public OWLOntologyManager create(){
        OWLOntologyManager ontman = OWLManager.createOWLOntologyManager();
        ontman.addIRIMapper(new AutoIRIMapper(new File(owlfile),true));
        return ontman;
    }*/
    
    public static void main(String[] args){
        try {
            Testie test = new Testie();
            //OWLOntologyManager owlman = test.create();
            OWLOntologyManager owlman = OWLManager.createOWLOntologyManager();
            OWLOntology owl = owlman.loadOntologyFromOntologyDocument(IRI.create(Testie.class.getResource(owlfile)));
            
            // equivalent to for(OWLClass cls : owl.getClassesInSignature()) <~~ some fancy ass functional isht
            owl.getClassesInSignature().stream().forEach((cls) -> {
                System.out.println(cls);
            });
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(Testie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
