(ns components.systems
  (:require [com.stuartsierra.component :as component]
            [components.aleph :as aleph]
            [components.http-kit :as http-kit]
            [components.mongo :as mongo]))

;; Use this for http-kit based webserver
(defn dev-system [config]
  (component/system-map
   :mongo (mongo/new-mongo-server config)
   :web (component/using (http-kit/new-http-server config) [:mongo])))

;; Use this for aleph based webserver
(defn prod-system [config]
  (component/system-map
   :mongo (mongo/new-mongo-server config)
   :web (component/using (aleph/new-aleph-server config) [:mongo])))
