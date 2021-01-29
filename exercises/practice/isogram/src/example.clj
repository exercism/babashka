(ns isogram
  (:require [clojure.string :as str]))

(defn isogram?
  [w]
  (every? #(= 1 %)
          (-> w
              (str/lower-case)
              (str/replace #"-| " "")
              (frequencies)
              (vals))))