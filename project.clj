(defproject s3-browser "0.1.0-SNAPSHOT"
  :description "Demo project using Clojure 1.10's nav and the REBL to browse S3"
  :url "https://github.com/carylee/s3-browser"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "0.4.490"]
                 [com.cognitect/rebl "0.9.109"]
                 [amazonica "0.3.136"]]
  :main s3-browser.core
  :profiles {:repl
             {:source-paths ["dev"]}})
