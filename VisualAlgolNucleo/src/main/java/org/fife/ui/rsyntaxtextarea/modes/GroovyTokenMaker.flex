/*
 * 09/28/2007
 *
 * GroovyTokenMaker.java - Scanner for the Groovy programming language.
 * Copyright (C) 2007 Robert Futrell
 * robert_futrell at users.sourceforge.net
 * http://fifesoft.com/rsyntaxtextarea
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * Scanner for the Groovy programming language.<p>
 *
 * This implementation was created using
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1; however, the generated file
 * was modified for performance.  Memory allocation needs to be almost
 * completely removed to be competitive with the handwritten lexers (subclasses
 * of <code>AbstractTokenMaker</code>, so this class has been modified so that
 * Strings are never allocated (via yytext()), and the scanner never has to
 * worry about refilling its buffer (needlessly copying chars around).
 * We can achieve this because RText always scans exactly 1 line of tokens at a
 * time, and hands the scanner this line as an array of characters (a Segment
 * really).  Since tokens contain pointers to char arrays instead of Strings
 * holding their contents, there is no need for allocating new memory for
 * Strings.<p>
 *
 * The actual algorithm generated for scanning has, of course, not been
 * modified.<p>
 *
 * If you wish to regenerate this file yourself, keep in mind the following:
 * <ul>
 *   <li>The generated GroovyTokenMaker.java</code> file will contain two
 *       definitions of both <code>zzRefill</code> and <code>yyreset</code>.
 *       You should hand-delete the second of each definition (the ones
 *       generated by the lexer), as these generated methods modify the input
 *       buffer, which we'll never have to do.</li>
 *   <li>You should also change the declaration/definition of zzBuffer to NOT
 *       be initialized.  This is a needless memory allocation for us since we
 *       will be pointing the array somewhere else anyway.</li>
 *   <li>You should NOT call <code>yylex()</code> on the generated scanner
 *       directly; rather, you should use <code>getTokenList</code> as you would
 *       with any other <code>TokenMaker</code> instance.</li>
 * </ul>
 *
 * @author Robert Futrell
 * @version 0.5
 *
 */
%%

%public
%class GroovyTokenMaker
%extends AbstractJFlexCTokenMaker
%unicode
%type org.fife.ui.rsyntaxtextarea.Token


%{


	/**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public GroovyTokenMaker() {
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *                    occurs.
	 */
	public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
		super.addToken(array, start,end, tokenType, startOffset);
		zzStartRead = zzMarkedPos;
	}


	/**
	 * Returns the text to place at the beginning and end of a
	 * line to "comment" it in a this programming language.
	 *
	 * @return The start and end strings to add to a line to "comment"
	 *         it out.
	 */
	public String[] getLineCommentStartAndEnd() {
		return new String[] { "//", null };
	}


	/**
	 * Returns the first token in the linked list of tokens generated
	 * from <code>text</code>.  This method must be implemented by
	 * subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text The text from which to get tokens.
	 * @param initialTokenType The token type we should start with.
	 * @param startOffset The offset into the document at which
	 *        <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing
	 *         the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
			case Token.LITERAL_STRING_DOUBLE_QUOTE:
				state = MULTILINE_STRING_DOUBLE;
				start = text.offset;
				break;
			case Token.LITERAL_CHAR:
				state = MULTILINE_STRING_SINGLE;
				start = text.offset;
				break;
			case Token.COMMENT_MULTILINE:
				state = MLC;
				start = text.offset;
				break;
			case Token.COMMENT_DOCUMENTATION:
				state = DOCCOMMENT;
				start = text.offset;
				break;
			default:
				state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new DefaultToken();
		}

	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 * @exception   IOException  if any I/O-Error occurs.
	 */
	private boolean zzRefill() throws java.io.IOException {
		return zzCurrentPos>=s.offset+s.count;
	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream 
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream 
	 */
	public final void yyreset(java.io.Reader reader) throws java.io.IOException {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL  = true;
		zzAtEOF  = false;
	}


%}

