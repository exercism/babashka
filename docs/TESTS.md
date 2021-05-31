# Tests

## clojure.test

Babashka comes bundled with clojure.test. Once you are ready to work on an exercise and have created a file to hold your solution (such as `bob.clj`) you can run the tests using `clojure.test/run-tests` [as described here](http://clojure.github.io/clojure/clojure.test-api.html#clojure.test/run-tests).


The REPL allows you to easily run code and get immediate feedback and can also be used to run tests. It is recommended to use `rlwrap` to enable line editing and command history with up/down arrows. To test an exercise, change to its directory and open a socket REPL adding the `src` and `test` directories to the classpath:
``` bash
/bob $ rlwrap bb --classpath src:test socket-repl 1666
```

First, `require` the test namespace:
``` clojure
=> (require 'bob-test)
nil
```

Then call `run-tests` on `bob-test`:
``` clojure
=> (clojure.test/run-tests 'bob-test)

Testing bob-test

Ran 25 tests containing 14 assertions.
0 failures, 0 errors.
{:test 25, :pass 25, :fail 0, :error 0, :type :summary}
```
