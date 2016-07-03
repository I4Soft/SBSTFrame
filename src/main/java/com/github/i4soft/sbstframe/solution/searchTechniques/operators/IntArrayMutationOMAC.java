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

package com.github.i4soft.sbstframe.solution.searchTechniques.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import com.github.i4soft.sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import com.github.i4soft.sbstframe.solution.searchTechniques.FitnessFunction;

public class IntArrayMutationOMAC implements EvolutionaryOperator<int[]> {
    Probability mutRate, classificatoryRate;
    int tcTotal, reqTotal;
    FitnessFunction fitness;

    public IntArrayMutationOMAC(AbstractEvolutionaryAlgorithm alg, 
            Probability mutationRate, Probability classificatoryRate) {
        this.tcTotal = alg.getBenchmark().getTestCaseTotal();
        this.reqTotal = alg.getBenchmark().getRequirementTotal();
        this.fitness = alg.getFitnessFunction();
        this.mutRate = mutationRate;
        this.classificatoryRate = classificatoryRate;
    }
    @Override
    public List<int[]> apply(List<int[]> selectedCandidates, Random rng) {  
        //ArrayList<ArrayList> arr = new ArrayList<>();
        List<int[]> result = new ArrayList<>(selectedCandidates.size());
        for (int[] candidate : selectedCandidates) {
            
            int[] copy = candidate.clone();
            
            if(rng.nextDouble() <= mutRate.doubleValue()){
                if (rng.nextDouble() > classificatoryRate.doubleValue()) {
                        int index = rng.nextInt(candidate.length);
                        int value = rng.nextInt(tcTotal);
                        copy[index] = value;

                }
            
                else{ //mut proposed
                    List<ArrayList> arr = new ArrayList<>(candidate.length);
                    Set <Integer> tmp = new HashSet<>();
                    for (int j = 0; j<candidate.length; j++){
                        arr.add(new ArrayList<Integer>());
                        arr.get(j).add(j);
                    }
                    for (int i = 0; i<reqTotal; i++){
                        for (int j = 0; j<candidate.length; j++){
                            if(fitness.executeTest(i, candidate[j])){
                                /*requirement i foi covered by test case j of the individual*/
                                arr.get(j).add(i);
                            }
                        }
                    }
                    
                    Collections.sort(arr, new Comparator<ArrayList>(){
                        public int compare(ArrayList a1, ArrayList a2) {
                            return a2.size() - a1.size(); // assumes you want biggest to smallest
                        }
                    });
                    
                    boolean flag = false; 
                    
                    for (int i = 0; i < arr.size() - 1; i++){
                        if(!flag){
                            tmp.addAll(arr.get(i).subList(1, arr.get(i).size()));
                        }
                        else flag = false;
                        
                        if(tmp.containsAll(arr.get(i+1).subList(1, arr.get(i+1).size()))) { //it's a nef
                            copy[(int)arr.get(i+1).get(0)] = rng.nextInt(tcTotal);
                            flag = true;
                        }
                    }
                    if(arr.get(0).size() == 1){
                        copy[(int)arr.get(0).get(0)] = rng.nextInt(tcTotal);
                    }
                    

                }
            }
            
            result.add(copy);
            
        }
        return result;
    }
}
