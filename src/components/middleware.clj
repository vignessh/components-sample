(ns components.middleware
  (:require [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn make-handler [handler]
  (->
   (wrap-defaults handler api-defaults)
   (wrap-json-body {:keywords? true})
   (wrap-json-response {:pretty true :escape-non-ascii true})))
