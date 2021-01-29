(ns largest-series-product)

(defn- digits->numbers
  [digits]
  (map #(- (int %) 48) digits))

(defn- product
  [digits]
  (->> digits
       digits->numbers
       (apply *)))

(def valid (into #{} "0123456789"))

(every? #(valid %) "1a23")

(defn largest-product [n digits-str]
  {:pre [(not (neg? n))
         (>= (count digits-str) n)
         (every? #(valid %) digits-str)]}
  (if (zero? n)
    1
    (->> digits-str
         (partition n 1)
         (map product)
         (apply max))))