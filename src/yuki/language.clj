(ns yuki.language
  (:require [clojure.string :as string]))



(def punctuation #{\~ \` \! \@ \" \#
                   \№ \$ \; \% \^ \:
                   \& \? \* \( \) \-
                   \_ \+ \= \/ \\ \|
                   \[ \] \{ \} \< \>
                   \. \' \,})

(def numbers #{\0 \1 \2 \3 \4
               \5 \6 \7 \8 \9})

(def common-words
  (string/split-lines
    (slurp (clojure.java.io/resource "common-words.txt"))))

(defn exists? [element dictionary]
  (some #(= element %1) dictionary))

(defn is-not-punctuation? [character]
  (not
    (exists? character punctuation)))

(defn is-not-number? [character]
  (not
    (exists? character numbers)))

(defn is-not-common-word? [word]
  (not
    (exists? word common-words)))

(defn remove-punctuation [source]
  (string/join ""
    (filter is-not-punctuation? source)))

(defn remove-numbers [source]
  (filter is-not-number? source))

(defn remove-common-words [source]
  (filter is-not-common-word? source))

(defn tokenize [source]
  (string/split source #"\s+"))

(defn get-stem [word]
  (let [stem (first (re-find #"(\w+?)(?=ing$|ed$|able$|ly$|es$|s$|ny$)" word))]
    (if stem stem word))) ;; TODO:

(defn get-stems [source]
  (map get-stem source))

(defn join-paragraphs [source]
  (string/join " "
    (string/split-lines source)))

(defn normalize [source]
  (get-stems
    (remove-common-words
      (tokenize
        (remove-punctuation
          (remove-numbers
            (join-paragraphs
              (string/lower-case source))))))))
