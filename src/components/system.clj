(ns components.system
  (:require [com.stuartsierra.component :as component]
            [components.aleph :as aleph]
            [components.mongo :as mongo]))

(defn prod-system [config]
  (component/system-map
   :mongo (mongo/new-mongo-server config)
   :web (component/using (aleph/new-aleph-server config) [:mongo])))
