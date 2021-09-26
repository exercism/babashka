(ns trinary)

(def dict {\0 0 \1 1 \2 2})

(defn to-decimal [x]
  (if (every? dict x)
    (reduce + (map #(* (dict %) %2)
                   (reverse x) (iterate #(* 3 %) 1)))
    0))