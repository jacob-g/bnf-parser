package org.futuresight.bnfparser.symbols;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.futuresight.bnfparser.Tuple2;
import org.futuresight.bnfparser.parser.Node;
import org.futuresight.bnfparser.parser.NonTerminalSymbolNode;

public final class NonTerminalSymbol extends AbstractSymbol {
	private final String name;
	private final List<List<Symbol>> definitions;
	
	private NonTerminalSymbol(String name, List<List<Symbol>> definitions) {
		this.name = name;
		this.definitions = new ArrayList<>(definitions);
	}
	
	public static NonTerminalSymbol of(String name, List<List<Symbol>> definitions) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(definitions);
		
		return new NonTerminalSymbol(name, definitions);
	}
	
	public void addDefinition(List<Symbol> definition) {
		definitions.add(new ArrayList<>(definition));
	}

	@Override
	public Optional<Tuple2<String, Node>> parseString(String message) {
		//for each node, see if it can be parsed
		for (List<Symbol> definition : definitions) {
			String currentMessage = message;
			List<Node> parseResult = new LinkedList<>();
			boolean failure = false;
			
			//parse each symbol individually and then continue parsing on the remainder after parsing that symbol
			for (Symbol symbol : definition) {
				Optional<Tuple2<String, Node>> symbolParseResult = symbol.parseString(currentMessage);
				
				if (symbolParseResult.isPresent()) {
					//the parse worked, so continue on the remainder after parsing the current symbol and add the node to the list of results
					currentMessage = symbolParseResult.get().getItem1();
					parseResult.add(symbolParseResult.get().getItem2());
				} else {
					failure = true;
					break;
				}
			}
			
			//if the operation never failed, return a Node with the result
			if (!failure) {
				return Optional.of(new Tuple2<>(currentMessage, NonTerminalSymbolNode.of(this, parseResult)));
			}
		}
		
		return Optional.empty();
	}
	
	public String getName() {
		return name;
	}
}
