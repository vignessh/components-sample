(ns components.middleware
  (:require [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [compojure.api.middleware :as mw]))

(defn make-handler2 [handler]
  (->
   (mw/api-middleware handler ;wrap-defaults handler api-defaults
                      ) 
   ;(wrap-json-body {:keywords? true})
   ;(wrap-json-response {:pretty true :escape-non-ascii true})
   ))

(defn make-handler [handler]
  (->
   (mw/api-middleware handler)
   ;(wrap-json-body {:keywords? true})
   ))
