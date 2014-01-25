(ns yuki.language-test
  (:require [clojure.test :refer :all]
            [yuki.language :refer :all]))

(deftest exists-test
  (testing "that `exists?` function finds element in set"
    (is (exists? 1 #{1 2 3 4})))
  (testing "that `exists?` function doesn't finds element in set"
    (is (not (exists? 1 #{2 3 4})))))

(deftest is-not-punctuation-test
  (testing "that `is-not-punctuation?` function returns positive result"
    (is (is-not-punctuation? \a)))
  (testing "that `is-not-punctuation?` function returns negative result"
    (is (not (is-not-punctuation? \!)))))

(deftest is-not-common-word-test
  (testing "that `is-not-common-word?` function returns positive result"
    (is (is-not-common-word? "arthur")))
  (testing "that `is-not-common-word?` function returns negative result"
    (is (not (is-not-common-word? "is")))))

(deftest remove-punctuation-test
  (testing "that `remove-punctuation` function removes punctuation"
    (is (= (remove-punctuation "hello, world!") "hello world"))))

(deftest remove-numbers-test
  (testing "thar `remove-numbers` function removes numbers"
    (is (= (clojure.string/join "" (remove-numbers "answer is 42")) "answer is "))))

(deftest remove-common-words-test
  (testing "that `remove-common-words` function removes common words"
    (is (= (remove-common-words ["sky" "is" "blue"]) ["sky" "blue"]))))

(deftest tokenize-test
  (testing "that `tokenize` function splits string by words"
    (is (= (tokenize "sky is blue") ["sky" "is" "blue"])))
  (testing "that `tokenize` function don't breaks string, if they contains only one word"
    (is (= (tokenize "kittens") ["kittens"]))))

(deftest get-stem-test
  (testing "that `get-stem` function finds stem"
    (is (= (get-stem "kittens") "kitten")))
  (testing "that `get-stem` function doesn't finds stem"
    (is (= (get-stem "kitten") "kitten"))))

(deftest get-stems-test
  (testing "that `get-stems` function returns stems for words in given list"
    (is (= (get-stems ["funny" "kittens"]) ["fun" "kitten"]))))

(deftest join-paragraphs-test
  (testing "that `join-paragraphs` function joins multiple lines into one"
    (is (= (join-paragraphs "very\r\nlarge\ntext") "very large text")))
  (testing "that `join-paragraphs` function not breaks string, if they contains only one line"
    (is (= (join-paragraphs "wu wei") "wu wei"))))

(deftest normalize-test
  (testing "that `prepare` function prepares text for classification"
    (is (= (normalize "In simple terms, a naive Bayes classifier assumes that the presence or absence of a particular feature is unrelated to the presence or absence of any other feature, given the class variable.") ["simple" "term" "naive" "bay" "classifier" "assum" "presence" "absence" "particular" "feature" "unrelat" "presence" "absence" "feature" "given" "clas" "vari"]))))