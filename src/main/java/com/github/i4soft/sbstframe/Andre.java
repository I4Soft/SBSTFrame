/*
 * Copyright 2015 Bruno.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.i4soft.sbstframe;

import java.io.IOException;

import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;

import com.github.i4soft.sbstframe.problem.Benchmarks;
import com.github.i4soft.sbstframe.problem.CachedProblem;
import com.github.i4soft.sbstframe.problem.DefaultProblemLazy;
import com.github.i4soft.sbstframe.results.Metrics;
import com.github.i4soft.sbstframe.solution.Experiment;
import com.github.i4soft.sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import com.github.i4soft.sbstframe.solution.searchTechniques.EvolutionaryStrategy;
import com.github.i4soft.sbstframe.solution.searchTechniques.GeneticAlgorithm;
import com.github.i4soft.sbstframe.solution.searchTechniques.IslandModel;
import com.github.i4soft.sbstframe.solution.searchTechniques.MyTerminalCondition;
import com.github.i4soft.sbstframe.solution.searchTechniques.RandomAlgorithm;
import com.github.i4soft.sbstframe.solution.searchTechniques.SteadyState;
import com.github.i4soft.sbstframe.solution.searchTechniques.SelectionOperator;
import com.github.i4soft.sbstframe.problem.IProblem;

/**
 *
 * @author Bruno
 */
public class Andre {
    
       public static void main(String[] args) throws IOException {
        // TODO code application logic here
       //framework's benchmark and algorithm
        IProblem defaultReport;
        
        //total of executions, max time in seconds for each execution, 
        //optional: results folder and/or if show evolution
        Experiment exp;
        AbstractEvolutionaryAlgorithm alg;
        Metrics results;
        
        for(int i= 1; i<2; i++){
            
            switch(i){
                 
                case 0:
                     System.out.println("Experimento 1 - EvolutionaryStrategy (1,7) ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(4, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new EvolutionaryStrategy(defaultReport, 39, 4, 0.95, 0.05,false,7); //restante dos params padrao
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                     
                case 1:
                     System.out.println("Experimento 1 - EvolutionaryStrategy (1,7) OpOMMAC 10%");
                     defaultReport = new DefaultProblemLazy(Benchmarks.spaceResultByLine);
                     defaultReport = new CachedProblem(defaultReport);
                     exp = new Experiment(2, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new GeneticAlgorithm(defaultReport, 50, 10, 0.95, 0.05, SelectionOperator.RouletteWheel); //restante dos params padrao
                    alg.setTerminationCondition(new MyTerminalCondition(2, alg.getFitnessFunction()));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                 
                 case 2:
                     System.out.println("Experimento 1 - EvolutionaryStrategy (1,7) OpOMMAC 40%");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new EvolutionaryStrategy(defaultReport, 39, 4, 0.95, 0.05,false,7, 0.4); //restante dos params padrao
                    

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                     
                 case 3:
                     System.out.println("Experimento 1 - EvolutionaryStrategy (1,7) OpOMMAC 70%");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new EvolutionaryStrategy(defaultReport, 39, 4, 0.95, 0.05,false,7, 0.7); //restante dos params padrao
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;

                 case 4:
                     System.out.println("Experimento 1 -  EvolutionaryStrategy (1 + 7) OMMAC 10%  ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new EvolutionaryStrategy(defaultReport, 39, 4, 0.95, 0.05,true,7,0.1); //restante dos params padrao

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                
                case 5:
                     System.out.println("Experimento 1 - EvolutionaryStrategy (1 + 1) OMMAC 10% ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate,plus selection, offspringmultiplayer, OMMAC)
                    alg = new EvolutionaryStrategy(defaultReport, 39, 4, 0.95, 0.05,true,1,0.1); //restante dos params padrao


                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
               
                case 6:
                     System.out.println("Experimento 1 - SteadyState 2, 2 OMMAC 10% ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, selectionSize, forceSingle, enum selectionStrategy, OMMAC )
                    alg = new SteadyState(defaultReport, 39, 4, 0.95, 0.05,2,true,SelectionOperator.RouletteWheel, 0.1); //restante dos params padrao


                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;    
                         
                case 7:
                     System.out.println("Experimento 1 - SteadyState 2,1 OMMAC 10%  ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, selectionSize, forceSingle, enum selectionStrategy, OMMAC )
                    alg = new SteadyState(defaultReport, 39, 4, 0.95, 0.05,2,false, SelectionOperator.RouletteWheel, 0.1); //restante dos params padrao

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;  
                    
                    
                case 8:
                     System.out.println("Experimento 1 - Modelo Ilhas  épocas: 25 migração: 5; OMMAC 10%  ");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch, enum selectionStrategy, OMMAC )
                    alg = new IslandModel(defaultReport, 39, 4, 0.95, 0.05,2,5,25, SelectionOperator.RouletteWheel, 0.1); //restante dos params padrao

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;  
                    
                 case 9:
                     System.out.println("Experimento 1 - AG OMMAC  10%");
                     defaultReport = new DefaultProblemLazy(Benchmarks.uniq);
                     exp = new Experiment(4, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, enum selectionStrategy, OMMAC )
                    alg = new GeneticAlgorithm(defaultReport, 39, 4, 0.95, 0.05, SelectionOperator.RouletteWheel, 0.1); //restante dos params padrao

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break; 
                
                 case 10:
                     
                    System.out.println("Experimento 1 - AL ");
                    defaultReport = new DefaultProblemLazy(Benchmarks.spaceResultByMethod);
                    exp = new Experiment(30, 180);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, enum selectionStrategy, OMMAC )
                    alg = new RandomAlgorithm(defaultReport, 39, 4); //restante dos params padrao

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break; 
            }
            
        }
    }
}
