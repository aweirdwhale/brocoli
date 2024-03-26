"""
Lexer ==> Lexical analysis :
	tokenizing the code :
		e.g : 'int x = 2 + 5' become:
			'[TYPE_INT, ID(x), EQUALS, INT(2), PLUS, INT(5)]'
	we do it line by line, without spaces and without '-----'
"""

