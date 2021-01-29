(ns pov)

(defn- list->map
  ([lst] (list->map lst {} nil))
  ([[me & children] rv prev]
   (let [rv (reduce #(list->map %2 %1 me) (assoc rv me []) children)]
     (if-not prev rv
             (-> rv
                 (update me conj prev)
                 (update prev conj me))))))

(defn- of-tree [start tree prev]
  (when (tree start)
    (apply vector start (map #(of-tree % tree start)
                             (remove #(= % prev) (tree start))))))

(defn of [start lst] (of-tree start (list->map lst) nil))

(defn- path-to [to [me & children] rv]
  (let [rv (conj rv me)]
    (if (= me to) rv
        (first (remove nil? (map #(path-to to % rv) children))))))

(defn path-from-to [from to lst] (path-to to (of from lst) []))