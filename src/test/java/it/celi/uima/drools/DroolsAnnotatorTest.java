package it.celi.uima.drools;

import it.celi.uima.lang.WhiteSpaceTokenizer;
import it.celi.uima.type.Classification;

import java.io.IOException;
import java.util.Collection;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.junit.Test;
import org.uimafit.factory.AnalysisEngineFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.factory.TypeSystemDescriptionFactory;
import org.uimafit.pipeline.SimplePipeline;
import org.uimafit.util.JCasUtil;
import org.xml.sax.SAXException;

import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class DroolsAnnotatorTest {

	@Test
	public void test() throws UIMAException, SAXException, IOException {

		TypeSystemDescription ts = TypeSystemDescriptionFactory.createTypeSystemDescription();

		AnalysisEngine tokenizer = AnalysisEngineFactory.createPrimitive(WhiteSpaceTokenizer.class, ts);

		AnalysisEngine drools = AnalysisEngineFactory.createPrimitive(DroolsAnnotator.class, ts);

		JCas jCas = JCasFactory.createJCas();

		jCas.setDocumentText("this is soooo good");
		SimplePipeline.runPipeline(jCas, tokenizer, drools);

		Collection<Classification> classifications = JCasUtil.select(jCas, Classification.class);

		assertFalse(classifications.isEmpty());

		Classification classification = classifications.iterator().next();
		assertThat(classification.getLabel(), equalTo("positive"));
	}
}
