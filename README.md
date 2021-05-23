# Exercism Babashka Track

[![Build Status](https://travis-ci.org/exercism/babashka.svg?branch=master)](https://travis-ci.org/exercism/babashka)

Exercism exercises in Babashka.

Haven't heard of Babashka? It's brand-new! Watch the [introductory talk](https://www.youtube.com/watch?v=Nw8aN-nrdEk).

## Setup

* Babashka installation is described in the [INSTALLATION](docs/INSTALLATION.md) document.
* Setup instructions and example editor configurations can be found in the [editors](docs/editors.md) document. 

## Exercise Tests

At the most basic level, Exercism is all about the tests. You can read more about how we think about test suites in [the Exercism documentation](https://github.com/exercism/legacy-docs/blob/master/language-tracks/exercises/anatomy/test-suites.md). Babashka comes bundled with [clojure.test](https://clojure.github.io/clojure/clojure.test-api.html). To run the entire test suite for all 76 exercises:

```bash
$ bb -f _test/check_exercises.clj
```

## Contributing

Thank you so much for contributing! :tada:

Please read about how to [get involved in a track](https://github.com/exercism/legacy-docs/tree/master/contributing-to-language-tracks). Be sure to read the Exercism [Code of Conduct](https://exercism.io/code-of-conduct).

We welcome pull requests of all kinds. No contribution is too small.

We encourage contributions that provide fixes and improvements to existing exercises. Please note that this track's exercises must conform to the Exercism-wide standards described in the [documentation](https://github.com/exercism/legacy-docs/tree/master/language-tracks/exercises). If you're unsure about how to make a change, then go ahead and [open an issue](https://github.com/exercism/babashka/issues) and we'll discuss it.
