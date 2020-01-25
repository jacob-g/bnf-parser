package org.futuresight.bnfparser;

import java.util.Optional;

import org.futuresight.bnfparser.parser.Node;
import org.futuresight.bnfparser.symbols.Symbol;

public abstract class Parser {
	private Parser() {
		
	}
	
	public static Optional<Node> parse(Symbol startingSymbol, String message) {
		Optional<Tuple2<String, Node>> parseResult = startingSymbol.parseString(message);
		
		return parseResult.filter(result -> result.getItem1().isEmpty()).map((Tuple2<String, Node> result) -> result.getItem2());
	}
}
