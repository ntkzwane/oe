/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testie;
import java.net.URISyntaxException;
import java.util.Collections;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import static org.semanticweb.owlapi.search.Searcher.annotations;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.OWLObjectVisitorExAdapter;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;
import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.model.AxiomType;

public class Testie{
    public static final String owlfile = "ncs.owl";
    public static final IRI ncs_iri = IRI.create("http://www.meteck.org/files/ontologies/ncs/ncs.owl");
    
    
    public static final OWLDataFactory df = OWLManager.getOWLDataFactory();
    
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
    
    public void printClasses (IRI iri) {
        OWLOntologyImpl owlont = new OWLOntologyImpl (new OWLOntologyManagerImpl (new OWLDataFactoryImpl (true, true)), new OWLOntologyID (iri) );
        
        for (OWLClass oc : owlont.getClassesInSignature(false))
            System.out.println (oc);
            
    }
    
    public void printClasses(OWLOntologyImpl ont, IRI iri) throws URISyntaxException{
        for(OWLEntity cls : ont.getClassesInSignature()){
            System.out.println("SubClass        :"+cls.isTopEntity());
            System.out.println("Type            :"+cls.getEntityType());
            System.out.println("NestedClassExp  :"+cls.getNestedClassExpressions().iterator().next());
            System.out.println("EntityName      :"+cls);
            System.out.println("ClassName       :"+cls.asOWLClass());
            System.out.println();
        } 
    }
    
    public void printAxioms(OWLOntology ont, IRI iri){
        PrefixManager pm = new DefaultPrefixManager(null,null,iri.getNamespace());
        OWLClass class1 = df.getOWLClass("#Class1",pm);
        for(OWLAxiom axiom : ont.getAxioms()){
            System.out.println("AxType      :" + axiom.getAxiomType());
            System.out.println("AxNNF       :" + axiom.getNNF());
            System.out.println("AxWAnn      :" + axiom.getAnnotations());
            System.out.println("AxWOAnn     :" + axiom.getAxiomWithoutAnnotations());
            System.out.println("AxName      :" + axiom);
            System.err.println("");
        }
    }
    
    public void printAnnotations(OWLOntology ont, IRI iri){
        PrefixManager pm = new DefaultPrefixManager(null,null,iri.getNamespace());
        OWLClass class1 = df.getOWLClass("#Class1",pm);
        for(OWLAnnotation annot : ont.getAnnotations()){
            System.out.println("AnProp      :" + annot.getProperty());
            System.out.println("AnValue     :" + annot.getValue());
            System.out.println("AxWAnn      :" + annot.getAnnotations());
            System.out.println("AxAnnInd    :" + annot.getAnonymousIndividuals());
            System.out.println("AxClass     :" + annot.getClass());
            System.out.println("AxClasInSig :" + annot.getClassesInSignature());
            System.out.println("AxName      :" + annot);
            System.err.println("");
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
    
    private static OWLReasoner createReasoner(final OWLOntology rootOntology) {
        // We need to create an instance of OWLReasoner. An OWLReasoner provides
        // the basic query functionality that we need, for example the ability
        // obtain the subclasses of a class etc. To do this we use a reasoner
        // factory.
        // Create a reasoner factory.
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        return reasonerFactory.createReasoner(rootOntology);
    }    
    
    private void reason(OWLOntology ont){
        OWLReasoner reas = createReasoner(ont);
        reas.getSubClasses(null, true);
    }
    
    public static void main(String[] args){
        try {
            Testie test = new Testie();
            //OWLOntologyManager owlman = test.create();
            OWLOntologyManager owlman = OWLManager.createOWLOntologyManager();
            OWLOntology ont = owlman.loadOntologyFromOntologyDocument(IRI.create(Testie.class.getResource(owlfile)));
            OWLDataFactory dataFact = OWLManager.getOWLDataFactory();
            
            IRI docIri = IRI.create(Testie.class.getResource(owlfile));
            IRI fakeIRI = IRI.create("http://www.meteck.org/files/ontologies/ncs/ncs.owl");
            System.out.println("Ontology Loaded...");
            System.out.println("Document IRI: " + docIri);
            System.out.println("Logical IRI : " + ont.getOntologyID());
            System.out.println("Format      : " + owlman.getOntologyFormat(ont)+"\n");
            
            PrefixManager pm = new DefaultPrefixManager(null,null,fakeIRI.getNamespace());
            OWLClass class1 = df.getOWLClass("#Class1",pm);
            OWLClass nc = df.getOWLClass("#NounClass",pm);
            OWLAxiom ax = df.getOWLSubClassOfAxiom(class1, nc);
            
            //test.printAxioms(ont, docIri);
            System.out.println(ax);
            System.out.println(((OWLEntity)class1));
            /////OWLSubClassOfAxiom subcl = df.getowlsubcla
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
            //test.printAxioms(ont, docIri);
            //test.mytestie(ont);
            //test.printAnnotations(ont,docIri);
            //test.printClasses(docIri);
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
