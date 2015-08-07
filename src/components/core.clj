(ns components.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [components.handler :as h]
            [components.systems :as systems]))

(defn -main [& args]
  (component/start 
   (systems/prod-system {:host "localhost" 
                        :mongo-port 27017 
                        :port 8080 
                        :handler h/handler})))
