package com.simpleplugin;

import com.intellij.lexer.Lexer;
import com.intellij.psi.impl.cache.impl.BaseFilterLexer;
import com.intellij.psi.impl.cache.impl.OccurrenceConsumer;
import com.intellij.psi.search.UsageSearchContext;

public class SimpleFilterLexer extends BaseFilterLexer {
    public SimpleFilterLexer(final Lexer originalLexer, final OccurrenceConsumer table) {
        super(originalLexer, table);
    }

    @Override
    public void advance() {
        scanWordsInToken(UsageSearchContext.IN_COMMENTS, false, false);
        advanceTodoItemCountsInToken();
        myDelegate.advance();
    }
}
