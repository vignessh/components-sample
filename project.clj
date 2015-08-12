(defproject components "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [com.novemberain/monger "3.0.0-rc1"]
                 [aleph "0.4.0"]
                 [http-kit "2.1.18"]
                 [org.clojure/tools.cli "0.3.1"]
                 [prismatic/schema "0.4.3"]
                 [metosin/compojure-api "0.22.0"]
                 [metosin/ring-swagger "0.20.4"]
                 [metosin/ring-swagger-ui "2.1.1-M2"]
                 [metosin/ring-middleware-format "0.6.0"]
                 [metosin/ring-http-response "0.6.2"]
                 [com.stuartsierra/component "0.2.3"]
                 [environ "1.0.0"]]
  :min-lein-version "2.3.3"
  :jvm-opts ^:replace ["-server" "-Xmx4g"]  
  :main components.core
  :plugins [[lein-environ "1.0.0"]]
  :profiles
  {:dev 
   {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}
  :aot :all)
