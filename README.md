# yuki [![Build Status](https://travis-ci.org/dieselpoweredkitten/yuki.png?branch=master)](https://travis-ci.org/dieselpoweredkitten/yuki)

Naive Bayes Classifier implemented in Clojure

## Usage

```clojure
(use '[yuki.bayes :as bayes])

(bayes/train "meow" :kittens)
(bayes/train "bark" :puppies)

(bayes/classify "little kitten meows")
;; => {:kittens -3.2958368660043287, :puppies -3.9889840465642745}
(bayes/classify "big dog barks at you")
;; => {:puppies -3.2958368660043287, :kittens -3.9889840465642745}
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
