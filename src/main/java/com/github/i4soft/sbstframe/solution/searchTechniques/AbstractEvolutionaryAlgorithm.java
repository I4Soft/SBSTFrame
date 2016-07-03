/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *      Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br>
 *      Eduardo Horst          <eduardoquijano2@gmail.com>
 * 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.github.i4soft.sbstframe.solution.searchTechniques;

import com.github.i4soft.sbstframe.solution.searchTechniques.operators.FitnessOrTime;
import com.github.i4soft.sbstframe.solution.searchTechniques.operators.EvolutionLogger;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import com.github.i4soft.sbstframe.results.Metrics;
import com.github.i4soft.sbstframe.problem.IProblem;
   
public abstract class AbstractEvolutionaryAlgorithm {
    /**
     * Fitness Function
     */
    private FitnessFunction fitness;
    
    /**
     * Population size
     */
    private int popSize;
    
    /**
     * Individual size
     */
    private int indSize;
    
    /**
     * Quantity of individuals choosen from elitism
     */
    private int elitism;
    
    /**
     * About the fitness function
     * True - is maximizing
     * False - is minimizing
     */
    private boolean maximizing;
    
    /**
     * Condition to terminate the execution
     */
    private TerminationCondition terminationCondition;
  //  private MyTerminalCondition terminationCondition2;
    
    /**
     * Evolution engine
     */
    private EvolutionEngine<int[]> engine;
    
    /**
     * Islando evolution engine
     */
    private IslandEvolution<int[]> islandEngine;

    /**
     * Test source
     */
    private IProblem benchmark;
    
    /**
     * ? related to island engine
     */
    private int epoch;
    
    /**
     * ? related to island engine
     */
    private int migration;
    
    
    /**
     * Default constructor
     * @param benchmark test source
     * @param popSize population size
     * @param indSize individual size
     */
    public AbstractEvolutionaryAlgorithm(IProblem benchmark, int popSize, int indSize) {
        this.benchmark = benchmark;
        this.popSize = popSize;
        this.indSize = indSize;
        this.fitness = new FitnessFunction(benchmark);
        this.elitism = 1;
        this.maximizing = true;
        this.migration = -1;
        this.epoch = -1;
    }
                
    /**
     * Calls the evolve of engine (if migration == -1) or of 
     * islandEngine(otherwise)
     * @return 
     */
    public int[] evolve() {
        
        if(migration == -1){
            
            int[] result = engine.evolve(popSize, elitism, terminationCondition); //cond. termino, fitness, maximiza ou min, miliseg        
            return result;
        }
        
        int[] result = islandEngine.evolve(popSize, elitism, epoch, migration, terminationCondition); //cond. termino, fitness, maximiza ou min, miliseg
        return result;

    }
   
    /**
     * Exposes popSize to overwrite
     * @param popSize 
     */
    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }
    
    /**
     * Exposes popSize to read
     * @return popSize
     */
    public int getPopSize() {
        return popSize;
    }

    /**
     * Exposes elitism to overwrite
     * @param elitism 
     */
    public void setElitism(int elitism) {
        this.elitism = elitism;
    }

    /**
     * Exposes Maximizing to overwrite
     * where true  - is Maximizing
     *       false - is Minimizing
     * @param maximizing 
     */
    public void setMaximizing(boolean maximizing) {
        this.maximizing = maximizing;
    }
    
    /**
     * Exposes fitness to overwrite
     * @param fitness 
     */
    public void setFitnessFunction(FitnessFunction fitness) {
        this.fitness = fitness;
    }
    
    /**
     * Exposes fitness to Read
     * @return fitness
     */
    public FitnessFunction getFitnessFunction() {
        return this.fitness;
    }
    
    /**
     * Exposes evaluatorCallsTotal to read
     * @return evalutorCallsTotal
     */
    public int getEvaluatorCallsTotal() {
        return fitness.getEvaluatorCallsTotal();
    }
    
    /**
     * Exposes the evolution engine to read
     * @return engine
     */
    public EvolutionEngine<int[]> getEngine() {
        return engine;
    }

    /**
     * Exposes the evolutionEngine to read
     * @param engine 
     */
    public void setEngine(EvolutionEngine<int[]> engine) {
        this.engine = engine;
    }
    
    /**
     * Exposes the Island engine to read
     * @return islandEngine
     */
    public IslandEvolution<int[]> getIslandEngine() {
        return this.islandEngine;
    }

    /**
     * Sets the parameters for island algorithm usage
     * @param engine island evolutionary engine
     * @param migration migration
     * @param epoch ?
     */
    public void setIslandEngine(IslandEvolution<int[]> engine, int migration, int epoch) {
        this.islandEngine = engine;
        this.migration = migration;
        this.epoch = epoch;
    
    }

    /**
     * Set timelimit for the execution of the alghorithm
     * @param time 
     */
    public void setTime(int time) {
        if(this.terminationCondition == null){
            this.terminationCondition = new FitnessOrTime(/*benchmark.getScoreMax()*/ 1, maximizing, time);
        }
    }
    
    /* Tentativa de controlar a chamada de funçao fitness
    public void setCallFitness(int maxCallFitness) {
            this.terminationCondition = new MyTerminalCondition(maxCallFitness, 0);
    }*/
    
    /**
     * Exposes the test source to read
     * @return benchmark
     */
    public IProblem getBenchmark() {
        return benchmark;
    }
    
    /**
     * Exposes indSize to read
     * @return indSize
     */
    public int getIndSize() {
        return indSize;
    }
    
    
    /**
     * Exposes metrics to add ?
     * @param metrics  
     */
    public void addMetrics(Metrics metrics){
        this.engine.addEvolutionObserver(new EvolutionLogger(metrics));
    }
    
    /**
     * Exposes terminationCondition to read
     * @return terminationCondition
     */
    public TerminationCondition getTerminationCondition() {
        return terminationCondition;
    }

    /**
     * Exposese terminationCondition to overwrite
     * @param terminationCondition 
     */
    public void setTerminationCondition(TerminationCondition terminationCondition) {
        this.terminationCondition = terminationCondition;
    }
    
}
