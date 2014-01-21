// This is a generated file. Not intended for manual editing.
package com.simpleplugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static com.simpleplugin.psi.SimpleTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimpleParser implements PsiParser {

  public static final Logger LOG_ = Logger.getInstance("com.simpleplugin.parser.SimpleParser");

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ == PROPERTY) {
      result_ = property(builder_, 0);
    }
    else {
      result_ = parse_root_(root_, builder_, 0);
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return simpleFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // property|COMMENT|CRLF
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = property(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (KEY? SEPARATOR VALUE?) | KEY
  public static boolean property(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "property")) return false;
    if (!nextTokenIs(builder_, "<property>", KEY, SEPARATOR)) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<property>");
    result_ = property_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, KEY);
    exit_section_(builder_, level_, marker_, PROPERTY, result_, false, null);
    return result_;
  }

  // KEY? SEPARATOR VALUE?
  private static boolean property_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "property_0")) return false;
    boolean result_ = false;
    Marker marker_ = enter_section_(builder_);
    result_ = property_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEPARATOR);
    result_ = result_ && property_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // KEY?
  private static boolean property_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "property_0_0")) return false;
    consumeToken(builder_, KEY);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "property_0_2")) return false;
    consumeToken(builder_, VALUE);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean simpleFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile")) return false;
    int pos_ = current_position_(builder_);
    while (true) {
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "simpleFile", pos_)) break;
      pos_ = current_position_(builder_);
    }
    return true;
  }

}
