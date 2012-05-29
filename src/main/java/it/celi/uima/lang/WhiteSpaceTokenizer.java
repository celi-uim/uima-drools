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
