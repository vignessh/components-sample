(ns components.aleph
  (:require [aleph.http :as http]
            [com.stuartsierra.component :as component]
            [compojure.api.middleware :as mw]))

(defrecord AlephWebServer [port handler]
  component/Lifecycle
  
  (start [this]
    (println ";; Starting Aleph HTTP server")
    (println this)
    (if-let [aleph (:aleph this)]
      (aleph))
    (assoc this :aleph (http/start-server (mw/wrap-components handler this) {:port port})))

  (stop [this]
    (println ";; Stopping Aleph HTTP server")
    (println this)
    (if-let [aleph (:aleph this)]
      (aleph))
    (dissoc this :aleph)))

(defn new-aleph-server [{:keys [port handler]}]
  (map->AlephWebServer {:port port :handler handler}))
