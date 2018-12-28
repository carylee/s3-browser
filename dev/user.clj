(ns user
  (:require
    [clojure.tools.namespace.repl :refer [refresh]]
    [cognitect.rebl :as rebl]
    [clojure.repl :refer :all]
    [s3-browser.core :refer [ls]]))


(comment
	;; Launch the REBL
	(rebl/ui)

  ;; From the REBL, start by listing S3 buckets
	;; After the bucket listing, you can browse the entire
	;; bucket, including previewing files, using the REBL
	(ls))
