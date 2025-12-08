lexer grammar ExprLexer;


 
fragment PATH1:([a-zA-Z0-9]|'/'|'.'|'*'|'?'|'~'|'-')|VARIABLE;

PATH: ~[ \t\r\n\\;] PATH1+;

SEMI:';';

NL:  '\n';


LT:'<';
LT_EQ:'<=';
GT:'>';
GT_EQ:'>=';
NOT: '!';
AND: '&&';
OR:  '||';

NUMBER :
     INTEGER
    | DECIMAL
    ;

fragment EXPONENT : ('e'|'E') ('+'|'-') ? INTEGER+;


VARIABLE
    : DOLLAR ID
    | DOLLAR (DOLLAR| STAR|QUESTION|[@#\-!]|DIGIT+)

    ;

DOT:'.';
fragment DOLLAR:'$';
SLASH:'/';


INTEGER
    : [0-9]+ ;

DECIMAL
    : INTEGER DOT INTEGER  EXPONENT?;

DQ_STRING
    : '"' ( ~["\\] | '\\' . )* '"'
    ;

SQ_STRING
    : '\'' ( ~['\\] | '\\' . )* '\''
    ;

ESC: '\\' .;


WS: [ \t\r]+ ;



TRUE: 'true';
FALSE: 'false';
COMMENT: '/*' .*? '*/' -> skip;


LINE_COMMENT: '#'  ~[\r\n]* (EOF | NL) -> skip;

LCURLY:'{';
RCURLY:'}';
QUOTE:'\'';
BACKQUOTE:'`';
CONTINUE:'continue';
BREAK:'break';
FOR:'for';
SELECT: 'select';
IN:'in';
WHILE:'while';
DONE:'done';

UNTIL:'until';
IF:'if';
FI:'fi';
THEN:'then';
ELSE:'else';
ELIF:'elif';
CASE:'case';
ESAC:'esac';

PERC:'%';
PLUS:'+';
STAR:'*';
POW:'**';
DO:'do';
EQ:'=';
EQUALITY:'=='|'-eq';
RX_EQUALITY:'=~';

NOT_EQ:'!='|'-ne';
MINUS:'-';
PIPE:'|';
AMP:'&';
TILDE:'~';
QUESTION:'?';
TIME:'time';
LPAREN:'(';
RPAREN:')';
LSQUARE:'[';
RSQUARE:']';

REDIRECT_APPEND_OUT_2 : '&>>';
REDIRECT_APPEND_OUT : '>>';
REDIRECT_READ_WRITE : '<>';
REDIRECT_BOTH:'>&';
REDIRECT_BOTH_2:'&>';
REDIRECT_INPUT_FROM_FID:'<&';

COMMA:',';
MINUS_ASSIGN:'-=';
STAR_ASSIGN:'*=';
DIV_ASSIGN:':^:=';
MOD_ASSIGN:'%=';
DIGIT: [0-9];
SPECIAL_UNIX: [-_+=~];
SPECIAL_WINDOWS: [-_+=~];
POS:'^';


PERC_PERC:'%%';
PERC_MINUS:'%-';
PERC_PLUS:'%+';
PERC_QUESTION:'%?';

ID      :   [a-zA-Z_]LETTER_OR_DIGIT* ;
LETTER_OR_DIGIT:[a-zA-Z_0-9];
fragment COLON: ':';
P1:COLON MINUS;
AT:'@';
TEXT:~[ \t\r\n];

DOLLAR_LPAREN_LPAREN: '$((';
RPAREN_RPAREN:  '))';
LPAREN_LPAREN: '((';

NOT_CURLY: [ \t]|~[}];
DECLARE_A : 'declare' WS* '-' DECLARE_OP+;
fragment DECLARE_OP:[aAfFgiIlnrtuxp];
DIVIDE: ':^:' ;
RX_CHAR:[!@#$%^&*()_+~];
POSIX_CHAR_CLASS: 
	':' '^'? ('alnum'|'alpha'|'ascii'|'blank'|'cntrl'|'digit'|'graph'|'lower'|'print'|'punct'|'space'|'upper'|'word'|'xdigit') ':'
	;

CHAR_CLASS: [.];

	


