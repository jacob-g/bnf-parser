package org.futuresight.bnfparser.symbols;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.futuresight.bnfparser.Tuple2;
import org.futuresight.bnfparser.parser.Node;
import org.futuresight.bnfparser.parser.TerminalSymbolNode;

public final class TerminalSymbol extends AbstractSymbol {
	private final String name;
	private final List<String> definitions;
	
	private TerminalSymbol(String name, List<String> definitions) {
		this.name = name;
		this.definitions = new ArrayList<>(definitions);
	}
	
	public static TerminalSymbol of(String name, List<String> definitions) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(definitions);
		
		return new TerminalSymbol(name, definitions);
	}

	@Override
	public Optional<Tuple2<String, Node>> parseString(String message) {
		//if any of the definitions match, take the first one and return a node with that
		Optional<String> matchingDefinitions = definitions.stream().filter(def -> message.startsWith(def)).findFirst();
		
		return matchingDefinitions
				.map(match -> new Tuple2<>(message.substring(match.length()), TerminalSymbolNode.of(this, match)));
	}
	
	public String getName() {
		return name;
	}
}
