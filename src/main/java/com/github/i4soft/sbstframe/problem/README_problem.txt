This README is with regard to the 'problem' package in this directory.  Various
subdirectories contain READMEs of their own for their respective packages.
If you're looking for overall documentation for SBSTFrame, see the README file
for the parent directory of this one (that is, the 'sbstframe' directory).

ABOUT THIS DIRECTORY
--------------------
O pacote problema contem a interface que define a comunicação do framework com os arquivos de entrada, 
ou seja, define a forma de capturar o problema do usuário. Além disso fornecer uma classe (DefaultReport) 
preparada para trabalhar com os benchmarks fornecidos pelo próprio SBSTFrame e um enum com as referências
para os benchmarks fornecidos pelo SBSTFrame.


problem.ProblemInterface

ProblemInterface define a interface de comunicação do framework com as informações dos benchmarks
contidos no arquivo de entrada. Esta classe também define a estrutura que ir[a manipular os dados 
obtidos por meio do arquivo.

problem.DefaultReport 

DefaultReport é responsável por ler e manipular os dados contidos no arquivo de benchmark. 
Tal arquivo deverá ser composto pelo resultado da execução dos casos de teste sobre os 
requisitos de teste, que nesse caso irá depender do critério de teste. Para o framework é 
indiferente qual critério foi utilizado. Visto que aqui trabalhe-se apenas com requisitos e 
casos de testes, o que torna o framework independente de tipo, nível e/ou critério de teste.

problem.Benchmarks

Benchamarks é um enum que faz referência aos benchmarks disponibilizados pelo framework
SBSTFrame. Tais benchmarks seguem o layout definido pelo framework.