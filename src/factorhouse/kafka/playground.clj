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

(group-by (juxt :broker :partition)  data/sizes)

(sort-by  (juxt :size :broker) data/sizes)

(dissoc {:broker 4, :part 7} :broker)

(map-indexed (fn [i v] [(inc i) v]) ["l" "x" "z"])

(into {} [[1 "a"] {2 "c"}])


(assoc {:name "oshan"} :age 12)

(for [a [1 2 4] b [:a :b]] [a b])

(for [x (range 5)] [x])

(for [x (range 5) :let [y (* x x)]] [y])


(take 20 (for [x (range 100)
               :let [y (inc x)
                     fizz (zero? (mod y 3))
                     buzz (zero? (mod y 5))]]
           (cond (and fizz buzz) "FizzBuzz" fizz "Fizz" buzz "Buzz" :else y)))


(reduce (fn [m k] (assoc m (keyword (str k)) (* k k))) {} (range 100))


(into {} (map (fn [i] [(str i) "ok"])) (range 100))


(filter #(zero? (mod % 3)) (range 100))

(->> (range 1000) (filter #(zero? (mod % 3))) (take 20))

(defn rule [n] (cond
                 (zero? (mod n 15)) "FizzBuzz"
                 (zero? (mod n 3)) "Fizz"
                 (zero? (mod n 5)) "Buzz"))

(->> (range 140 260)
     (map (juxt identity #(rule %)))
     (remove #(nil? (second %)))
     (take 20))

(merge {:l 78} {:j 5 :l 8})

(keys {:j 78 :jn 9 :n 4})

(vals {:k 8 :n 56})

(get-in {:name "oz" :address {:city "vic"}} [:address :city])

(assoc-in {:name "oz"} [:name] "wow")

(update-in {:count 12} [:count] #(+ % 29))
