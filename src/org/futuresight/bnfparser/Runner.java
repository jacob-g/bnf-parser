package org.futuresight.bnfparser;

import java.util.Arrays;
import org.futuresight.bnfparser.symbols.NonTerminalSymbol;
import org.futuresight.bnfparser.symbols.Symbol;
import org.futuresight.bnfparser.symbols.TerminalSymbol;

public class Runner {

	public static void main(String[] args) {
		Symbol space = TerminalSymbol.of("space", Arrays.asList(" "));
		Symbol separator = TerminalSymbol.of("sep", Arrays.asList("; "));
		
		Symbol letter = TerminalSymbol.of("ltr", Arrays.asList("A", "B", "C"));
		Symbol quote = TerminalSymbol.of("quote", Arrays.asList("\""));
		NonTerminalSymbol stringBody = NonTerminalSymbol.of("str-body", Arrays.asList());
		stringBody.addDefinition(Arrays.asList(letter, stringBody));
		stringBody.addDefinition(Arrays.asList(letter));
		
		Symbol string = NonTerminalSymbol.of("str", Arrays.asList(Arrays.asList(quote, stringBody, quote)));
		
		Symbol printKeyword = TerminalSymbol.of("print", Arrays.asList("print"));
		
		Symbol printStatement = NonTerminalSymbol.of("print-stmt", Arrays.asList(Arrays.asList(printKeyword, space, string)));
		
		Symbol ifSymbol = TerminalSymbol.of("if", Arrays.asList("if"));
		
		Symbol openParen = TerminalSymbol.of("open-paren", Arrays.asList("("));
		Symbol closeParen = TerminalSymbol.of("close-paren", Arrays.asList(")"));
		
		Symbol bool = TerminalSymbol.of("bool", Arrays.asList("true", "false"));
		
		Symbol condition = NonTerminalSymbol.of("condition", Arrays.asList(Arrays.asList(bool)));
		
		Symbol ifStatement = NonTerminalSymbol.of("if-stmt", Arrays.asList(Arrays.asList(ifSymbol, openParen, condition, closeParen)));
		
		NonTerminalSymbol instruction = NonTerminalSymbol.of("instr", Arrays.asList(Arrays.asList(ifStatement), Arrays.asList(printStatement)));
		
		NonTerminalSymbol instructionSet = NonTerminalSymbol.of("inst-set", Arrays.asList());
		instructionSet.addDefinition(Arrays.asList(instruction, separator, instructionSet));
		instructionSet.addDefinition(Arrays.asList(instruction));
		
		System.out.println(Parser.parse(instructionSet, "print \"AB\"; print \"B\"; if(true)")
				.map(result -> result.toString())
				.orElse("Parse failed"));
	}

}
