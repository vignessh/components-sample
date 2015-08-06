(ns components.mongo
  (:require [com.stuartsierra.component :as component]))

(defrecord MongoServer [host port]
  component/Lifecycle

  (start [this]
    (println ";; Starting MongoServer")
    (println this)
    (assoc this :db "db")) 
  
  (stop [this]
    (println ";; Stopping MongoServer")
    (println this)
    (dissoc this :db)))

(defn new-mongo-server [{:keys [host mongo-port]}]
  (map->MongoServer {:host host :port mongo-port}))
