package org.futuresight.bnfparser.parser;

import java.util.Objects;

import org.futuresight.bnfparser.symbols.Symbol;

public final class TerminalSymbolNode extends AbstractNode {
	private final Symbol symbol;
	private final String text;
	
	private TerminalSymbolNode(Symbol symbol, String text) {
		this.symbol = symbol;
		this.text = text;
	}
	
	public static TerminalSymbolNode of(Symbol symbol, String text) {
		Objects.requireNonNull(symbol);
		Objects.requireNonNull(text);
		
		return new TerminalSymbolNode(symbol, text);
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public String getText() {
		return text;
	}
	
	public String toString() {
		return String.format("[<%s>=%s]", symbol.getName(), getText());
	}
}
