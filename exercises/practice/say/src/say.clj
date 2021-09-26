(ns say
  (:require [clojure.string :as str]))

(defn reverse-digits [num]
  (map #(mod % 10) (take-while (partial < 0) (iterate #(quot % 10) num))))

(defn digits "split num to digits list" [num]
  (reverse (reverse-digits num)))

(defn speak-1 [[num]]
  (case num
    1 "one"
    2 "two"
    3 "three"
    4 "four"
    5 "five"
    6 "six"
    7 "seven"
    8 "eight"
    9 "nine"
    ""))

(defn sanitize [pronouciation]
  (case pronouciation
    "ten-one" "eleven"
    "ten-two" "twelve"
    "ten-three" "thirteen"
    "ten-four" "fourteen"
    "ten-five" "fifteen"
    "ten-six" "sixteen"
    "ten-seven" "seventeen"
    "ten-eight" "eighteen"
    "ten-nine" "nineteen"
    pronouciation))

(defn speak-2 [[h l]]
  (let [low-digit (speak-1 [l])
        compound (case h
                   1 "ten"
                   2 "twenty"
                   3 "thirty"
                   4 "forty"
                   5 "fifty"
                   6 "sixty"
                   7 "seventy"
                   8 "eighty"
                   9 "ninety"
                   "")]
    (if (= h 0)
      low-digit
      (if (not= low-digit "")
        (sanitize (str compound "-" low-digit))
        compound))))

(defn speak-3 [[f m l]]
  (let [low-digits (speak-2 [m l])]
    (if (= f 0)
      low-digits
      (str (speak-1 [f]) " hundred" (when (not= low-digits "") (str " " low-digits))))))

(defn trunk [col unit]
  (let [len (count col)
        num-in-str (case len
                     1 (speak-1 col)
                     2 (speak-2 col)
                     3 (speak-3 col))]
    (if (not= num-in-str "")
      (str num-in-str " " unit)
      "")))

(def units ["" "thousand" "million" "billion" "trillion"])
(defn number [num]
  (if (or  (< num 0) (> num 999999999999))
    (throw (IllegalArgumentException. "should between 0 and 999999999999."))
    (if (= num 0) "zero"
        (let [trunks (map #(-> %1 reverse (trunk %2)) (partition 3 3 [] (reverse-digits num)) units)]
          (->> trunks
               reverse
               (str/join " ")
               str/trim)))))