(ns octal)

(defn- digit-to-value [dgt-char]
  {:post [(and (>= % 0) (< % 8))]}
  (- (int dgt-char) (int \0)))

(defn to-decimal [oct-str]
  (try
    (reduce
     (fn [value digit] (+ (* 8 value) (digit-to-value digit)))
     0
     oct-str)
    (catch AssertionError _  0)))