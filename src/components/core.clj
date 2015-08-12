(ns components.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [components.handler :as h]
            [components.systems :as systems]))

(def system 
  (systems/dev-system {:host "localhost" 
                        :mongo-port 27017
                        :database "b2b"
                        :options {:connections-per-host 10}
                        :port 8080 
                        :handler h/handler}))

(defn start []
  (component/start system))

(defn shutdown []
  (component/stop system))

(defn -main [& args]
  (.addShutdownHook (Runtime/getRuntime) (Thread. shutdown))
  (start))
