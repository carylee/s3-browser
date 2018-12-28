# S3 Browser

S3 Browser is a toy project to learn and demo the use of Clojure 1.10's [nav](https://clojure.github.io/clojure/clojure.core-api.html#clojure.core.protocols/nav) function.
Using the [REBL](http://rebl.cognitect.com/), you can list S3 buckets, browse the keys
in a bucket by prefix (like navigating a file tree), and even preview file contents.
All this is done with minimal code by implementing the `Navigable` protocol on S3 buckets, prefixes, and objects.

## Prerequisites

S3 Browser expects you to already have the REBL locally, in form of the `com.cognitect/rebl "0.9.109"` artifact installed to your local maven repository.

### Installing the REBL to your local repository

1. Add the [lein-localrepo](https://github.com/kumarshantanu/lein-localrepo) leiningen plugin to your `profiles.clj`
2. Download and unzip [the REBL](http://rebl.cognitect.com/)
3. Use the lein-localrep plugin to install the REBL to your m2 repository

```bash
lein localrepo install REBL-0.9.108.jar com.cognitect/rebl 0.9.109```
```

## Usage

Start a repl using `lein repl`. This will default to the `user` namespace, which refers to the core function `ls`.

Launch the REBL:

```clojure
(rebl/ui)
```

In the REBL, evaluate the `ls` function to list buckets in your account:
```clojure
(ls)
```

From there, you can navigate the S3 buckets in your AWS account using the REBL graphically. Note: you will not be able to navigate to buckets you do not have permission to list.


## Caveats

S3 Browser uses your default AWS credential chain and settings. It doesn't currently work with cross-region buckets, so requests will be made in the default region for your AWS profile (look at your `~/.aws/config`).

S3 Browser also is only intended to demo `nav` and the REBL, so it isn't tested extensively, nor does it have any error handling. The REBL itself, however, gracefully presents stack traces (themselves implementing `datafy` and `nav`).

## Resources

* Stuart Halloway's [REBL talk](https://www.youtube.com/watch?v=c52QhiXsmyI) from Clojure/conj 2018
* Sean Corfields [Datafy and Nav blog post](http://corfield.org/blog/2018/12/03/datafy-nav/)
* Jay Zawrotny's [lein-rebl-example](https://github.com/jayzawrotny/lein-rebl-example)