Letter							= [A-Za-z]
NonzeroDigit						= [1-9]
Digit							= ("0"|{NonzeroDigit})
HexDigit							= ({Digit}|[A-Fa-f])
OctalDigit						= ([0-7])
AnyCharacterButApostropheOrBackSlash	= ([^\\'])
AnyCharacterButDoubleQuoteOrBackSlash	= ([^\\\"\n])
EscapedSourceCharacter				= ("u"{HexDigit}{HexDigit}{HexDigit}{HexDigit})
Escape							= ("\\"(([btnfr\"'\\])|([0123]{OctalDigit}?{OctalDigit}?)|({OctalDigit}{OctalDigit}?)|{EscapedSourceCharacter}))
NonSeparator						= ([^\t\f\r\n\ \(\)\{\}\[\]\;\,\.\=\>\<\!\~\?\:\+\-\*\/\&\|\^\%\"\']|"#"|"\\")
IdentifierStart					= ({Letter}|"_"|"$")
IdentifierPart						= ({IdentifierStart}|{Digit}|("\\"{EscapedSourceCharacter}))

LineTerminator				= (\n)
WhiteSpace				= ([ \t\f])

CharLiteral				= ([\']({AnyCharacterButApostropheOrBackSlash}|{Escape})*[\'])
UnclosedCharLiteral			= ([\'][^\'\n]*)
ErrorCharLiteral			= ({UnclosedCharLiteral}[\'])
StringLiteral				= ([\"]({AnyCharacterButDoubleQuoteOrBackSlash}|{Escape})*[\"])
UnclosedStringLiteral		= ([\"]([\\].|[^\\\"])*[^\"]?)
ErrorStringLiteral			= ({UnclosedStringLiteral}[\"])

MLCBegin					= "/*"
MLCEnd					= "*/"
DocCommentBegin			= "/**"
LineCommentBegin			= "//"

IntegerHelper1				= (({NonzeroDigit}{Digit}*)|"0")
IntegerHelper2				= ("0"(([xX]{HexDigit}+)|({OctalDigit}*)))
IntegerLiteral				= ({IntegerHelper1}[lL]?)
HexLiteral				= ({IntegerHelper2}[lL]?)
FloatHelper1				= ([fFdD]?)
FloatHelper2				= ([eE][+-]?{Digit}+{FloatHelper1})
FloatLiteral1				= ({Digit}+"."({FloatHelper1}|{FloatHelper2}|{Digit}+({FloatHelper1}|{FloatHelper2})))
FloatLiteral2				= ("."{Digit}+({FloatHelper1}|{FloatHelper2}))
FloatLiteral3				= ({Digit}+{FloatHelper2})
FloatLiteral				= ({FloatLiteral1}|{FloatLiteral2}|{FloatLiteral3}|({Digit}+[fFdD]))
ErrorNumberFormat			= (({IntegerLiteral}|{HexLiteral}|{FloatLiteral}){NonSeparator}+)
BooleanLiteral				= ("true"|"false")

Separator					= ([\(\)\{\}\[\]])
Separator2				= ([\;,.])

NonAssignmentOperator		= ("+"|"-"|"<="|"^"|"++"|"<"|"*"|">="|"%"|"--"|">"|"/"|"!="|"?"|">>"|"!"|"&"|"=="|":"|">>"|"~"|"|"|"&&"|">>>")
AssignmentOperator			= ("="|"-="|"*="|"/="|"|="|"&="|"^="|"+="|"%="|"<<="|">>="|">>>=")
GroovyOperator				= ("=~")
Operator					= ({NonAssignmentOperator}|{AssignmentOperator}|{GroovyOperator})

DocumentationKeyword		= ("author"|"deprecated"|"exception"|"link"|"param"|"return"|"see"|"serial"|"serialData"|"serialField"|"since"|"throws"|"version")

Identifier				= ({IdentifierStart}{IdentifierPart}*)
ErrorIdentifier			= ({NonSeparator}+)

Annotation				= ("@"{Identifier}?)
/*
URLCharacter				= ([A-Za-z_0-9:/\.\?=&\-])
URLCharacters				= ({URLCharacter}+)
URL						= (("http://"|"www."){URLCharacters})
*/

%state MLC
%state DOCCOMMENT
%state MULTILINE_STRING_DOUBLE
%state MULTILINE_STRING_SINGLE

%%

/* Keywords */
<YYINITIAL> "abstract"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "break"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "case"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "catch"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "class"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "continue"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "default"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "do"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "else"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "extends"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "final"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "finally"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "for"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "if"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "it"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "implements"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "import"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "instanceof"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "native"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "new"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "null"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "package"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "private"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "protected"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "public"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "return"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "static"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "strictfp"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "super"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "switch"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "synchronized"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "this"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "throw"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "throws"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "transient"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "try"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "void"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "volatile"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "while"				{ addToken(Token.RESERVED_WORD); }

/* Groovy keywords */
<YYINITIAL> "as"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "assert"			{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "def"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "mixin"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "property"			{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "test"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "using"				{ addToken(Token.RESERVED_WORD); }
<YYINITIAL> "in"				{ addToken(Token.RESERVED_WORD); }

/* Data types. */
<YYINITIAL> "boolean"				{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "byte"					{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "char"					{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "double"				{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "float"					{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "int"					{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "long"					{ addToken(Token.DATA_TYPE); }
<YYINITIAL> "short"					{ addToken(Token.DATA_TYPE); }

/* Booleans. */
<YYINITIAL> {BooleanLiteral}			{ addToken(Token.LITERAL_BOOLEAN); }

/* Standard Java classes (java.lang) */
<YYINITIAL> "Boolean"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Byte"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "Character"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Class"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "ClassLoader"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "Compiler"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Double"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Float"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "InheritableThreadLocal"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "Integer"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Long"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "Math"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "Number"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Object"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Package"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Process"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Runtime"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "RuntimePermission"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "SecurityManager"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "Short"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "StackTraceElement"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "StrictMath"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "String"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "StringBuffer"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "System"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Thread"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "ThreadGroup"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "ThreadLocal"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "Throwable"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Void"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "ArithmeticException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "ArrayIndexOutOfBoundsException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "ArrayStoreException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "ClassCastException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "ClassNotFoundException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "CloneNotSupportedException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "Exception"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalAccessException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalArgumentException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalMonitorStateException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalStateException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalThreadStateException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "IndexOutOfBoundsException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "InstantiationException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "InterruptedException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "NegativeArraySizeException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "NoSuchFieldException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "NoSuchMethodException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "NullPointerException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "NumberFormatException"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "RuntimeException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "SecurityException"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "StringIndexOutOfBoundsException"	{ addToken(Token.FUNCTION); }
<YYINITIAL> "UnsupportedOperationException"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "AbstractMethodError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "AssertionError"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "ClassCircularityError"			{ addToken(Token.FUNCTION); }
<YYINITIAL> "ClassFormatError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "Error"							{ addToken(Token.FUNCTION); }
<YYINITIAL> "ExceptionInInitializerError"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalAccessError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "IllegalAccessError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "IncompatibleClassChangeError"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "InternalError"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "LinkageError"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "NoClassDefFoundError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "NoSuchFieldError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "NoSuchMethodError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "OutOfMemoryError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "StackOverflowError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "ThreadDeath"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "UnknownError"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "UnsatisfiedLinkError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "UnsupportedClassVersionError"		{ addToken(Token.FUNCTION); }
<YYINITIAL> "VerifyError"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "VirtualMachineError"				{ addToken(Token.FUNCTION); }
<YYINITIAL> "CharSequence"					{ addToken(Token.FUNCTION); }
<YYINITIAL> "Cloneable"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Comparable"						{ addToken(Token.FUNCTION); }
<YYINITIAL> "Runnable"						{ addToken(Token.FUNCTION); }

<YYINITIAL> {

	{LineTerminator}				{ addNullToken(); return firstToken; }

	{Identifier}					{ addToken(Token.IDENTIFIER); }

	{WhiteSpace}+					{ addToken(Token.WHITESPACE); }

	/* Multiline string literals. */
	\"\"\"						{ start = zzMarkedPos-3; yybegin(MULTILINE_STRING_DOUBLE); }
	\'\'\'						{ start = zzMarkedPos-3; yybegin(MULTILINE_STRING_SINGLE); }


	/* String/Character literals. */
	{CharLiteral}					{ addToken(Token.LITERAL_CHAR); }
	{UnclosedCharLiteral}			{ addToken(Token.ERROR_CHAR); addNullToken(); return firstToken; }
	{ErrorCharLiteral}				{ addToken(Token.ERROR_CHAR); }
	{StringLiteral}				{ addToken(Token.LITERAL_STRING_DOUBLE_QUOTE); }
	{UnclosedStringLiteral}			{ addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken; }
	{ErrorStringLiteral}			{ addToken(Token.ERROR_STRING_DOUBLE); }

	/* Comment literals. */
	"/**/"						{ addToken(Token.COMMENT_MULTILINE); }
	{MLCBegin}					{ start = zzMarkedPos-2; yybegin(MLC); }
	{DocCommentBegin}				{ start = zzMarkedPos-3; yybegin(DOCCOMMENT); }
	{LineCommentBegin}.*			{ addToken(Token.COMMENT_EOL); addNullToken(); return firstToken; }

	/* Annotations. */
	{Annotation}					{ addToken(Token.VARIABLE); /* FIXME:  Add token type to Token? */ }

	/* Separators. */
	{Separator}					{ addToken(Token.SEPARATOR); }
	{Separator2}					{ addToken(Token.IDENTIFIER); }

	/* Operators. */
	{Operator}					{ addToken(Token.OPERATOR); }

	/* Numbers */
	{IntegerLiteral}				{ addToken(Token.LITERAL_NUMBER_DECIMAL_INT); }
	{HexLiteral}					{ addToken(Token.LITERAL_NUMBER_HEXADECIMAL); }
	{FloatLiteral}					{ addToken(Token.LITERAL_NUMBER_FLOAT); }
	{ErrorNumberFormat}				{ addToken(Token.ERROR_NUMBER_FORMAT); }

	{ErrorIdentifier}				{ addToken(Token.ERROR_IDENTIFIER); }

	/* Ended with a line not in a string or comment. */
	<<EOF>>						{ addNullToken(); return firstToken; }

	/* Catch any other (unhandled) characters and flag them as bad. */
	.							{ addToken(Token.ERROR_IDENTIFIER); }

}


<MLC> {

	[^\n\*]+					{}
/*	[^\h\w\n\*]+				{}
	{URL}					{ int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); addToken(temp,zzMarkedPos-1, Token.HYPERLINK); start = zzMarkedPos; }
	"h"						{}
	"w"						{}
*/
	\n						{ addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken; }
	{MLCEnd}					{ yybegin(YYINITIAL); addToken(start,zzStartRead+1, Token.COMMENT_MULTILINE); }
	\*						{}
	<<EOF>>					{ addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken; }

}


<DOCCOMMENT> {

	[^\@\n\<\*]+				{}
/*	[^\h\w\@\n\<\*]+			{}
	{URL}					{ int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_DOCUMENTATION); addToken(temp,zzMarkedPos-1, Token.HYPERLINK); start = zzMarkedPos; }
	"h"						{}
	"w"						{}
*/
	"@"{DocumentationKeyword}	{ int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_DOCUMENTATION); addToken(temp,zzMarkedPos-1, Token.VARIABLE); start = zzMarkedPos; }
	"@"						{}
	\n						{ addToken(start,zzStartRead-1, Token.COMMENT_DOCUMENTATION); return firstToken; }
	"<"[/]?({Letter}[^\>]*)?">"	{ int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_DOCUMENTATION); addToken(temp,zzMarkedPos-1, Token.PREPROCESSOR); start = zzMarkedPos; }
	\<						{}
	{MLCEnd}					{ yybegin(YYINITIAL); addToken(start,zzStartRead+1, Token.COMMENT_DOCUMENTATION); }
	\*						{}
	<<EOF>>					{ yybegin(YYINITIAL); addToken(start,zzEndRead, Token.COMMENT_DOCUMENTATION); return firstToken; }

}


<MULTILINE_STRING_DOUBLE> {
	[^\"\\\n]*				{}
	\\.?						{ /* Skip escaped chars, handles case: '\"""'. */ }
	\"\"\"					{ yybegin(YYINITIAL); addToken(start,zzStartRead+2, Token.LITERAL_STRING_DOUBLE_QUOTE); }
	\"						{}
	\n						{ addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken; }
	<<EOF>>					{ addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken; }
}


<MULTILINE_STRING_SINGLE> {
	[^\'\\\n]*				{}
	\\.?						{ /* Skip escaped chars, handles case: "\'''". */ }
	\'\'\'					{ yybegin(YYINITIAL); addToken(start,zzStartRead+2, Token.LITERAL_CHAR); }
	\'						{}
	\n						{ addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken; }
	<<EOF>>					{ addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken; }
}
