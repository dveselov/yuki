# yuki

Naive Bayes Classifier implemented in Clojure

## Usage

```clojure
(use '[yuki.bayes :as bayes])

(bayes/train "meow" :kittens)
(bayes/train "bark" :puppies)

(bayes/classify "pshchhh")
```

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
