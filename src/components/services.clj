(ns components.services
     (:require [monger.collection :as mc]
               [monger.joda-time]
               [monger.json]
               [schema.core :as s]
               [components.document :as d])
     (:import [com.mongodb DB])
     (:import org.bson.types.ObjectId)
     (:import org.joda.time.DateTime)
     (:import components.mongo.MongoServer))

(def collection "b2b.contentstore.document")

(defn ^Boolean is-permitted 
  [^String tenant-id
   ^String user-id
   ^String resource
   ^String permission]
  (Boolean/TRUE))

(s/defn find-by-id :- d/Document 
  [server :- MongoServer 
   tenant-id :- String
   document-id :- String
   user-id :- String]
  (when-let [is-permitted (is-permitted tenant-id user-id (str "/files/" document-id) "READ")]
    (mc/find-one-as-map (:connection server) collection {:_id (ObjectId. document-id) :tenantId tenant-id})))


(s/defn store :- d/Document 
  [server :- MongoServer
   tenant-id :- String
   document :- d/Document]
 (let [{:keys [metadata
               uploadedOn
               modifiedOn
               documentType
               uri
               title
               size
               userTags
               systemTags
               createdBy
               description
               isPublic
               shares
               checksum
               fileName
               status
               creatorShortName]
        :or {uploadedOn (DateTime.)
             modifiedOn (DateTime.)
             isPublic Boolean/FALSE
             userTags ()
             systemTags ()
             shares ()}} document]
   (mc/insert-and-return (:connection server) collection 
                         {:uploadedOn uploadedOn
                          :modifiedOn modifiedOn
                          :documentType documentType
                          :uri uri
                          :title title
                          :tenantId tenant-id
                          :size (Long. size)
                          :createdBy createdBy
                          :description description
                          :isPublic isPublic
                          :checksum checksum
                          :fileName fileName
                          :status "INPROGRESS"
                          :metadata metadata
                          :userTags userTags
                          :systemTags systemTags})))
