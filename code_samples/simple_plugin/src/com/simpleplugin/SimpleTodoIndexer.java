package com.simpleplugin;

import com.intellij.lexer.Lexer;
import com.intellij.psi.impl.cache.impl.OccurrenceConsumer;
import com.intellij.psi.impl.cache.impl.todo.LexerBasedTodoIndexer;

public class SimpleTodoIndexer extends LexerBasedTodoIndexer {
    @Override
    public Lexer createLexer(OccurrenceConsumer consumer) {
        return SimpleIdIndexer.createIndexingLexer(consumer);
    }
}
