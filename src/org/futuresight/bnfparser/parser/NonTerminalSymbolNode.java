package org.futuresight.bnfparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.futuresight.bnfparser.symbols.NonTerminalSymbol;

public class NonTerminalSymbolNode extends AbstractNode {
	private final NonTerminalSymbol symbol;
	private final List<Node> subNodes;
	
	private NonTerminalSymbolNode(NonTerminalSymbol symbol, List<Node> subNodes) {
		this.symbol = symbol;
		this.subNodes = new ArrayList<>(subNodes);
	}
	
	public static NonTerminalSymbolNode of(NonTerminalSymbol symbol, List<Node> subNodes) {
		Objects.requireNonNull(symbol);
		Objects.requireNonNull(subNodes);
		
		return new NonTerminalSymbolNode(symbol, subNodes);
	}
	
	public String toString() {
		//return a string in the format "[<name>=[symbol1], [symbol2], ...] for each sub node
		return String.format("[<%s>=%s]", symbol.getName(), subNodes.stream().map(node -> node.toString()).collect(Collectors.joining(",")));
	}
}
