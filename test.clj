#!/usr/bin/env bb

(require
 '[cheshire.core :as json]
 '[clojure.string :as str]
 '[babashka.classpath :as cp]
 '[clojure.java.shell :as shell])

(def root "/github/workspace/main/")
(def test-runner "/github/workspace/clojure-test-runner/test-runner.clj")

(comment
  (def root "/home/porky/exercism/babashka/")
  (def test-runner "/home/porky/exercism/babashka/test-runner.clj")
  )

(defn- ->snake_case [s] (str/replace s \- \_))

(def practice-exercises
  (map #(% "slug")
       (-> (str root "config.json")
           slurp
           json/parse-string
           (get "exercises")
           (get "practice"))))

(def concept-exercises
  (map #(% "slug")
       (-> (str root "config.json")
           slurp
           json/parse-string
           (get "exercises")
           (get "concept"))))

(defn test-exercise [slug]
  (let [concept? (contains? (set concept-exercises) slug)
        practice? (contains? (set practice-exercises) slug)
        example (if practice?
                  (str root "exercises/practice/" slug "/src/example.clj")
                  (str root "exercises/concept/" slug "/.meta/exemplar.clj"))
        src (if practice?
              (str root "exercises/practice/" slug "/src/" (->snake_case slug) ".clj")
              (str root "exercises/concept/" slug "/.meta/" (->snake_case slug) ".clj"))]
    (shell/sh "cp" example src)
    (= "pass" ((json/parse-string
                (:out (shell/sh test-runner
                                slug
                                (str root (if practice? "exercises/practice/" "exercises/concept/") slug "/")
                                (str root (if practice? "exercises/practice/" "exercises/concept/") slug "/"))))
               "status"))))

(defn test-exercises! []
  (for [exercise (into practice-exercises concept-exercises)]
    {(keyword exercise) (test-exercise exercise)}))

(let [results (test-exercises!)
      fails (filter false? results)]
  (prn {:tested (count results)
        :fails fails})
  (System/exit (count fails)))
