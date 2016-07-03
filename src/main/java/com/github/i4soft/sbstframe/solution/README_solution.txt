This README is with regard to the 'solution' package in this directory.  Various
subdirectories contain READMEs of their own for their respective packages.
If you're looking for overall documentation for SBSTFrame, see the README file
for the parent directory of this one (that is, the 'sbstframe' directory).

ABOUT THIS DIRECTORY
--------------------
O pacote "solution" contem as classes Experiment, SearchTechnique, SearchTechniqueParams e o
subpacote searchTechniques com as técnicas de buscas apoiadas pelo framework atualmente.
As classes contidas nesse pacote são responsáveis pela aplicação de alguma técnica de 
otimização sobre o problema de teste de software.

solution.Experiment

A classe Experiment é responsável pela configuração do experimento a ser realizado, ou seja,
pela configuração do ambiente onde será aplicada alguma técnica de busca sobre um conjunto 
de dados correspondente ao resultado da execução dos casos de teste sobre os requisitos de 
teste. Tais dados definem o problema da área de teste de software que se pretente aplicar 
alguma técnica de otimização.