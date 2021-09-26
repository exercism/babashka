(ns minesweeper
  (:require [clojure.string :as str]))

(def ordinals
  (disj (set (for [x [-1 0 1]
                   y [-1 0 1]]
               [x y])) [0 0])) ; no origin coord [0 0]

(def glyphs {:bomb  \*
             :empty \space})

(def bomb? #{(:bomb glyphs)})

(defn bombs
  [tiles coords]
  (reduce + (for [terms ordinals
                  :when (bomb? (get-in tiles (map + coords terms)))]
              1)))

(defn sweep
  [tiles coords]
  (let [hits (bombs tiles coords)]
    (if (zero? hits)
      (:empty glyphs)
      hits)))

(defn flag
  [tiles coords]
  (if (bomb? (get-in tiles coords))
    (:bomb glyphs)
    (sweep tiles coords)))

(defn draw
  [board]
  (let [tiles  (str/split-lines board)
        width  (count (first tiles))
        height (count tiles)
        reveal (for [y (range width) x (range height)]
                 (flag tiles [x y]))]
    (str/join \newline (map str/join (partition width reveal)))))