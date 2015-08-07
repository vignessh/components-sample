(ns components.routes
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.swagger.ui :as swagger]))

(defapi api-routes
   (swagger/swagger-ui)
   (swagger-docs {:info 
                  {:version "1.0.0"
                   :title "API endpoints"
                   :contact {:email "contact@me.com"}}
                  :tags [{:name "example" :description "Sample use of Compojure API and the Components library"}]})
   (context* "/echo" []
             :tags ["echo"]
             :components [mongo]
             (GET* "/:name" []
                   :return String
                   :path-params [name :- String]
                   :summary "Echoes the given name"
                   (ok (str "Hello, " name))))

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
