/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniproj.zuluverb;

/**
 *
 * @author Chuck
 */
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.apibinding.OWLManager;
import java.io.File;

public class IndexHandler {
    public static final IRI ncs_iri = IRI.create("http://www.meteck.org/files/ontologies/ncs.owl");
    //OWLDataFactory df = OWLManager.getOWLDataFactory();


    public static void main(String[] args){
    	System.out.println(ncs_iri.getNamespace());
    }    
}
