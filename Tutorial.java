import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.apibinding.OWLManager;
import java.io.File;

public class Tutorial {
    public static final IRI ncs_iri = IRI
        .create("http://www.meteck.org/files/ontologies/ncs.owl");
    OWLDataFactory df = OWLManager.getOWLDataFactory();
    
    public OWLOntologyManager create() {
        OWLOntologyManager m = OWLManager.createOWLOntologyManager();

        m.addIRIMapper(new AutoIRIMapper( new File("materializedOntologies"), true));
        return m;
    }
}
