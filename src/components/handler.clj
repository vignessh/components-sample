(ns components.handler
  (:require [components.routes :as routes]
            [compojure.api.middleware :as mw]))

(defn handler [components]
  (mw/wrap-components routes/api-routes components))
