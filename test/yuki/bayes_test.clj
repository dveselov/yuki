(ns yuki.bayes-test
  (:require [clojure.test :refer :all]
            [yuki.bayes :refer :all]))

(deftest get-unique-words-count-test
  (testing
    (is (= (unique-words-count) 0)))
  (testing
    (do
      (train "testing kittens" :testing)
      (is (= (unique-words-count) 2))
      (reset)))
  (testing
    (do
      (train "testing kittens and kittens" :testing)
      (is (= (unique-words-count) 2))
      (reset))))

(deftest get-words-count-in-category-test
  (testing
    (is (= (words-in-category :kittens) 0)))
  (testing
    (is (= (words-in-category :testing) 0)))
  (testing
    (do
      (train "kittens" :positive)
      (is (= (words-in-category :positive) 1))
      (reset))))

(deftest training-test
  (testing
    (do
      (train "meow" :positive)
      (train "bark" :negative)
      (let [category (classify "meow")]
        (is (= category {:negative -1.791759469228055, :positive -1.0986122886681096}))
        (is (> (:positive category) (:negative category))))
      (reset))))

