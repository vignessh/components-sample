(ns components.handler
  (:require [components.middleware :as mw]
            [components.routes :as routes]))

(def handler (mw/make-handler routes/api-routes))
