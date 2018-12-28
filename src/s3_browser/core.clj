(ns s3-browser.core
  (:require [amazonica.aws.s3 :as s3]
            [clojure.string :as str]
						[cognitect.rebl :as rebl]
            [clojure.core.protocols :as p]))


(def ^:dynamic *preview-max-bytes*)


(defn get-object
  [bucket item-key]
  (-> (s3/get-object :bucket-name bucket :key item-key :range [0 *preview-max-bytes*])
      (with-meta {`p/nav (fn [coll k v]
                           (if (#{:input-stream :object-content} k)
                             (slurp v)
                            v))})))


(declare ls)


(defn list-buckets
  []
  (-> (mapv :name (s3/list-buckets))
      (with-meta {`p/nav (fn [coll k v]
                           (ls v))})))

(defn ls
  ([]
   (list-buckets))
  ([bucket]
   (ls bucket "" "/"))
  ([bucket prefix]
   (ls bucket prefix "/"))
  ([bucket prefix delimiter]
   (let [resp (s3/list-objects-v2 {:bucket-name bucket
                                   :prefix prefix
                                   :delimiter delimiter})]
    (-> (concat (sort (:common-prefixes resp))
                (sort (mapv :key (:object-summaries resp))))
        (with-meta {`p/nav (fn [coll k v]
                             (if (str/ends-with? v delimiter)
                               (ls bucket v delimiter)
                               (get-object bucket v)))})))))
