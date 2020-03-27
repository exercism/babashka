(ns bob
  (:require [clojure.string :as str]))

(defn response-for [s]
  (cond
    (not (nil? (re-matches #"[\s\t\n]*" s))) "Fine. Be that way!"
    (not (nil? (re-matches #"[A-Z\s]+\?" s))) "Calm down, I know what I'm doing!"
    (not (nil? (re-matches #".+\?\s*$" s))) "Sure."
    (not (nil? (re-matches #"(?=.*[A-Z])[0-9,A-Z\W\s]+!?$" s))) "Whoa, chill out!"

    :else "Whatever."))