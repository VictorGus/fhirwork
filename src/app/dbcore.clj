(ns app.dbcore
  (:require [cheshire.core       :as json]
            [monger.core         :as mg]
            [monger.credentials  :as mg-cred]
            [monger.collection   :as mg-col]
            [monger.conversion   :as mg-conv]
            [monger.operators    :as mg-ops]
            [app.manifest        :refer [app-config]]))


(defonce operators-dictionary
  {:gt          mg-ops/$gt
   :gte         mg-ops/$gte
   :lt          mg-ops/$lt
   :lte         mg-ops/$lte
   :all         mg-ops/$all
   :in          mg-ops/$in
   :nin         mg-ops/$nin
   :eg          mg-ops/$eq
   :ne          mg-ops/$ne
   :elemMatch   mg-ops/$elemMatch
   :regex       mg-ops/$regex
   :options     mg-ops/$options
   :where       mg-ops/$where
   :and         mg-ops/$and
   :or          mg-ops/$or
   :nor         mg-ops/$nor
   :inc         mg-ops/$inc
   :mul         mg-ops/$mul
   :set         mg-ops/$set
   :unset       mg-ops/$unset
   :setOnInsert mg-ops/$setOnInsert
   :rename      mg-ops/$rename
   :push        mg-ops/$push
   :position    mg-ops/$position
   :each        mg-ops/$each
   :addToSet    mg-ops/$addToSet
   :pop         mg-ops/$pop
   :pull        mg-ops/$pull
   :pullAll     mg-ops/$pullAll
   :bit         mg-ops/$bit
   :exists      mg-ops/$exists
   :mod         mg-ops/$mod
   :type        mg-ops/$type
   :size        mg-ops/$size
   :not         mg-ops/$not})

(defn get-operator [k]
  (k operators-dictionary))

(defn create-client [user password dbname]
  (mg-cred/create user dbname (.toCharArray password)))

(defn create-connection [{{:keys [host port user dbname password]} :db :as config}]
  (let [client (create-client user password dbname)]
    (mg/connect-with-credentials host port client)))

(defn extract-db [connection dbname]
  (mg/get-db connection dbname))

(defn get-connection [{{:keys [dbname]} :db :as config}]
  (let [connection (create-connection config)
        db (extract-db connection dbname)]
    db))

(def db-connection (delay (get-connection app-config)))

(defn truncate-collection [db collection]
  (mg-col/remove @db collection))

(defn create [db collection object]
  (mg-col/insert-and-return @db collection object))

(defn create-batch [db collection objects]
  (mg-col/insert-batch @db collection objects))

(defn search [db collection query]
  (mg-col/find-maps @db collection query))

(defn search-by-id [db collection id]
  (mg-col/find-map-by-id @db collection id))

(defn update
  ([db collection query object]
   (let [object* (reduce-kv (fn [acc k v]
                             (if-let [op (get-operator k)]
                               (assoc acc op v)
                               (assoc acc k v))) {} object)]
     (mg-col/update @db collection query object*)))
  ([db collection query object {:keys [upsert] :as ops}]
   (let [object* (reduce-kv (fn [acc k v]
                             (if-let [op (get-operator k)]
                               (assoc acc op v)
                               (assoc acc k v))) {} object)]
     (mg-col/update db collection query object* {:upsert upsert}))))

(defn update-by-id
  ([db collection id object]
   (let [object* (reduce-kv (fn [acc k v]
                             (if-let [op (get-operator k)]
                               (assoc acc op v)
                               (assoc acc k v))) {} object)]
     (mg-col/update-by-id @db collection id object*)))
  ([db collection id object {:keys [upsert] :as ops}]
   (let [object* (reduce-kv (fn [acc k v]
                             (if-let [op (get-operator k)]
                               (assoc acc op v)
                               (assoc acc k v))) {} object)]
     (mg-col/update-by-id @db collection id object* {:upsert upsert}))))

(defn delete [db collection query]
  (mg-col/remove @db collection query))

(defn delete-by-id [db collection id]
  (mg-col/remove-by-id @db collection id))

(comment

  (mg-col/insert-and-return @db-connection "documents" {:name "Monger"
                                                        :_id "123"})

  (mg-col/count @db-connection "documents")

  (mg-col/find-maps @db-connection "documents" {:_id "123"})

  (mg-col/update @db-connection "documents" {:name "Test1"} {:age 20})

  (search db-connection "documents" {:age 32})

  (update-by-id db-connection "documents" "123" {:age 33})

  (delete-by-id db-connection :documents "123567")


  )

