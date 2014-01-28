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
      (reset! documents {})
      (reset! categories {})))
  (testing
    (do
      (train "testing kittens and kittens" :testing)
      (is (= (unique-words-count) 2))
      (reset! documents {})
      (reset! categories {}))))

(deftest get-words-count-in-category-test
  (testing
    (is (= (words-in-category :kittens) 0)))
  (testing
    (is (= (words-in-category :testing) 0)))
  (testing
    (do
      (train "kittens" :positive)
      (is (= (words-in-category :positive) 1))
      (reset! documents {})
      (reset! categories {}))))
