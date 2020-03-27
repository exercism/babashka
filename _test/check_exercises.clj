(ns check-exercises
  (:require [clojure.string :as str]
   [clojure.test :refer [deftest is successful? run-tests]]
   [cheshire.core :as json]))

(defn- ->snake_case [s] (str/replace s \- \_))

(deftest check-exercises
  (doseq [exercise ((json/parse-string (slurp "config.json")) "exercises")
          :let [slug             (exercise "slug")
                path-to-exercise (partial str "exercises/" slug "/")
                exercise-tests   (symbol (str slug "-test"))]]
    (load-file (path-to-exercise "src/example.clj"))
    (load-file (path-to-exercise "test/" (->snake_case slug) "_test.clj"))
    (is (successful? (run-tests exercise-tests)))))

(let [report (run-tests)]
  (System/exit (+ (:fail report)
                  (:error report))))