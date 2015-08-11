(ns components.document
  (:require [schema.core :as s]
            [ring.swagger.json-schema :as json-schema])
  (:import org.joda.time.DateTime)
  (:import org.bson.types.ObjectId))

(defmethod json-schema/json-type org.bson.types.ObjectId [_] {:type "string"})

(s/defschema Metadata
  {(s/optional-key :createdBy) String
   (s/optional-key :creatorShortName) String})

(s/defschema Document 
  {(s/optional-key :_id) ObjectId
   :tenantId String
   (s/optional-key :uploadedOn) DateTime
   (s/optional-key :createdBy) String
   (s/optional-key :modifiedOn) DateTime
   (s/optional-key :documentType) String
   :uri String
   :title String
   :size Long
   :description String
   (s/optional-key :isPublic) Boolean
   (s/optional-key :checksum) String
   :fileName String
   :status String
   (s/optional-key :metadata) Metadata
   (s/optional-key :userTags) [String]
   (s/optional-key :systemTags) [String]
   (s/optional-key :shares) [String]})
