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

package it.celi.uima.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.uimafit.examples.type.Token;

import static java.util.regex.Pattern.compile;

public class WhiteSpaceTokenizer extends org.uimafit.component.JCasAnnotator_ImplBase {

	private final Pattern pattern;

	public WhiteSpaceTokenizer() {
		super();
		pattern = compile("[\\S|\\n]+");
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		Matcher matcher = pattern.matcher(jCas.getDocumentText());

		while (matcher.find()) {

			Token token = new Token(jCas, matcher.start(), matcher.end());
			token.addToIndexes();
		}

	}

}
