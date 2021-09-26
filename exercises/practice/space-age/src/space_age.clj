(ns space-age)

(def year-seconds (* 365.25 24 60 60))

(defmacro defperiod
  [planet ratio]
  `(defn ~(symbol (str "on-" planet))
     [seconds#]
     (/ seconds# (* ~ratio year-seconds))))

(defperiod "earth"   1.0)
(defperiod "mercury" 0.2408467)
(defperiod "venus"   0.61519726)
(defperiod "mars"    1.8808158)
(defperiod "jupiter" 11.862615)
(defperiod "saturn"  29.447498)
(defperiod "uranus"  84.016846)
(defperiod "neptune" 164.79132)