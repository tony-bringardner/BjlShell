parser  grammar ExprParser;


options {
   tokenVocab = ExprLexer;
}

program: statement+;

statement:WS* statement_type WS* statment_terminator;

statement_type: 
		command
		| assingment
		;

statment_terminator: (EOF|NL|SEMI);
		
command: 	command_name (WS* arguments=argument)*;

argument: 	expr;
command_name: expr;

assingment: expr WS* EQ WS* expr;
		
// ANTLR4 : Left recursion!
// Operator precedence matches order of definition
expr   	: 	
			value
		|	op=MINUS unaryMinus=expr
		|	op=PLUS	 unaryPlus=expr 
		| 	op=NOT 	 unaryNot=expr 
		
		| 	left=expr  op=( STAR | SLASH )  right=expr       	
      	| 	left=expr  op=( PLUS | MINUS )  right=expr 
      	| 	left=expr  op=( LT | GT )  right=expr
      	| 	left=expr  op=( PERC | POW )  right=expr
      	| 	left=expr  op=( GT_EQ | LT_EQ | NOT_EQ )  right=expr
      	| 	left=expr  op= EQUALITY   right=expr
      	| 	left=expr  op= (OR | AND)   right=expr
      	| 	ternaryTest=expr  QUESTION ternaryTrue=expr AND ternaryFalse=expr
      	| 	LPAREN single=expr RPAREN 
       	;

	
value:
		 constant 
		|  array_element
		| VARIABLE		 
		;

			
array_element:
			ID LSQUARE expr RSQUARE (LSQUARE expr RSQUARE)*
		|	ID LSQUARE expr RSQUARE       	
		;
		
constant :
		 	NUMBER
		 	| string  	
	  		| boolean
	  		| path
		;

string: DQ_STRING | SQ_STRING | ESC | TEXT;
boolean: TRUE | FALSE;
path: (PATH|ID)+	;

