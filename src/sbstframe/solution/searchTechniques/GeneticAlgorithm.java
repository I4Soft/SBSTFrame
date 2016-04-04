/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *  Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br
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

package sbstframe.solution.searchTechniques;

import java.util.ArrayList;
import java.util.List;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.IntArrayCrossover;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
//import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import sbstframe.solution.searchTechniques.operators.IntArrayFactory;
import sbstframe.solution.searchTechniques.operators.IntArrayMutation;
import sbstframe.solution.searchTechniques.operators.IntArrayMutationOMAC;
import sbstframe.problem.IProblem;

public class GeneticAlgorithm extends AbstractEvolutionaryAlgorithm{
    private double crossRate, mutRate,classificatoryRate;
    
    public GeneticAlgorithm(IProblem benchmark, int popSize, int indSize,
            double crossRate, double mutRate, SelectionOperator selectionStrategy){
        
        super(benchmark, popSize, indSize);
        this.crossRate = crossRate;
        this.mutRate = mutRate;
        this.classificatoryRate = 0.0;
        
        IntArrayFactory factory = new IntArrayFactory(indSize, benchmark.getTestCaseTotal());
        List<EvolutionaryOperator<int[]>> operators = new ArrayList<EvolutionaryOperator<int[]>>(2);
        IntArrayCrossover crossoverOp = new IntArrayCrossover(1, new Probability(crossRate));
        IntArrayMutation mutationOp = new IntArrayMutation(benchmark.getTestCaseTotal(), new Probability(mutRate));
        operators.add(crossoverOp);
        operators.add(mutationOp);
        EvolutionPipeline<int[]> pipeline = new EvolutionPipeline<int[]>(operators);
        
        SelectionStrategy selection = null;

        
        FitnessFunction fitness = getFitnessFunction();
        
         switch(selectionStrategy){
             case RouletteWheel:
                selection = new RouletteWheelSelection();
                break;
             
         }
        
       EvolutionEngine<int[]> engine = new GenerationalEvolutionEngine<int[]>(factory, 
            pipeline, fitness, selection, new MersenneTwisterRNG());
        
        setEngine(engine);
        
    }
    
    public GeneticAlgorithm(IProblem benchmark, int popSize, int indSize,
            double crossRate, double mutRate, SelectionOperator selectionStrategy,
            double classificatoryRate){
        
        super(benchmark, popSize, indSize);
        this.crossRate = crossRate;
        this.mutRate = mutRate;
        this.classificatoryRate = classificatoryRate;
        
        IntArrayFactory factory = new IntArrayFactory(indSize, benchmark.getTestCaseTotal());
        List<EvolutionaryOperator<int[]>> operators = new ArrayList<EvolutionaryOperator<int[]>>(2);
        IntArrayCrossover crossoverOp = new IntArrayCrossover(1, new Probability(crossRate));
        //IntArrayMutation mutationOp = new IntArrayMutation(benchmark.getTestCaseTotal(), new Probability(mutRate));
        IntArrayMutationOMAC mutationOpOMMAC = new IntArrayMutationOMAC(this,new Probability(mutRate), new Probability(classificatoryRate));

        operators.add(crossoverOp);
        operators.add(mutationOpOMMAC);
        EvolutionPipeline<int[]> pipeline = new EvolutionPipeline<int[]>(operators);
        
        SelectionStrategy selection = null;

        
        FitnessFunction fitness = getFitnessFunction();
        
         switch(selectionStrategy){
             case RouletteWheel:
                selection = new RouletteWheelSelection();
                break;
             
         }
        
       EvolutionEngine<int[]> engine = new GenerationalEvolutionEngine<int[]>(factory, 
            pipeline, fitness, selection, new MersenneTwisterRNG());
        
        setEngine(engine);
        
    }
    @Override
    public String toString(){
        return "geneticAlg;"+crossRate+";"+mutRate+";"+ "useClassification;"+this.classificatoryRate;
    }
 
}
