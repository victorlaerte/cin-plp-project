# BNF (Backus-Naur form)

```
Programa ::= Expressao
Expressao ::= Valor
| ExpUnaria
| ExpBinaria
| ExpDeclaracao
| Id
| Aplicacao
| IfThenElse
 
Valor ::= ValorConcreto | ValorAbstrato
ValorAbstrato ::= ValorFuncao

ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString | ValorLista

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao | "not" Expressao | "length" Expressao 
                          | head(Expressao) | tail(Expressao) 
                          | ExpCompreensaoLista
                          | wait(ValorInteiro)
                          | receive(ValorString "," ValorInteiro)
                          | send(ValorString "," ValorString)

ExpCompreensaoLista ::= Expressao Gerador | Expressao Gerador Filtro

Gerador ::= “for” Id “in” Expressao
                | “for” Id “in” Expressao [“,”] Gerador

Filtro ::= “if” Expressao

ExpBinaria ::=     Expressao "+" Expressao
| Expressao "-" Expressao
| Expressao "*" Expressao
| Expressao ">" Expressao
| Expressao "<" Expressao
| Expressao "and" Expressao
| Expressao "or" Expressao
| Expressao "==" Expressao
| Expressao "++" Expressao
| Expressao ".." Expressao
| Expressao ":" Expressao
| Expressao "^^" Expressao
 
ExpDeclaracao ::= "let" DeclaracaoFuncional "in" Expressao
DeclaracaoFuncional ::= DecVariavel
| DecFuncao
| DecComposta
 
DecVariavel ::= "var" Id "=" Expressao
DecFuncao ::= "fun" ListId "=" Expressao

DecProcesso ::= "process" ExpDeclaracao "end"

DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListId ::= Id  |  Id, ListId

Aplicacao:= Expressao"(" ListExp ")"

ListExp ::= Expressao  |  Expressao, ListExp
```