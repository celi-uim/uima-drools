/*
 Copyright 2007-2012 CELI srl
 All rights reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package it.celi.uima.drools;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.uimafit.examples.type.Token;
import org.uimafit.util.JCasUtil;

public class DroolsAnnotator extends org.uimafit.component.JCasAnnotator_ImplBase {

	private static Logger log = Logger.getLogger(DroolsAnnotator.class);
	private KnowledgeBase kbase;

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		kbuilder.add(ResourceFactory.newFileResource("./src/main/resources/it/celi/uima/drools/grammars/DroolsAnnotator.drl"), ResourceType.DRL);

		KnowledgeBuilderErrors errors = kbuilder.getErrors();

		if (!errors.isEmpty()) {
			for (KnowledgeBuilderError knowledgeBuilderError : errors) {
				log.error("error while parsing grammmars:: " + knowledgeBuilderError.getMessage());
			}
			throw new ResourceInitializationException();
		}

		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();
		kSession.setGlobal("log", log);
		kSession.setGlobal("jCas", jcas);

		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		for (Token token : tokens) {
			kSession.insert(token);
		}

		try {
			kSession.fireAllRules();
		} catch (Exception e) {
			log.error("error while processing :: " + jcas.getDocumentText());
		} finally {
			kSession.dispose();
		}

	}
}
