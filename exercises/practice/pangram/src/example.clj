(ns pangram
  (:require [clojure.string :as str]))

(defn- char<= [ch1 ch2] (<= (compare (str ch1) (str ch2)) 0))

(defn pangram? [input]
  (->> input
       (into [] (comp (map #(str/lower-case (str %)))
                      (filter #(and (char<= \a %) (char<= % \z)))
                      (distinct)))
       count
       (= 26)))
