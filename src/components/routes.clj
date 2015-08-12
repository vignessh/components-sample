(ns components.routes
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [ring.swagger.ui :as swagger]
            [components.services :as services]
            [components.document :as d]))

(s/defschema User {:name s/Str
                   :sex (s/enum :male :female)
                   :address {:street s/Str
                             :zip s/Str}})

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
                   (ok (str "Hello, " name)))
             (PUT* "/anonymous" []
                   :return   [{:hot Boolean}]
                   :body     [body [{:hot (s/either Boolean String)}]]
                   :summary  "echoes a vector of anonymous hotties"
                   (ok body))
             (POST* "/" []
                    :return User
                    :body [user User]
                    (ok user)))

  (context* "/:tenant-id/document" [tenant-id]
            :tags ["document"]
            :path-params [tenant-id :- String]
            :header-params [username :- String, shortname :- String]
            :components [mongo]
            (GET* "/:id" [id :as request]
                  :path-params [id :- String]
                  :return d/Document
                  :summary "Returns the document identified by the given identifier"
                  ;(println (:headers request))
                  (ok (services/find-by-id mongo tenant-id id username)))
            (POST* "/" []
                   :body [document d/Document]
                   :return d/Document
                   :summary "Attach given metadata alongwith uploaded document"
                   (prn document)
                   (ok document))))
