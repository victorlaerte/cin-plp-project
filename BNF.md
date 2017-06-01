# BNF (Backus-Naur form)

```
Programa ::= Expressao | ExpMultiprocess
Expressao ::= Valor
| ExpUnaria
| ExpBinaria
| ExpDeclaracao
| Id
| Aplicacao
| IfThenElse
| ExpMultiprocess
| ExpExecutor

Valor ::= ValorConcreto | ValorAbstrato
ValorAbstrato ::= ValorFuncao

ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString | ValorLista

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao | "not" Expressao | "length" Expressao
                          | head(Expressao) | tail(Expressao)
                          | ExpCompreensaoLista
                          | wait(Expressao | ListExp)
                          | receive(Expressao)

ExpCompreensaoLista ::= Expressao Gerador | Expressao Gerador Filtro

ExpExecutor ::= send(ValorString "," ValorString)

Gerador ::= “for” Id “in” Expressao
                | “for” Id “in” Expressao [“,”] Gerador

Filtro ::= “if” Expressao

ExpBinaria ::= Expressao "+" Expressao
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

ExpMultiprocess ::= DecProcess  |  ListDecProcess

ListDecProcess ::= DecProcess  |  ListDecProcess

DecProcesso ::= "process" Id ExpDeclaracao "end"

DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListId ::= Id  |  Id, ListId

Aplicacao:= Expressao"(" ListExp ")"

ListExp ::= Expressao  |  Expressao, ListExp
```
