/*
 * Copyright 2014 Bruno.
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

/**
 *
 * @author Bruno
 */
import org.uncommons.watchmaker.framework.termination.ElapsedTime;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import com.github.i4soft.sbstframe.problem.Benchmarks;
import com.github.i4soft.sbstframe.problem.DefaultProblemLazy;
import com.github.i4soft.sbstframe.results.Metrics;
import com.github.i4soft.sbstframe.solution.Experiment;
import com.github.i4soft.sbstframe.solution.searchTechniques.*;
import com.github.i4soft.sbstframe.solution.searchTechniques.operators.FitnessOrTime;
import com.github.i4soft.sbstframe.problem.IProblem;

public class Teste {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
    public static void main(String[] args) {
        // TODO code application logic here
       //framework's benchmark and algorithm
        ProblemInterface defaultReport;
        
        //total of executions, max time in seconds for each execution, 
        //optional: results folder and/or if show evolution
        Experiment exp;
        AbstractEvolutionaryAlgorithm alg;
        Metrics results;
        
        for(int i= 62; i<69; i++){
            
            switch(i){
                case 0:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 1:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 2:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 3:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 4:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 5:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
             
                case 6:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 7:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 8:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 9:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 10:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 11:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                 case 12:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 13:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 14:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 15:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 16:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 17:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
                    
                 case 18:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.comm);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 19:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.comm);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 20:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.comm);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 21:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.comm);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 22:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.comm);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 23:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.comm);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
                    
                  case 24:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.fourballs);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 25:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.fourballs);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 26:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.fourballs);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 27:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.fourballs);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 28:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.fourballs);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 29:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.fourballs);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
                    
                case 30:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.look);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 31:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.look);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 32:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.look);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 33:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.look);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 34:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.look);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 35:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.look);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
                case 36:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.mid);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 37:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.mid);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 38:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.mid);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 39:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.mid);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 40:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.mid);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 41:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.mid);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
                case 42:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.trityp);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 43:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.trityp);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 44:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.trityp);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 45:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.trityp);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 46:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.trityp);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 47:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.trityp);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    
                    
               case 48:
                     System.out.println("Experimento 1 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.uniq);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 49:
                     System.out.println("Experimento 2 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.uniq);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 50:
                     System.out.println("Experimento 3 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.uniq);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 51:
                     
                    System.out.println("Experimento 4 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.uniq);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 52:
                     
                    System.out.println("Experimento 5 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.uniq);
                    exp = new Experiment(30,60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                case 53:
                     
                    System.out.println("Experimento 6 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.uniq);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                    /* Não deu certo ler o bechmark tao grande.. estouro de memória
                    case 6:
                     System.out.println("Experimento 7 - Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 7:
                     System.out.println("Experimento 8 - Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 8:
                     System.out.println("Experimento 9 - Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                              
                case 9:
                     
                    System.out.println("Experimento 10 - Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                case 10:
                     
                    System.out.println("Experimento 11 - Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                
                case 11:
                     
                    System.out.println("Experimento 12 - Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByLine);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;                    
                    */
                    
                    
                    /*
                case 54:
                     
                    System.out.println("Experimento 1 - Benchmark Bub Algoritmo Aleatório ");
                    defaultReport = new DefaultReport(Benchmarks.bubcorrecto);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                    
                   case 55:
                     
                    System.out.println("Experimento 1 - Benchmark Comm Algoritmo Aleatório");
                    defaultReport = new DefaultReport(Benchmarks.comm);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                   case 56:
                     
                    System.out.println("Experimento 1 - Benchmark Fourballs Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.fourballs);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                   case 57:
                     
                    System.out.println("Experimento 1 - Benchmark Look Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.look);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                   case 58:
                     
                    System.out.println("Experimento 1 - Benchmark Mid Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.mid);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                       
                   case 59:
                     
                    System.out.println("Experimento 1 - Benchmark Space Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.spaceResultByMethod);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                       
                   case 60:
                     
                    System.out.println("Experimento 1 - Benchmark TRityp Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.trityp);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
                       
        
                   case 61:
                     
                    System.out.println("Experimento 1 - Benchmark Uniq Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.uniq);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                       
            //######################################################################33
                    case 62:
                     
                    System.out.println("Experimento 1 - Benchmark cal Algoritmo Aleatório Taxa de Mutação 1 %");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new RandomAlgorithm(defaultReport, 100, 10); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
                                      
                case 63:
                     
                    System.out.println("Experimento 2 - Benchmark cal Algoritmo Genetico Taxa de Mutação 1%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.01); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;        
                    
                case 64:
                     
                    System.out.println("Experimento 3 - Benchmark cal Algoritmo Genetico Taxa de Mutação 5%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30,60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.05); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());   
                    break;  
                    
                    
                case 65:
                     
                    System.out.println("Experimento 4 - Benchmark cal Algoritmo Genetico Taxa de Mutação 20%");
                    defaultReport = new DefaultReport(Benchmarks.cal);
                    exp = new Experiment(30, 60);
        
                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
                    alg = new GeneticAlgorithm(defaultReport, 100, 10, 0.80, 0.20); //restante dos params padrao

                    alg.setTerminationCondition(new  GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                    break;
               
                case 66:
                     System.out.println("Experimento 5 - Benchmark cal Modelo em Ilhas Taxa de Mutação 1% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.01,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                
                case 67:
                     System.out.println("Experimento 6 - Benchmark cal Modelo em Ilhas Taxa de Mutação 5% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.05,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));
                    
                    
                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
                                
                case 68:
                     System.out.println("Experimento 7 - Benchmark cal Modelo em Ilhas Taxa de Mutação 20% ");
                     defaultReport = new DefaultReport(Benchmarks.cal);
                     exp = new Experiment(30, 60);

                    //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate, islands, migration, epoch)
                    alg = new IslandModel(defaultReport, 100, 10, 0.80, 0.20,3, 10, 30); //restante dos params padrao

                    alg.setTerminationCondition(new GenerationCount(200));

                    results = exp.run(alg);
                    System.out.println(results.toString());
                  break;
                                    
               
            }
        }
    }
*/
}


