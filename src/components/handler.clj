(ns components.handler
  (:require [components.middleware :as mw]
            [components.routes :as routes]))

(def handler routes/api-routes)
(def handler2 (mw/make-handler routes/api-routes))
