// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import org.intellij.sdk.language.parser.SimpleParser;
import org.intellij.sdk.language.psi.*;
import org.jetbrains.annotations.NotNull;

public class SimpleParserDefinition implements ParserDefinition {
  public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
  public static final TokenSet COMMENTS = TokenSet.create(SimpleTypes.COMMENT);
  
  public static final IFileElementType FILE = new IFileElementType(SimpleLanguage.INSTANCE);
  
  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new SimpleLexerAdapter();
  }
  
  @NotNull
  @Override
  public TokenSet getWhitespaceTokens() {
    return WHITE_SPACES;
  }
  
  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }
  
  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }
  
  @NotNull
  @Override
  public PsiParser createParser(final Project project) {
    return new SimpleParser();
  }
  
  @Override
  public IFileElementType getFileNodeType() {
    return FILE;
  }
  
  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new SimpleFile(viewProvider);
  }
  
  @Override
  public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
  
  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return SimpleTypes.Factory.createElement(node);
  }
}