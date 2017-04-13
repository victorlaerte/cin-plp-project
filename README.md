# cin-plp-project
This repository is the final project of PLP class from <b>CIn - UFPE</b>

## Theme
Functions with parallel execution and communication channel

## Team
* [Alex Juvêncio Costa](https://github.com/AlexJCosta) - [ajc2@cin.ufpe.br](ajc2@cin.ufpe.br)
* [Victor Laerte de Oliveira](https://github.com/victorlaerte) - [vlo2@cin.ufpe.br](vlo2@cin.ufpe.br)

## Proposal
As a proposal for this project we will implement the functionality to execute multiple functions in parallel.

The evaluation process will be parallel through threads and the order of return will depend on the execution time and thread interdependencies.
We intend to implement this in a modular and incremental way making it easy to understand and extend.

The syntax of the functionality [can be found here](/SINTAX.md)

The BNF model [can be found here](/BNF.md)

A sample code with concurrency and messaging that will guide real implementation [can be found here](https://github.com/victorlaerte/ichat-api)

## Justification
Parallel processing increases processing power by enabling active processors to work in parallel on the same problem in order to reduce the overall execution time, as well as to make better use of available resources by running a multi-process application, and also increase performance considerable. [1]

## References
[1] Navaux, Philippe OA, César AF De Rose, and Laércio L. Pilla. "Fundamentos das Arquiteturas para Processamento Paralelo e Distribuído." XI Escola Regional de Alto Desempenho do Estado do Rio Grande do Sul-2011-Porto Alegre, RS (2011): 22-59.
