package com.simpleplugin;

import com.intellij.lexer.Lexer;
import com.intellij.psi.impl.cache.impl.OccurrenceConsumer;
import com.intellij.psi.impl.cache.impl.id.LexerBasedIdIndexer;

public class SimpleIdIndexer extends LexerBasedIdIndexer {

    public static Lexer createIndexingLexer(OccurrenceConsumer consumer) {
        return new SimpleFilterLexer(new SimpleLexerAdapter(), consumer);
    }

    @Override
    public Lexer createLexer(final OccurrenceConsumer consumer) {
        return createIndexingLexer(consumer);
    }
}
