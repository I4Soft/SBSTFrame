/*
 * Copyright 2015 Eduardo Horst.
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
package com.github.i4soft.sbstframe.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Class for storage of test results in order to not need re-tests.
 * Be aware, it only caches getTest() results.
 * @author Eduardo Horst
 */
public class CachedProblem extends ProblemDecorator {
    private final Map<Key, Boolean> cache;
    private final int MAX_KEYS;
    private final Random rand;
    
    /**
     * Default Constructor
     * sets the max quantity of entrys in to 3000
     * @param myProblem The ProblemInterface to be cached
     */
    public CachedProblem(IProblem myProblem) {
        super(myProblem);
        MAX_KEYS = 3000;
        cache = new ConcurrentHashMap<>(MAX_KEYS);

        rand = new Random();
    }
    
    /**
     * Constructor enabling the max quantity of keys in this cache
     * @param myProblem
     * @param MAX_KEYS max entrys to be saved (after entrys will be replaced)
     */
    public CachedProblem(IProblem myProblem, int MAX_KEYS) {
        super(myProblem);
        
        this.MAX_KEYS = MAX_KEYS;
        cache = new HashMap<>(MAX_KEYS);
        rand = new Random();
    }
    
    /**
     * Returns the result of the same call for myProblem.getTest() (given in the 
     * constructor), but it stores it's result so it won't need to make the same
     * call in the future.
     * Be aware that since it only calls myProblem.getTest() once, it will all-
     * ways return the same result (given same parameters).
     * @param testCase
     * @param testReq
     * @return 
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        final Key currentKey;
        currentKey = new Key(testCase, testReq);
        
        return cache.computeIfAbsent(currentKey, new Function<Key, Boolean>() {
            @Override
            public synchronized Boolean apply(Key myKey) {
                while(cache.size() >= MAX_KEYS) removeRandomKey();
                return problem.getTest(myKey.testCase, myKey.testReq);
                
            }
        });
    }
    
    /**
     * Removes a random key from this cache
     */
    private synchronized void removeRandomKey() {
        cache.remove(cache.keySet().stream().skip(rand.nextInt(cache.size())).findAny().get());
    }

    /**
     * Storage for the key identifier used in hashmap
     */
    private class Key {
        /**
         * Index of the test case
         */
        private final int testCase;
        
        /**
         * Index of the test requirement
         */
        private final int testReq;
        
        /**
         * Creates a key for usage in the hash map, note parameters order matter,
         * expression ( new key(1,2) != new key(2,1) ) is true
         * @param testCase index of test case
         * @param testReq  index of test requirement
         */
        private Key(int testCase, int testReq) {
            this.testCase = testCase;
            this.testReq = testReq;
        }
        
        /**
         * Exposes testCase for reading
         * @return testCase
         */
        public int getTestCase() {
            return testCase;
        }
        
        /**
         * Exposes testReq for reading
         * @return 
         */
        public int getTestReq() {
            return testReq;
        }

        /**
         * Hash function for this class, depedent on testCase and testReq
         * @return hashCode
         */
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + this.testCase;
            hash = 97 * hash + this.testReq;
            return hash;
        }

        /**
         * Compares this instance with obj
         * @param obj any other object
         * @return ture if obj is an instance of Key and has the same testCase 
         * and testReq
         */
        @Override
        public boolean equals(Object obj) {
            if (obj != null &&
                getClass() == obj.getClass()) {
                
                final Key other = (Key) obj;
                
                return this.testCase == other.testCase && 
                        this.testReq == other.testReq;
                
            }

            return false;
        }
    }
}
