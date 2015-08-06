(ns components.routes
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]))

(defapi api-routes
   (ring.swagger.ui/swagger-ui)
   (swagger-docs)
   (context* "/echo" []
             :tags ["echo"]
             :components [mongo]
             (GET* "/:name" []
                   :return String
                   :path-params [name :- String]
                   :summary "Echoes the given name"
                   (ok (str "Hello, " name mongo))))

  (context* "/:tenant-id/document" [tenant-id]
            :tags ["document"]
            :path-params [tenant-id :- String]
            :header-params [username :- String, shortname :- String]
            :components [mongo]
            (GET* "/:id" [id :as request]
                  :path-params [id :- String]
                  :summary "Returns the document identified by the given identifier"
                  (ok {:createdBy username :creatorShortName shortname :tenant-id tenant-id}))
            (POST* "/" [:as request]
                   :summary "Attach given metadata alongwith uploaded document"
                   (let [content (request :body)
                         meta (merge (content :metadata) {:createdBy username :creatorShortName shortname})
                         document (assoc content :metadata meta)]
                     (ok document)))))
