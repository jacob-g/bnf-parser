package org.futuresight.bnfparser.symbols;

import java.util.Optional;
import org.futuresight.bnfparser.*;
import org.futuresight.bnfparser.parser.*;

public interface Symbol {
	/**
	 * Get the name of the symbol
	 * @return The name of the symbol
	 */
	public String getName();
	
	/**
	 * Parse a message
	 * @param message
	 * @return If the message could be parsed, the remaining string and the node representing the parse result. Otherwise, an empty optional.
	 */
	Optional<Tuple2<String, Node>> parseString(String message);
}
