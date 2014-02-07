# yuki [![Build Status](https://travis-ci.org/dieselpoweredkitten/yuki.png?branch=master)](https://travis-ci.org/dieselpoweredkitten/yuki)

Naive Bayes Classifier implemented in Clojure

## Usage

```clojure
(use '[yuki.bayes :as bayes])

(bayes/train "meow" :kittens)
(bayes/train "bark" :puppies)

(bayes/classify "pshchhh") ;; => {:kittens -1.791759469228055, :puppies -1.0986122886681096}
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
