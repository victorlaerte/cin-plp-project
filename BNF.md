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
| ExpProcessoDeclaracao

Valor ::= ValorConcreto | ValorAbstrato
ValorAbstrato ::= ValorFuncao

ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString | ValorLista

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao | "not" Expressao | "length" Expressao 
                          | head(Expressao) | tail(Expressao) 
                          | ExpCompreensaoLista

ExpCompreensaoLista ::= Expressao Gerador | Expressao Gerador Filtro

ExpExecutor ::= wait(Expressao) | send(ValorString "," ValorString)

ExpPromise ::= receive(ValorString "," ValorInteiro)

ExpProcess ::= ExpExecutor | ExpPromise

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

ExpProcessoDeclaracao ::= "let" DecProcess
DecProcesso ::= "process" Id ExpDeclaracao "end"

DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListId ::= Id  |  Id, ListId

Aplicacao:= Expressao"(" ListExp ")"

ListExp ::= Expressao  |  Expressao, ListExp
```
