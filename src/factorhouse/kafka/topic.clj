(ns factorhouse.kafka.topic
  (:require
   [factorhouse.test.data :as data]))

;; Technical Challenge! Implement this function.

(defn sizes
  "Transform raw topic information into a more useable sizes shape"
  [topics]
  (->>
   (for [broker (keys topics)
         directory (keys (get-in topics [broker]))
         [topic-info partition-info] (get-in topics [broker directory :replica-infos])]
     (merge topic-info partition-info {:broker broker :dir directory}))
   (sort-by (juxt :broker :topic :partition))))


(sizes data/topics)


;; Extension Challenge! Implement these functions.

(defn categories-logical
  "Transform topic sizes into categorised logical view"
  [sizes])

(defn categories-physical
  "Transform topic sizes into categorised physical view"
  [sizes])