(ns rotational-cipher)

(defn rotate [s delta]
  (letfn [(rot [c]
            (let [v (int c)]
              (cond
                (<= (int \a) v (int \z))
                (char (+ (int \a) (mod (+ (- v (int \a)) delta) 26)))
                (<= (int \A) v (int \Z))
                (char (+ (int \A) (mod (+ (- v (int \A)) delta) 26)))
                :else c)))]
    (apply str (map rot s))))