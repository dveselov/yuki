(ns yuki.bayes
  (:require [yuki.language :as language]))


(def documents  (atom {}))
(def categories (atom {}))

(defn words-in-category [category]
  (count (category @categories)))

(defn unique-words-count []
  (count
    (set
      (flatten
        (map #(keys (%1 @categories)) (keys @categories))))))

(defn get-word-count [word category]
  (get-in @categories [category word] 0))

(defn inc-word-count [word category]
  (let [word-count (get-word-count word category)]
    (swap! categories assoc-in [category word] (inc word-count))))

(defn dec-word-count [word category]
  (let [word-count (get-word-count word category)]
    (if (> word-count 0)
      (swap! categories assoc-in [category word] (dec word-count)))))

(defn get-documents-count [kw]
  (get-in @documents [kw] 0))

(defn inc-documents-count [kw]
  (let [docs-count (get-documents-count kw)]
    (swap! documents assoc-in [kw] (inc docs-count))))

(defn dec-documents-count [kw]
  (let [docs-count (get-documents-count kw)]
    (if (> docs-count 0)
      (swap! documents assoc-in [kw] (dec docs-count)))))

(defn total-documents-count []
  (reduce +
    (map #(get-documents-count %1) (keys @documents))))

(defn train [source category]
  (let [source (language/normalize source)]
    (do
      (doall (map #(inc-word-count %1 category) source))
      (inc-documents-count category))))

(defn untrain [source category]
  (let [source (language/normalize source)]
    (do
      (doall (map #(dec-word-count %1 category) source))
      (dec-documents-count category))))

(defn word-probability [word category]
  (let [word-count             (get-word-count word category)
        words-in-this-category (words-in-category category)
        total-unique-words     (unique-words-count)]
    (Math/log
      (/ (+ word-count 1) (+ total-unique-words words-in-this-category)))))

(defn category-probability [source category]
  (let [total-documents-count      (total-documents-count)
        documents-in-this-category (get-documents-count category)]
    (reduce +
      (Math/log (/ documents-in-this-category total-documents-count))
      (map #(word-probability %1 category) source))))

(defn classify-source [source]
  (into (hash-map)
    (doall
      (map
        #(vector %1 (category-probability source %1)) (keys @categories)))))

(defn classify [source]
  (let [source  (language/normalize source)
        results (into (hash-map) (classify-source source))]
      (into (sorted-map-by #(> (%1 results) (%2 results))) results)))

(defn reset []
  (do
    (reset! documents {})
    (reset! categories {})))

