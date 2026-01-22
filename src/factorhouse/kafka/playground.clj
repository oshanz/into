(ns factorhouse.kafka.playground

  (:require [factorhouse.test.data :as data]))

(use 'com.rpl.specter)

;; https://www.youtube.com/watch?v=rh5J4vacG98

(select [1 "/kfk" :replica-infos ALL LAST] data/topics)

(transform [1 "/kfk" :replica-infos ALL] #(sort-by :size %) data/topics)

(setval [1 "/kfk" :replica-infos ALL LAST] {:a 1} data/topics)

(select [MAP-KEYS] data/topics)

(select [MAP-KEYS NAME] {:a 3 :b 4 :c 5})

(transform [(submap [:a :c]) MAP-VALS]
           inc
           {:a 1, :b 1})

;; collect all keys
(select [ALL FIRST] data/topics)

(transform [ALL ALL] (fn [elem] {:broker elem}) data/topics)

(transform [MAP] (fn [k v] {:broker k :dir v}) data/topics)

(transform [MAP-VALS (selected? :broker map?) ALL] (fn [broker] {:broker broker :topic 1}) data/topics)

(select [MAP-VALS MAP-VALS MAP-VALS ALL] data/topics)

;; juxt inout to a vector
((juxt even? pos-int? odd? #(< % 10)) 23)


;; comp right to left,
;; a(b(c(x)))
;;  pipe a()b()c()

((comp inc #(* % 4) dec) 8)

;; -> first arg ->> last arg
(-> {:name "oshan", :city "VIC" :age 31} (assoc :from "LK") (update :age inc))


(mapcat vector [:v, :k, :h] [78, 4, 5])

(reduce + 50 [8, 5])