Atividade Acadêmica da disciplina de Estrutura de Dados II
============

Trabalho para a disciplina de Estrutura de Dados II da Universidade Federal Rural do Rio de Janeiro.

Considere as 4 relações abaixo (os campos sublinhados são as chaves-primárias):

* Aluno (id, curso_id, matricula, nome)
* Disciplina (id, nome, curso_id)
* DisciplinaHistorico (id, aluno_id, disciplina_id, nota, ano, periodo, situacao)
* Curso (id, nome)

Partindo da premissa que cada relação está armazenada em um arquivo separado (considere que todos os valores são strings e que os campos são separados por “\t” e linhas por “\n”), crie um programa que seja capaz de realizar as seguintes consultas:

* Retornar os nomes e as matrículas dos alunos de um curso (nome do curso é informado como parâmetro);
* Retornar os nomes das disciplinas que um aluno tirou 10 (matrícula do aluno é informada como parâmetro);
* Retornar os nomes das disciplinas e suas respectivas notas para um aluno em um determinado ano-período (matrícula do aluno, ano e período são informados como parâmetros).
* (Opcional) O boletim de um aluno, que consiste em uma lista com os nomes das disciplinas, suas notas e situações, agrupadas por ano e período.

O programa deve:

* Possuir uma estrutura a ser chamada de Catálogo, onde serão armazenados os meta-dados das relações;
* Possuir um índice em Árvore B (a ser implementado pelo grupo) nos campos DisciplinaHistorico (aluno_id, ano, periodo);
* Estimar o custo dos diferentes planos de execução para cada consulta. Utilizando Programação Dinâmica, o programa deve encontrar o plano menos custoso e executá-lo. Repare que o plano de execução pode variar conforme os parâmetros informados pelo usuário;

O que deve ser entregue por cada grupo:

* Código-fonte e executável do programa;
* Relatório, em formato de monografia, contendo informações detalhadas do processo de construção do programa (impresso e em meio digital); e
* Manual de Instruções do programa (impresso e em meio digital).
