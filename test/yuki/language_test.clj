(ns yuki.language-test
  (:require [clojure.test :refer :all]
            [yuki.language :refer :all]))

(deftest exists-test
  (testing
    (is (exists? 1 #{1 2 3 4})))
  (testing
    (is (not (exists? 1 #{2 3 4})))))

(deftest is-not-punctuation-test
  (testing
    (is (is-not-punctuation? \a)))
  (testing
    (is (not (is-not-punctuation? \!)))))

(deftest is-not-common-word-test
  (testing
    (is (is-not-common-word? "arthur")))
  (testing
    (is (not (is-not-common-word? "is")))))

(deftest remove-punctuation-test
  (testing
    (is (= (remove-punctuation "hello, world!") "hello world"))))

(deftest remove-numbers-test
  (testing
    (is (= (clojure.string/join "" (remove-numbers "answer is 42")) "answer is "))))

(deftest remove-common-words-test
  (testing
    (is (= (remove-common-words ["sky" "is" "blue"]) ["sky" "blue"]))))

(deftest tokenize-test
  (testing
    (is (= (tokenize "sky is blue") ["sky" "is" "blue"])))
  (testing
    (is (= (tokenize "kittens") ["kittens"]))))

(deftest get-stem-test
  (testing
    (is (= (get-stem "kittens") "kitten")))
  (testing
    (is (= (get-stem "kitten") "kitten"))))

(deftest get-stems-test
  (testing
    (is (= (get-stems ["funny" "kittens"]) ["fun" "kitten"]))))

(deftest join-paragraphs-test
  (testing
    (is (= (join-paragraphs "very\r\nlarge\ntext") "very large text")))
  (testing
    (is (= (join-paragraphs "wu wei") "wu wei"))))

(deftest normalize-test
  (testing
    (is (= (normalize "In simple terms, a naive Bayes classifier assumes that the presence or absence of a particular feature is unrelated to the presence or absence of any other feature, given the class variable.") ["simple" "term" "naive" "bay" "classifier" "assum" "presence" "absence" "particular" "feature" "unrelat" "presence" "absence" "feature" "given" "clas" "vari"]))))
