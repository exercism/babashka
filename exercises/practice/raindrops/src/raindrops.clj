(ns raindrops)

(defn convert [number]
  (cond-> nil
    (zero? (mod number 3)) (str "Pling")
    (zero? (mod number 5)) (str "Plang")
    (zero? (mod number 7)) (str "Plong")
    :else (or (str number))))