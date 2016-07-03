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

package com.github.i4soft.sbstframe.solution.searchTechniques;

import java.util.ArrayList;
import java.util.List;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import com.github.i4soft.sbstframe.solution.searchTechniques.operators.IntArrayFactory;
import com.github.i4soft.sbstframe.solution.searchTechniques.operators.IntArrayMutationAll;
import com.github.i4soft.sbstframe.problem.IProblem;

public class RandomAlgorithm extends AbstractEvolutionaryAlgorithm{
    public RandomAlgorithm(IProblem benchmark, int popSize, int indSize){
        
        super(benchmark, popSize, indSize);
        
        IntArrayFactory factory = new IntArrayFactory(indSize, benchmark.getTestCaseTotal());
        List<EvolutionaryOperator<int[]>> operators = new ArrayList<EvolutionaryOperator<int[]>>(2);
        IntArrayMutationAll mutationOp = new IntArrayMutationAll(benchmark.getTestCaseTotal(), new Probability(1d));
        operators.add(mutationOp);
        EvolutionPipeline<int[]> pipeline = new EvolutionPipeline<int[]>(operators);
        
        
        FitnessFunction fitness = getFitnessFunction();
        
        RandomSelection selection = new RandomSelection();
        
        EvolutionEngine<int[]> engine = new GenerationalEvolutionEngine<int[]>(factory, 
            pipeline, fitness, selection, new MersenneTwisterRNG());
        
        setEngine(engine);
        
    }
    
    @Override
    public String toString(){
        return "random;";
    }
}
