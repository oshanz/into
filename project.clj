(defproject io.factorhouse/into "1.0.0"

  :description "A hurdle of the technical variety"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/tools.logging "1.3.0"]
                 [ch.qos.logback/logback-classic "1.3.15"]
                 [clj-kondo "2024.11.14"]
                 [com.bhauman/rebel-readline "0.1.5"]]

  :aliases {"kondo" ["run" "-m" "clj-kondo.main" "--lint" "src:test" "--parallel"]
            "rebl" ["trampoline" "run" "-m" "rebel-readline.main"]})
