/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import static org.semanticweb.owlapi.search.Searcher.annotations;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLObjectVisitorExAdapter;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;

public class Testie{
    public static final String owlfile = "ncs.owl";
    public static final IRI ncs_iri = IRI.create("http://www.meteck.org/files/ontologies/ncs/ncs.owl");
    
    public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
    
    public OWLClass getSubclass(OWLClass inClass){
        OWLClass subClass = null;
        
        return subClass;
    }
    
    public void walkOnt(OWLOntology ont){
        //create the walker
        OWLOntologyWalker walker = new OWLOntologyWalker(Collections.singleton(ont));
        // tell the walker to walk the path
        OWLOntologyWalkerVisitor visitor = new OWLOntologyWalkerVisitor(walker) {
            @Override
            public void visit(OWLObjectSomeValuesFrom desc){
                System.out.println(desc.toString());
                System.out.println("    " + getCurrentAxiom());
            }
        };
        walker.walkStructure(visitor);
        System.out.println("Done walking");
    }
    
    public void printClasses(OWLOntology ont) throws URISyntaxException{
            for(OWLEntity cls : ont.getEntitiesInSignature(IRI.create(new URI ("http://www.meteck.org/files/ontologies/ncs.owl")))){
       
             System.out.println(cls);
             //System.out.println("   " + cls.getObjectPropertiesInSignature());
             
             System.out.println();             
             /*try{
                Set<OWLNamedIndividual> ind = cls.getIndividualsInSignature();
                System.out.println(" -- numinds -- " + ind.size());//System.out.println(" --   " + ind.iterator().next());
                System.out.println(" -- individual printed");
             }catch(Exception e){System.out.println("No individual");}*/
            } 
    }
    
    public void mytestie(OWLOntology ont){
        PrefixManager pm = new DefaultPrefixManager(null,null,ncs_iri.getNamespace());
        OWLClass class1 = df.getOWLClass("#Class1",pm);
        OWLClass class2 = df.getOWLClass("#Class2",pm);
        OWLObjectPropertyExpression hasPlural = df.getOWLObjectProperty("#hasPlural", pm);
        
        System.out.println("--1 " + pm.getDefaultPrefix());
        System.out.println("--2 " + class1.getObjectPropertiesInSignature().size());
        System.out.println("--3 " + class2.getClassesInSignature());
        System.out.println("--4 " + ont.getClassesInSignature().size());
        System.out.println("--5 " + hasPlural.getSimplified());
    }
    
    public void readAnnotations(OWLOntology ont, OWLDataFactory dataFact){
        for(OWLClass cls : ont.getClassesInSignature()){
            // get hte annotations on the class that use the label property
            //for(OWLAnnotation annotation : cls.getAnnotations(ont, dataFact)){
            for(OWLAnnotationProperty annotation : cls.getAnnotationPropertiesInSignature()){
                //OWLLiteral val = (OWLLiteral) annotation.getValue();
                OWLLiteral val = (OWLLiteral) annotation.getSignature();
                System.out.println(cls + " labelled " + val.getLiteral());
            }
        }
        System.out.println("Annotations Read");
    }
    
    /*private String labelFor(OWLEntity clazz, OWLOntology ont){
    Set<OWLAnnotation> annotations;
    annotations = clazz.getAnnotations();
    }
    
    private static void printHierarchy(OWLReasoner reez, OWLClass clazz, int level, Set<OWLClass> visited) {
    // only print satisfiable classes th skip Nothing
    if(!visited.contains(clazz) && reez.isSatisfiable(clazz)){
    visited.add(clazz);
    }
    for(int i = 0; i < level *4; i++){
    System.out.println(" ");
    }
    System.out.println(labelFor(clazz,reez.getRootOntology()));
    }*/
    
    public static void main(String[] args){
        try {
            Testie test = new Testie();
            //OWLOntologyManager owlman = test.create();
            OWLOntologyManager owlman = OWLManager.createOWLOntologyManager();
            OWLOntology ont = owlman.loadOntologyFromOntologyDocument(IRI.create(Testie.class.getResource(owlfile)));
            OWLDataFactory dataFact = OWLManager.getOWLDataFactory();
            
            /*String base = "http://www.meteck.org/files/ontologies/ncs";
            PrefixManager pm = new DefaultPrefixManager(null,null,base);
            OWLClass class1 = df.getOWLClass("#Class1",pm);
            Set<OWLObjectProperty> settie;
            OWLSubClassOfAxiom subcl = dataFact.getOWLSubClassOfAxiom(class1, class1);
            OWLObjectProperty hasPlural = df.getOWLObjectProperty("#hasPlural", pm);
            
            System.out.println(subcl);
            settie = class1.getObjectPropertiesInSignature();
            Iterator iter = settie.iterator();
            while(iter.hasNext()){
                OWLObjectProperty objProp = (OWLObjectProperty)iter.next();
                System.out.println(objProp);
            }
            System.out.println(class1);
            System.out.println(class1.isOWLClass());*/
            //System.out.println(df.getOWLNamedIndividual(IRI.create(df.getOWLThing().getIRI().toString())) + "mu");
            
            //test.testReadAnnotations(ont);
            //print the classes
            //test.mytestie(ont);
            test.printClasses(ont);
            System.out.println();
            // walk the classes
            //test.walkOnt(ont);
            System.out.println();
            // attempt to extract labels
            //test.readAnnotations(ont, dataFact);
            System.out.println();
            /*OWLClass classie = (OWLClass) ont.getNestedClassExpressions().iterator().next();
            LabelExtractor lab = new LabelExtractor(classie);
            lab.visit(classie);*/
            
            /*OWLClass clazz = dataFact.getOWLThing();
            System.out.println("Class   : " + clazz);
            for(OWLClass classie : ont.getClassesInSignature()){
            //System.out.println((OWLClass)dataFact.getOWLLiteral(classie.isOWLThing()));
            System.out.println(classie.isType(EntityType.CLASS));
            }*/
            //printHierarchy(ont,clazz,new HashSet<OWLClass>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static class LabelExtractor extends OWLObjectVisitorExAdapter implements OWLAnnotationObjectVisitorEx{
        public LabelExtractor(Object o) {
            super(o);
        }
        @Override
        public String visit(OWLAnnotation annotation){
            if(annotation.getProperty().isLabel()){
                OWLLiteral c = (OWLLiteral) annotation.getValue();
                System.out.println(c.getLiteral());
                return c.getLiteral();
            }
            return null;
        }
    }
    
    public void testReadAnnotations(OWLOntology ont) throws OWLException {
        for (OWLClass cls : ont.getClassesInSignature()) {
            // Get the annotations on the class that use the label property
            OWLEntity entity = (OWLEntity) cls;
            for (OWLAnnotation annotation : annotations(
                    ont.getAnnotationAssertionAxioms(cls.getIRI()),
                    df.getRDFSLabel())) {
                System.out.println(annotation);
                if (annotation.getValue() instanceof OWLLiteral) {
                    OWLLiteral val = (OWLLiteral) annotation.getValue();
                    // look for portuguese labels
                    //if (val.hasLang("pt")) {
                        //assertNotNull(val.getLiteral());
                         System.out.println(cls + " labelled " +
                         val.getLiteral());
                    //}
                }
            }
        }
    }
    
}
