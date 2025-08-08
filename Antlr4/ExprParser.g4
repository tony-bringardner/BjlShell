parser grammar ExprParser;


options {
   tokenVocab = FileSourceShLexer;
}

		
// ANTLR4 : Left recursion!
// Operator precedence matches order of definition
expr   	: 	function_call
		|   array_element
		| 	elment_array=expr LSQUARE elment_index=expr RSQUARE (LSQUARE elment_indexes=expr RSQUARE)*
		|	op=MINUS unaryMinus=expr
		|	op='+' 	 unaryPlus=expr 
		| 	op='!' 	 unaryNot=expr 
		
		| 	left=expr  op=( STAR | SLASH )  right=expr       	
      	| 	left=expr  op=( PLUS | MINUS )  right=expr 
      	| 	left=expr  op=( LT | GT )  right=expr
      	| 	left=expr  op=( PERC | POW )  right=expr
      	| 	left=expr  op=( GT_EQ | LT_EQ | NOT_EQ )  right=expr
      	| 	left=expr  op= EQUALITY   right=expr
      	| 	left=expr  op= (OR | AND)   right=expr
      	| 	ternaryTest=expr  QUESTION ternaryTrue=expr AND ternaryFalse=expr
      	| 	'(' single=expr ')' 
      	| 	ID
      	| 	constant
       	;
       	
array_element:
			ID LSQUARE expr RSQUARE (LSQUARE expr RSQUARE)*
		|	ID LSQUARE expr RSQUARE       	
		;
		
constant :		
		 	NUMBER  	
	  
		;

function_call:
			ID LPAREN arguments  RPAREN 			
			;
			
arguments : expr (COMMA expr);

					